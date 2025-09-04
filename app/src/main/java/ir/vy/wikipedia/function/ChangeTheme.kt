package ir.vy.wikipedia.function

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class ChangeTheme : Application() {
    override fun onCreate() {
        super.onCreate()

        // set first change
        val sharedDarkMode = getSharedPreferences("DarkModePrefs", MODE_PRIVATE)
        val isDarkModeEnabled = sharedDarkMode.getBoolean("isDarkModeEnabled", false)
        val followThemeSystem = sharedDarkMode.getBoolean("FollowTheme", false)


        // set the initial state of the switch based on the current theme
        AppCompatDelegate.setDefaultNightMode(
            if (followThemeSystem) AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            else if (isDarkModeEnabled) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
    }
}