package com.example.memorabilia.theme

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.example.memorabilia.R
import com.example.memorabilia.main.MainActivity
import com.example.memorabilia.profile.ProfileActivity


class SwitchThemeActivity : AppCompatActivity() {

    private lateinit var switchTheme: Switch
    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        private const val PREFS_NAME = "prefs"
        private const val PREF_DARK_THEME = "dark_theme"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val useDarkTheme = sharedPreferences.getBoolean(PREF_DARK_THEME, false)

        // Set the theme based on the user's preference
        setTheme(if (useDarkTheme) R.style.Base_Theme_Memorabilia_night else R.style.Base_Theme_Memorabilia)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_switch_theme)

        switchTheme = findViewById(R.id.switch_theme)
        switchTheme.isChecked = useDarkTheme

        switchTheme.setOnCheckedChangeListener { _, isChecked ->
            // Update the theme preference
            sharedPreferences.edit().putBoolean(PREF_DARK_THEME, isChecked).apply()

            // Restart the application to apply the new theme
            val intent = Intent(applicationContext, ProfileActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }
}