package com.roman.moviemania.genre.presentation

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roman.moviemania.core.domain.utils.onError
import com.roman.moviemania.core.domain.utils.onSuccess
import com.roman.moviemania.genre.domain.Genre
import com.roman.moviemania.genre.domain.GenreRepository
import com.roman.moviemania.genre.domain.Movie
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
    private val genreRepository: GenreRepository
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
            GenreAction.OnSearchFabClick -> fabClick()
        }
    }

    private fun selectGenre(genre: Genre) {
        Log.d(TAG, "selectGenre: $genre")

        _uiState.update {
            it.copy(selectedGenre = genre)
        }
        // todo
    }

    private fun selectMovie(movie: Movie) {
        Log.d(TAG, "selectMovie: $movie")

        // todo
    }

    private fun fabClick() {
        Log.d(TAG, "fabClick")

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
                        selectedGenre = genres.firstOrNull()
                    )
                }
            }
            .onError { error ->
                Log.e(TAG, "loadGenres: $error")
                _uiState.update {
                    it.copy(isLoading = false)
                }
                _events.send(GenreEvents.Error(error))
            }
    }
}