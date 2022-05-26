package com.will_d.ex32hilt

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrefDataStoreManager @Inject constructor(
    @ApplicationContext val context : Context) {
    val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "setting_pref")
    val EMAPLE_COUNTER = intPreferencesKey("example_counter")
    val aa = context.dataStore.data
        .map {
            it[EMAPLE_COUNTER] ?: 0
        }

    suspend fun bbb() {
        context.dataStore.edit {
            val value = it[EMAPLE_COUNTER] ?: 0
            it[EMAPLE_COUNTER] = value + 1
        }
    }
}