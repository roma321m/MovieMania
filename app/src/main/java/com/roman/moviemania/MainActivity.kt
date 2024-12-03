package com.roman.moviemania

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.roman.moviemania.app.navigation.AppNavigation
import com.roman.moviemania.app.navigation.Navigator
import com.roman.moviemania.app.presentation.AppAction
import com.roman.moviemania.app.presentation.AppViewModel
import com.roman.moviemania.app.ui.theme.MovieManiaTheme
import com.roman.moviemania.core.presentation.components.bottombar.BottomBarView
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private val navigator: Navigator by inject()
    private val appViewModel: AppViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        enableEdgeToEdge()

        setContent {
            MovieManiaTheme {
                AppNavigation(
                    modifier = Modifier.fillMaxSize(),
                    navigator = navigator,
                ) {
                    BottomBarView(
                        items = appViewModel.bottomNavBarItems,
                        selectedItemIndex = appViewModel.currentScreenIndex,
                        onNavItemClick = {
                            appViewModel.onAction(AppAction.OnBottomNavItemClick(it))
                        },
                    )
                }
            }
        }
    }

}