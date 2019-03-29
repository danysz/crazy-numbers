package com.app.dleelk4apps.crazynumbers.App

import android.app.Application
import com.app.dleelk4apps.crazynumbers.utils.GameSharedPreference

/**
 * Created by OmarAyed on 01/01/2018.
 */

class CrazyNumbersApplication : Application() {

    companion object {
        lateinit var appInstance: CrazyNumbersApplication
            private set
    }

    private lateinit var sharedPreferenceInstance: GameSharedPreference

    override fun onCreate() {
        super.onCreate()
        appInstance = this
        sharedPreferenceInstance  = GameSharedPreference(applicationContext)
    }

     fun getPrefsInstance(): GameSharedPreference {
        return sharedPreferenceInstance
    }

}