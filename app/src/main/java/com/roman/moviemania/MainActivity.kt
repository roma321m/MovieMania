package com.roman.moviemania

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.roman.moviemania.app.navigation.AppNavigation
import com.roman.moviemania.app.navigation.Navigator
import com.roman.moviemania.app.ui.theme.MovieManiaTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private val navigator: Navigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        enableEdgeToEdge()

        setContent {
            MovieManiaTheme {
                AppNavigation(
                    navigator = navigator,
                )
            }
        }
    }

}