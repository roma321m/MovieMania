package com.roman.moviemania.explore.presentation

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roman.moviemania.app.launcher.ActivityLauncher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExploreViewModel(
    private val activityLauncher: ActivityLauncher
) : ViewModel() {

    companion object {
        private const val TAG = "ExploreViewModel"
    }

    private val _uiState = MutableStateFlow(ExploreUiState())
    val uiState = _uiState
        .onStart {
            // todo: load data
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
            it.copy(searchActive = false)
        }
    }

    private fun onSearch() {
        Log.d(TAG, "onSearch")

        _uiState.update {
            it.copy(searchActive = false)
        }
        // todo
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
}