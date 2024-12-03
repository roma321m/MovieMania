package com.roman.moviemania.app.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roman.moviemania.app.navigation.Navigator
import com.roman.moviemania.app.navigation.bottombar.NavigationBarDefaults
import kotlinx.coroutines.launch

class AppViewModel(
    private val navigator: Navigator
) : ViewModel() {

    companion object {
        private const val TAG = "AppViewModel"
    }

    val bottomNavBarItems = NavigationBarDefaults.items
    private val startScreenIndex = bottomNavBarItems.indexOfFirst {
        it.route == navigator.startDestination
    }

    var isLoading by mutableStateOf(false)
        private set

    var currentScreenIndex by mutableIntStateOf(startScreenIndex)
        private set

    fun onAction(action: AppAction) {
        Log.d(TAG, "onAction: $action")

        when (action) {
            is AppAction.OnBottomNavItemClick -> onBottomNavItemClick(action.index)
        }
    }

    private fun onBottomNavItemClick(index: Int) {
        Log.d(TAG, "onBottomNavItemClick: $index")

        val route = bottomNavBarItems[index].route
        viewModelScope.launch {
            navigator.navigate(route)
        }.also {
            currentScreenIndex = index
        }
    }

}