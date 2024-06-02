package com.example.memorabilia.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import com.example.memorabilia.R
import com.example.memorabilia.ViewModelFactory
import com.example.memorabilia.currentlyreading.CurrentlyReadingActivity
import com.example.memorabilia.finishedreading.FinishedReadingActivity
import com.example.memorabilia.settings.SettingsActivity
import com.example.memorabilia.search.SearchActivity
import com.example.memorabilia.theme.ThemeViewModel
import com.example.memorabilia.wanttoread.WantToReadActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import androidx.datastore.preferences.core.Preferences

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {

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
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.homenav
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homenav -> {
                    true
                }
                R.id.searchnav -> {
                    startActivity(Intent(applicationContext, SearchActivity::class.java))
                    overridePendingTransition(0, 0)
                    true                }

                R.id.profilenav -> {
                    startActivity(Intent(applicationContext, SettingsActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                else -> false
            }
        }

        val buttonCurrentlyReading = findViewById<MaterialButton>(R.id.button_currently_reading)
        buttonCurrentlyReading.setOnClickListener {
            val intent = Intent(this, CurrentlyReadingActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        val buttonWantToRead = findViewById<MaterialButton>(R.id.button_want_to_read)
        buttonWantToRead.setOnClickListener {
            val intent = Intent(this, WantToReadActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

        }

        val buttonFinished = findViewById<MaterialButton>(R.id.button_finished_reading)
        buttonFinished.setOnClickListener {
            val intent = Intent(this, FinishedReadingActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)

        }


    }
}