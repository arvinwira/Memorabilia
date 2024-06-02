package com.example.memorabilia.theme

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.memorabilia.R
import com.example.memorabilia.ViewModelFactory
import com.google.android.material.switchmaterial.SwitchMaterial

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SwitchThemeActivity : AppCompatActivity() {

    private lateinit var switchTheme: SwitchMaterial
    private val themeViewModel: ThemeViewModel by viewModels {
        ViewModelFactory(dataStore)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        themeViewModel.getThemeSettings().observe(this) { isDarkModeActive ->
            AppCompatDelegate.setDefaultNightMode(
                if (isDarkModeActive) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_switch_theme)

        switchTheme = findViewById(R.id.switch_theme)

        themeViewModel.getThemeSettings().observe(this) { isDarkModeActive ->
            switchTheme.isChecked = isDarkModeActive
        }

        switchTheme.setOnCheckedChangeListener { _, isChecked ->
            themeViewModel.saveThemeSetting(isChecked)
        }
    }
}
