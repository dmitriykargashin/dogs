package com.cardamon.dogs.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class SharedPreferencesHelper {

    companion object {
        // @Volatile - Writes to this property are immediately visible to other threads

        private var prefs: SharedPreferences? = null
        private const val PREF_TIME = "Pref time"

        @Volatile
        private var instance: SharedPreferencesHelper? = null
        private val LOCK = Any()


        operator fun invoke(context: Context): SharedPreferencesHelper =
        // Already instantiated? - return the instance
            // Otherwise instantiate in a thread-safe manner
            instance ?: synchronized(LOCK) {
                instance
                    ?: buildHelper(context).also {
                        instance = it
                    }
            }

        private fun buildHelper(context: Context): SharedPreferencesHelper {
            prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return SharedPreferencesHelper()
        }

    }

    fun saveUpdateTime(time: Long) {
        prefs?.edit(commit = true) {
            putLong(PREF_TIME, time)
        }

    }

    fun getUpdateTime()= prefs?.getLong(PREF_TIME,0)
}