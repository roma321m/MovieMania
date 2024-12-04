package com.roman.moviemania.app.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roman.moviemania.app.navigation.Navigator
import com.roman.moviemania.app.navigation.bottombar.NavigationBarDefaults
import com.roman.moviemania.app.navigation.routes.Route
import com.roman.moviemania.core.domain.repository.ConfigurationRepository
import com.roman.moviemania.core.domain.utils.onError
import com.roman.moviemania.core.domain.utils.onSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AppViewModel(
    private val navigator: Navigator,
    private val configurationRepository: ConfigurationRepository,
) : ViewModel() {

    companion object {
        private const val TAG = "AppViewModel"
    }

    val bottomNavBarItems = NavigationBarDefaults.items

    var isLoading by mutableStateOf(true)
        private set

    var currentScreenIndex by mutableIntStateOf(0)
        private set

    fun onLifecycleEvent(event: Lifecycle.Event) {
        Log.d(TAG, "onLifecycleEvent: ${event.name}")

        when (event) {
            Lifecycle.Event.ON_CREATE -> loadImageConfiguration()
            else -> Unit
        }
    }

    fun onAction(action: AppAction) {
        Log.d(TAG, "onAction: $action")

        when (action) {
            is AppAction.OnBottomNavItemClick -> onBottomNavItemClick(action.index)
            is AppAction.OnNavigationChange -> onNavChange(action.route)
        }
    }

    private fun onBottomNavItemClick(index: Int) {
        if (index == currentScreenIndex) return

        Log.d(TAG, "onBottomNavItemClick: $index")

        val route = bottomNavBarItems[index].route
        currentScreenIndex = index
        viewModelScope.launch {
            navigator.navigate(route)
        }
    }

    private fun onNavChange(route: Route) {
        Log.d(TAG, "onNavChange: $route")
        currentScreenIndex = bottomNavBarItems.indexOfFirst {
            it.route == route
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
                .loadImageConfiguration()
                .onSuccess { imageConfiguration ->
                    Log.d(TAG, "loadImageConfiguration: $imageConfiguration")
                    delay(300)
                    isLoading = false
                }
                .onError { error ->
                    Log.e(TAG, "loadImageConfiguration: $error")
                    isLoading = false
                }
        }
    }

}