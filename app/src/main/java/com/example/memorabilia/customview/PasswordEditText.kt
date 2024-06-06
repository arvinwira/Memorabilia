package com.example.memorabilia.customview

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class PasswordEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {
    init {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val passwordPattern = "^(?=.*[A-Z])(?=.*\\d).{8,}$"
                if (!s.matches(passwordPattern.toRegex())) {
                    error = "Password must contain at least 8 characters, including an uppercase letter and a number"
                } else {
                    error = null
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }
}
