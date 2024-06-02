package com.example.memorabilia

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.memorabilia.theme.ThemePreferences
import com.example.memorabilia.theme.ThemeViewModel

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class ViewModelFactory(private val dataStore: DataStore<Preferences>) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThemeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ThemeViewModel(ThemePreferences.getInstance(dataStore)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
