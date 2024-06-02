package com.example.memorabilia.settings

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.memorabilia.R
import com.example.memorabilia.main.MainActivity
import com.example.memorabilia.search.SearchActivity
import com.example.memorabilia.theme.SwitchThemeActivity
import com.example.memorabilia.welcome.WelcomeActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.selectedItemId = R.id.profilenav

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homenav -> {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.searchnav -> {
                    startActivity(Intent(applicationContext, SearchActivity::class.java))
                    overridePendingTransition(0, 0)
                    true                }

                R.id.profilenav -> {
                    true
                }
                else -> false
            }
        }

        val logoutProfile = findViewById<MaterialButton>(R.id.LogoutButton)
        logoutProfile.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        val themeButton = findViewById<MaterialButton>(R.id.ThemeButton)
        themeButton.setOnClickListener {
            val intent = Intent(this, SwitchThemeActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

        }
    }
}