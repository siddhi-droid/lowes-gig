package com.siddhi.lowesgig.data.local.prefs

import android.content.SharedPreferences
import com.siddhi.lowesgig.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsPreferences @Inject constructor(private val prefs: SharedPreferences) {
    companion object {
        val PREF_FILE_NAME = R.string.prefName//PrefName
    }

    fun clear() {
        prefs.edit().clear().apply()
    }

    fun remove(key: String?) {
        prefs.edit().remove(key).apply()
    }

    fun putData(key: String?, data: String?) {
        prefs.edit().putString(key, data).apply()
    }

    fun putDataForInt(key: String?, data: Int?) {
        if (data != null) {
            prefs.edit().putInt(key, data).apply()
        }
    }

    fun getDataForInt(key: String?): Int {
        return prefs.getInt(key, 0)
    }

    fun putDataForBoolean(key: String?, data: Boolean?) {
        if (data != null) {
            prefs.edit().putBoolean(key, data).apply()
        }
    }

    fun getDataForBoolean(key: String?): Boolean? {
        return prefs.getBoolean(key, false)
    }
}