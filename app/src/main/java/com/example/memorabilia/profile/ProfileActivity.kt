package com.example.memorabilia.profile

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.memorabilia.R
import com.example.memorabilia.login.LoginActivity
import com.example.memorabilia.theme.SwitchThemeActivity
import com.google.android.material.button.MaterialButton

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        val logoutProfile = findViewById<MaterialButton>(R.id.LogoutButton)
        logoutProfile.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val themeButton = findViewById<MaterialButton>(R.id.ThemeButton)
        themeButton.setOnClickListener {
            val intent = Intent(this, SwitchThemeActivity::class.java)
            startActivity(intent)

        }
    }
}