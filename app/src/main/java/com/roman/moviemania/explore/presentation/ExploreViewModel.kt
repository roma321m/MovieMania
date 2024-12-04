package com.roman.moviemania.explore.presentation

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roman.moviemania.app.launcher.ActivityLauncher
import com.roman.moviemania.core.domain.model.ImageConfiguration
import com.roman.moviemania.core.domain.repository.ConfigurationRepository
import com.roman.moviemania.core.domain.repository.DiscoverRepository
import com.roman.moviemania.core.domain.utils.onError
import com.roman.moviemania.core.domain.utils.onSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExploreViewModel(
    private val discoverRepository: DiscoverRepository,
    private val configRepository: ConfigurationRepository,
    private val activityLauncher: ActivityLauncher
) : ViewModel() {

    companion object {
        private const val TAG = "ExploreViewModel"
    }

    private var imageConfiguration: ImageConfiguration? = configRepository.cachedImageConfiguration

    private val _uiState = MutableStateFlow(ExploreUiState())
    val uiState = _uiState
        .onStart {
            onStart()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            ExploreUiState()
        )

    private val _events = Channel<ExploreEvents>()
    val events = _events.receiveAsFlow()


    fun onLifecycleEvent(event: Lifecycle.Event) {
        Log.d(TAG, "onLifecycleEvent: ${event.name}")

        when (event) {
            Lifecycle.Event.ON_CREATE -> Unit
            else -> Unit
        }
    }

    fun onAction(action: ExploreAction) {
        Log.d(TAG, "onAction: $action")
        when (action) {
            ExploreAction.OnSearchFabClick -> onSearchFabClick()
            ExploreAction.OnAboutClicked -> onAboutClick()
            is ExploreAction.OnMoreActionClick -> onMoreActionClick(action.expand)
            ExploreAction.OnPrivacyPolicyClick -> onPrivacyPolicyClick()
            ExploreAction.OnTermsAndConditionsClick -> onTermsClick()
            ExploreAction.OnHideSearchBar -> onHideSearch()
            ExploreAction.OnSearch -> onSearch()
            is ExploreAction.OnSearchQueryChange -> onSearchQueryChange(action.query)
            is ExploreAction.OnSearchExpanded -> onSearchExpanded(action.expanded)
        }
    }

    private fun onSearchQueryChange(query: String) {
        Log.d(TAG, "onSearchQueryChange: $query")

        _uiState.update {
            it.copy(searchQuery = query)
        }
    }

    private fun onHideSearch() {
        Log.d(TAG, "onHideSearch")

        _uiState.update {
            it.copy(
                searchActive = false,
                expendedSearch = false,
            )
        }
    }

    private fun onSearch() {
        Log.d(TAG, "onSearch")
        // todo
    }

    private fun onSearchExpanded(expanded: Boolean) {
        Log.d(TAG, "onSearchExpanded: $expanded")

        _uiState.update {
            it.copy(expendedSearch = expanded)
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
            _events.send(ExploreEvents.About)
        }
    }

    private fun onSearchFabClick() {
        Log.d(TAG, "onSearchFabClick")

        _uiState.update {
            it.copy(searchActive = true)
        }
    }

    private fun onStart() = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "onStart")

        if (imageConfiguration == null) {
            loadImageConfiguration()
            return@launch
        }

        loadMovies()
    }

    private fun loadMovies() = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "loadMovies")

        loadNowPlaying()
        loadPopular()
        loadTopRated()
        loadUpcoming()
    }

    private fun loadImageConfiguration() = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "loadImageConfiguration")
        configRepository
            .loadImageConfiguration()
            .onSuccess { config ->
                Log.d(TAG, "loadImageConfiguration: $config")
                imageConfiguration = config
                loadMovies()
            }
            .onError { error ->
                Log.e(TAG, "loadImageConfiguration: $error")
                _events.send(ExploreEvents.Error(error))
            }
    }

    private fun loadNowPlaying() = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "loadNowPlaying")

        discoverRepository
            .getNowPlaying(imageConfiguration)
            .onSuccess { movies ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        nowPlayingMovies = movies
                    )
                }
            }
            .onError {
                Log.e(TAG, "loadNowPlaying: $it")
                _events.send(ExploreEvents.Error(it))
            }
    }

    private fun loadPopular() = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "loadPopular")

        discoverRepository
            .getPopular(imageConfiguration)
            .onSuccess { movies ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        popular = movies
                    )
                }
            }
            .onError {
                Log.e(TAG, "loadPopular: $it")
                _events.send(ExploreEvents.Error(it))
            }
    }

    private fun loadTopRated() = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "loadTopRated")

        discoverRepository
            .getTopRated(imageConfiguration)
            .onSuccess { movies ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        topRated = movies
                    )
                }
            }
            .onError {
                Log.e(TAG, "loadTopRated: $it")
                _events.send(ExploreEvents.Error(it))
            }
    }

    private fun loadUpcoming() = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "loadUpcoming")

        discoverRepository
            .getUpcoming(imageConfiguration)
            .onSuccess { movies ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        upcoming = movies
                    )
                }
            }
            .onError {
                Log.e(TAG, "loadUpcoming: $it")
                _events.send(ExploreEvents.Error(it))
            }
    }
}