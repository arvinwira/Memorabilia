package com.example.memorabilia.settings

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.memorabilia.R

class AppGuideActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_guide) // replace with your actual layout resource ID

        val view = findViewById<View>(R.id.sVAppGuide) // replace with your actual view ID
        if (view != null) {
            ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
                // your code here
                insets
            }
        }
    }
}
