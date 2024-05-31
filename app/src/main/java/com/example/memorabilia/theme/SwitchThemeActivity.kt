package com.example.memorabilia.theme

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.memorabilia.R


class SwitchThemeActivity : AppCompatActivity() {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var switchTheme: Switch
    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        private const val PREFS_NAME = "prefs"
        private const val PREF_DARK_THEME = "dark_theme"
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val useDarkTheme = sharedPreferences.getBoolean(PREF_DARK_THEME, false)

        // Set the theme based on the user's preference
        setTheme(if (useDarkTheme) R.style.Base_Theme_Memorabilia_Dark else R.style.Base_Theme_Memorabilia)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_switch_theme)

        switchTheme = findViewById(R.id.switch_theme)
        switchTheme.isChecked = useDarkTheme

        switchTheme.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // If the switch is checked, set the theme to dark mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPreferences.edit().putBoolean(PREF_DARK_THEME, true).apply()
            } else {
                // If the switch is not checked, set the theme to light mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPreferences.edit().putBoolean(PREF_DARK_THEME, false).apply()
            }
            recreate()
        }
    }
}