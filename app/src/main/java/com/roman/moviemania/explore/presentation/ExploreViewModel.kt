package com.roman.moviemania.explore.presentation

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn

class ExploreViewModel() : ViewModel() {

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
        }
    }

    private fun onSearchFabClick() {
        Log.d(TAG, "onSearchFabClick")
    }

}