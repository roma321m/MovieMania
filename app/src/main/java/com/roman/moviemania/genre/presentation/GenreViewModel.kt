package com.roman.moviemania.genre.presentation

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roman.moviemania.app.launcher.ActivityLauncher
import com.roman.moviemania.core.domain.model.DiscoverSortOptions
import com.roman.moviemania.core.domain.model.ImageConfiguration
import com.roman.moviemania.core.domain.model.Movie
import com.roman.moviemania.core.domain.repository.ConfigurationRepository
import com.roman.moviemania.core.domain.repository.DiscoverRepository
import com.roman.moviemania.core.domain.utils.onError
import com.roman.moviemania.core.domain.utils.onSuccess
import com.roman.moviemania.genre.domain.model.Genre
import com.roman.moviemania.genre.domain.repository.GenreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GenreViewModel(
    private val genreRepository: GenreRepository,
    private val discoverRepository: DiscoverRepository,
    private val configRepository: ConfigurationRepository,
    private val activityLauncher: ActivityLauncher
) : ViewModel() {

    companion object {
        private const val TAG = "GenreViewModel"
    }

    private val _uiState = MutableStateFlow(GenreUiState())
    val uiState = _uiState
        .onStart {
            loadGenres()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            GenreUiState()
        )

    private val _events = Channel<GenreEvents>()
    val events = _events.receiveAsFlow()

    private var imageConfiguration: ImageConfiguration? = configRepository.cachedImageConfiguration
    private var page = 1
    private var loadingNextPage = false

    fun onLifecycleEvent(event: Lifecycle.Event) {
        Log.d(TAG, "onLifecycleEvent: ${event.name}")

        when (event) {
            Lifecycle.Event.ON_CREATE -> Unit
            else -> Unit
        }
    }

    fun onAction(action: GenreAction) {
        Log.d(TAG, "onAction: $action")
        when (action) {
            is GenreAction.OnGenreSelected -> selectGenre(action.genre)
            is GenreAction.OnMovieSelected -> selectMovie(action.movie)
            is GenreAction.OnMoreActionClick -> onMoreActionClick(action.expanded)
            GenreAction.OnPrivacyPolicyClick -> onPrivacyPolicyClick()
            GenreAction.OnTermsAndConditionsClick -> onTermsClick()
            GenreAction.OnAboutClicked -> onAboutClick()
            is GenreAction.OnSortActionClick -> onSortActionClick(action.expanded)
            is GenreAction.OnSortOptionClick -> onSortOptionClick(action.sort)
            GenreAction.OnScrollEnding -> onScrollEnding()
        }
    }

    private fun onMoreActionClick(expanded: Boolean) {
        Log.d(TAG, "onMoreActionClick: $expanded")

        _uiState.update {
            it.copy(showMoreMenu = expanded)
        }
    }

    private fun onPrivacyPolicyClick() {
        Log.d(TAG, "onPrivacyPolicyClick")

        _uiState.update {
            it.copy(showMoreMenu = false)
        }
        activityLauncher.launchPrivacyPolicy()
    }

    private fun onTermsClick() {
        Log.d(TAG, "onTermsClick")

        _uiState.update {
            it.copy(showMoreMenu = false)
        }
        activityLauncher.launchTermsAndConditions()
    }

    private fun onAboutClick() {
        Log.d(TAG, "onAboutClick")

        _uiState.update {
            it.copy(showMoreMenu = false)
        }
        viewModelScope.launch {
            _events.send(GenreEvents.About)
        }
    }

    private fun onSortActionClick(expanded: Boolean) {
        Log.d(TAG, "onSortActionClick: $expanded")

        _uiState.update {
            it.copy(showSortMenu = expanded)
        }
    }

    private fun onSortOptionClick(sort: DiscoverSortOptions) {
        Log.d(TAG, "onSortOptionClick: $sort")

        if (sort != _uiState.value.sortBy) {
            _uiState.value.selectedGenre?.let { loadMovies(it) }
        }
        _uiState.update {
            it.copy(
                showSortMenu = false,
                sortBy = sort
            )
        }
    }

    private fun selectGenre(genre: Genre) {
        Log.d(TAG, "selectGenre: $genre")

        _uiState.update {
            it.copy(selectedGenre = genre)
        }

        loadMovies(genre)
    }

    private fun selectMovie(movie: Movie) {
        Log.d(TAG, "selectMovie: $movie")

        // todo
    }

    private fun loadGenres() = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "loadGenres")
        _uiState.update {
            it.copy(isLoading = true)
        }

        genreRepository.getGenres()
            .onSuccess { genres ->
                Log.d(TAG, "loadGenres: $genres")
                _uiState.update {
                    it.copy(
                        genres = genres,
                        isLoading = false,
                    )
                }
                genres.firstOrNull()?.let { selectGenre(it) }
            }
            .onError { error ->
                Log.e(TAG, "loadGenres: $error")
                _uiState.update {
                    it.copy(isLoading = false)
                }
                _events.send(GenreEvents.Error(error))
            }
    }

    private fun loadMovies(genre: Genre) {
        Log.d(TAG, "loadMovies: $genre")

        if (imageConfiguration == null) {
            loadImageConfiguration()
            return
        }

        loadingNextPage = true
        page = 1

        viewModelScope.launch(Dispatchers.IO) {
            discoverRepository
                .getDiscoverMovieByGenre(
                    imageConfiguration = imageConfiguration,
                    page = page,
                    genreId = genre.id,
                    sort = _uiState.value.sortBy
                )
                .onSuccess { movies ->
                    Log.d(TAG, "loadMovies: $movies")
                    _uiState.update { state ->
                        state.copy(movies = movies.distinctBy { it.id })
                    }
                    loadingNextPage = false
                }
                .onError { error ->
                    Log.e(TAG, "loadMovies: $error")
                    _events.send(GenreEvents.Error(error))
                    loadingNextPage = false
                }
        }
    }

    private fun onScrollEnding() {
        Log.d(TAG, "onScrollEnding")
        if (loadingNextPage) return

        loadingNextPage = true
        page++
        loadMoreMovies()
    }

    private fun loadMoreMovies() = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "loadMoreMovies")
        val genreId = _uiState.value.selectedGenre?.id ?: return@launch

        discoverRepository
            .getDiscoverMovieByGenre(
                imageConfiguration = imageConfiguration,
                page = page,
                genreId = genreId,
                sort = _uiState.value.sortBy
            )
            .onSuccess { movies ->
                Log.d(TAG, "loadMoreMovies: $movies")
                _uiState.update { state ->
                    state.copy(
                        movies = (_uiState.value.movies + movies)
                            .distinctBy { it.id }
                    )
                }
                loadingNextPage = false
            }
            .onError { error ->
                Log.e(TAG, "loadMoreMovies: $error")
                _events.send(GenreEvents.Error(error))
                loadingNextPage = false
            }
    }


    private fun loadImageConfiguration() = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "loadImageConfiguration")
        configRepository
            .getImageConfiguration()
            .onSuccess { config ->
                imageConfiguration = config
            }
            .onError { error ->
                Log.e(TAG, "loadImageConfiguration: $error")
                _events.send(GenreEvents.Error(error))
            }
    }
}