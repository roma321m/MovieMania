package com.roman.moviemania.app.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roman.moviemania.app.navigation.Navigator
import com.roman.moviemania.app.navigation.bottombar.NavigationBarDefaults
import com.roman.moviemania.core.domain.repository.ConfigurationRepository
import com.roman.moviemania.core.domain.utils.onError
import com.roman.moviemania.core.domain.utils.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppViewModel(
    private val navigator: Navigator,
    private val configurationRepository: ConfigurationRepository,
) : ViewModel() {

    companion object {
        private const val TAG = "AppViewModel"
    }

    val bottomNavBarItems = NavigationBarDefaults.items
    private val startScreenIndex = bottomNavBarItems.indexOfFirst {
        it.route == navigator.startDestination // fixme - bug
    }

    var isLoading by mutableStateOf(true)
        private set

    private val _uiState = MutableStateFlow(AppUiState(currentScreenIndex = startScreenIndex))
    val uiState = _uiState
        .onStart {
            loadImageConfiguration()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            AppUiState(currentScreenIndex = startScreenIndex)
        )

    fun onAction(action: AppAction) {
        Log.d(TAG, "onAction: $action")

        when (action) {
            is AppAction.OnBottomNavItemClick -> onBottomNavItemClick(action.index)
            AppAction.LoadConfig -> loadImageConfiguration()
        }
    }

    private fun onBottomNavItemClick(index: Int) {
        Log.d(TAG, "onBottomNavItemClick: $index")

        val route = bottomNavBarItems[index].route
        viewModelScope.launch {
            navigator.navigate(route)
        }.also {
            _uiState.update {
                it.copy(currentScreenIndex = index)
            }
        }
    }

    private fun loadImageConfiguration() {
        Log.d(TAG, "loadImageConfiguration")
        if (configurationRepository.cachedImageConfiguration != null) {
            Log.d(TAG, "loadImageConfiguration: cached")
            isLoading = false
            return
        }

        viewModelScope.launch {
            Log.d(TAG, "loadImageConfiguration: loading")
            configurationRepository
                .getImageConfiguration()
                .onSuccess { imageConfiguration ->
                    isLoading = false
                    Log.d(TAG, "loadImageConfiguration: $imageConfiguration")
                }
                .onError { error ->
                    isLoading = false
                    Log.e(TAG, "loadImageConfiguration: $error")
                }
        }
    }

}