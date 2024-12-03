package com.roman.moviemania.app.launcher

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log

class ActivityLauncher(
    private val context: Context
) {

    companion object {
        private const val TAG = "ActivityLauncher"
        private const val TC_URL = "https://sites.google.com/view/movie-mania-tc/%D7%91%D7%99%D7%AA"
        private const val PP_URL = "https://sites.google.com/view/movie-mania-pp/%D7%91%D7%99%D7%AA"
    }

    fun launchPrivacyPolicy() {
        Log.d(TAG, "lunchPrivacyPolicy")
        launch(PP_URL)
    }

    fun launchTermsAndConditions() {
        Log.d(TAG, "lunchTermsAndConditions")
        launch(TC_URL)
    }

    private fun launch(url: String) {
        val urlIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(urlIntent)
    }
}