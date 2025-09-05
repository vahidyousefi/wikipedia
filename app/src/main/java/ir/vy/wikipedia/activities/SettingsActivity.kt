package ir.vy.wikipedia.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.drawable.RippleDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import androidx.core.graphics.drawable.toDrawable
import androidx.core.net.toUri
import androidx.core.view.WindowCompat
import com.google.android.material.snackbar.Snackbar
import ir.vy.wikipedia.R
import ir.vy.wikipedia.databinding.ActivitySettingsBinding
import ir.vy.wikipedia.function.snackBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        // set first change theme
        val sharedDarkMode = getSharedPreferences("DarkModePrefs", MODE_PRIVATE)
        val isDarkModeEnabled = sharedDarkMode.getBoolean("isDarkModeEnabled", false)
        val followThemeSystem = sharedDarkMode.getBoolean("FollowTheme", false)

        // set the initial state of the switch based on the current theme
        AppCompatDelegate.setDefaultNightMode(
            if (followThemeSystem) AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            else if (isDarkModeEnabled) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
        // do`nt margin from top when change theme
        WindowCompat.setDecorFitsSystemWindows(window, false)

        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        // auto change versionName from gradle -------------------------------------
        // get info
        val packageInfo = this.packageManager.getPackageInfo(this.packageName, 0)
        // get versionName
        val versionName = packageInfo.versionName
        // show versionName
        binding.versionNameApp.text = "Version $versionName"
        //--------------------------------------------------------------------------

        binding.backArrow.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)
//            finish()
        }
        // set ripple color for any
//        setRippleEffect(binding.backArrow)

        // go to edit Profile setting
        binding.frameLayoutProfile.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)
//            finish()
        }

        // ------------ Switch actions ---------------
        // - Notification
        val sharedNotif = getSharedPreferences("SwitchNotification", MODE_PRIVATE)
        // set First change
        binding.swNotif.isChecked = sharedNotif.getBoolean("isNotificationEnabled", false)

        binding.swNotif.setOnCheckedChangeListener { _, isChecked ->
            sharedNotif.edit {
                putBoolean("isNotificationEnabled", isChecked)
                apply()
            }
            snackBarSwitch(if (isChecked) "Notification active" else "Notification paused")
        }

        // - Switch Dark Mode
        /**
         * To ensure that the theme is applied correctly to the application and does not use the default theme
         * when starting and does not change the theme later,
         * I wrote the -sharedPreferences- code before the setContentView function.
         */
        // set first state switch
        binding.swDarkMode.isChecked = if (followThemeSystem) {
            resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
        } else {
            isDarkModeEnabled
        }
        // update icon in switch
        updateSwitchThumbIcon(binding.swDarkMode.isChecked)

        binding.swDarkMode.setOnCheckedChangeListener { _, isChecked ->

            // save Change
            sharedDarkMode.edit {
                putBoolean("isDarkModeEnabled", isChecked)
                putBoolean("FollowTheme", false)
            }

            if (isChecked) {
                snackBarSwitch("DarkMode on")
                // Delayed execution 1 (Old way)
                Handler(Looper.getMainLooper()).postDelayed({
                    // Dark mode on
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }, 700)
            } else {
                snackBarSwitch("DarkMode off")
                // Delayed execution 2 (New way)
                CoroutineScope(Dispatchers.Main).launch {
                    delay(700)
                    // Dark mode off
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
            updateSwitchThumbIcon(isChecked)
        }
        //-----------------------------------------------------------------------------------------

        val urls = listOf(
            "https://m.mediawiki.org/wiki/Wikimedia_Apps/Android_FAQ",
            "https://foundation.m.wikimedia.org/wiki/Policy:Terms_of_Use"
        )
        // go to web FAQ
        binding.FAQ.setOnClickListener {
            openWeb(urls[0])
        }
        // go to web Terms.
        binding.termsOfService.setOnClickListener {
            openWeb(urls[1])
        }
    }

    private fun openWeb(url: String) {

        val goToWeb = Intent(Intent.ACTION_VIEW, url.toUri())
        startActivity(goToWeb)
    }

    // customize snackBar for Switch
    private fun snackBarSwitch(textTitle: String) {
        this.snackBar(
            binding.root,
            textTitle,
            null,
            null,
            R.color.red,
            R.color.white_night,
            R.color.blue_dark,
            Snackbar.LENGTH_SHORT,
            android.view.Gravity.BOTTOM,
            120
        )
    }

    // ThumbIcon in switch
    private fun updateSwitchThumbIcon(isDarkMode: Boolean) {
        val thumbIcon = if (isDarkMode) {
            ContextCompat.getDrawable(this, R.drawable.ic_dark_mood)
        } else {
            ContextCompat.getDrawable(this, R.drawable.ic_light_mode)
        }
        binding.swDarkMode.thumbIconDrawable = thumbIcon
    }

    // Ripple effect color
    private fun setRippleEffect(view: View) {

        val rippleColor =
            ColorStateList.valueOf(ContextCompat.getColor(view.context, R.color.blue_ripple))
        val rippleDrawable =
            RippleDrawable(rippleColor, null, android.graphics.Color.WHITE.toDrawable())

        view.foreground = rippleDrawable
        view.isClickable = true
        view.isFocusable = true
    }
}