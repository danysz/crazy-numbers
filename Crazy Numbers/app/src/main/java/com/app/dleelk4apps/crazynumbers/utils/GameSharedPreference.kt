package com.app.dleelk4apps.crazynumbers.utils

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import android.preference.PreferenceManager
import com.app.dleelk4apps.crazynumbers.data.GamerData
import com.google.gson.Gson


/**
 * Created by OmarAyed on 04/01/2018.
 */
class GameSharedPreference(context: Context) {

    private val PREFS_FILENAME = "com.app.dleelk4apps.crazynumbers.prefs"
    private var context: Context? = null
    private var prefs: SharedPreferences

    init {
        this.context = context
        prefs = context.getSharedPreferences(PREFS_FILENAME, 0)
    }

    fun getString(key: String): String? {
        var value: String? = null
        if (prefs != null) {
            value = prefs.getString(key, null)
        }
        return value
    }

    fun setString(key: String, value: String): Boolean {
        if (prefs != null) {
            val editor = prefs.edit()
            editor.putString(key, value)
            return editor.commit()
        }
        return false
    }

    fun getInteger(key: String): Int {
        var value = 1
        if (prefs != null) {
            value = prefs.getInt(key, 1)
        }
        return value
    }

    fun setInteger(key: String, value: Int): Boolean {
        if (prefs != null) {
            val editor = prefs.edit()
            editor.putInt(key, value)
            return editor.commit()
        }
        return false
    }

    fun getBoolean(key: String): Boolean {
        var value = true
        if (prefs != null) {
            value = prefs.getBoolean(key, true)
        }
        return value
    }


    fun setBoolean(key: String, value: Boolean): Boolean {
        if (prefs != null) {
            val editor = prefs.edit()
            editor.putBoolean(key, value)
            return editor.commit()
        }
        return false
    }

    fun setGamerData(gameData: GamerData): Boolean {
        val gson = Gson()
        val json = gson.toJson(gameData)
        return setString(Constants.GAME_GAMER_DATA, json)
    }

    fun getGamerData(): GamerData {
        var gamerData = GamerData()
        if (prefs != null) {
            val gson = Gson()
            val json = prefs.getString(Constants.GAME_GAMER_DATA, "")
            if (json != null && !json.isEmpty()) {
                gamerData = gson.fromJson<Any>(json, GamerData::class.java) as GamerData
            }

        }
        return gamerData
    }
}