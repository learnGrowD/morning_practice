package com.will_d.ex31datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class PrefDataStoreManager(val context: Context) {
//    val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "setting")
    val Context.datastore : DataStore<Preferences> by preferencesDataStore(name = "settings_pref")

    val EXAMPLE_COUNTER = intPreferencesKey("example_counter") // Key은 example_counter이고, Int형 값임
    val exampleCounterFlow  = context.datastore.data
        .map {
            it[EXAMPLE_COUNTER] ?: 0
        }

    suspend fun incrementCounter() {
        context.datastore.edit { settings ->
            val currentCounterValue = settings[EXAMPLE_COUNTER] ?: 0
            settings[EXAMPLE_COUNTER] = currentCounterValue + 1
        }
    }




}