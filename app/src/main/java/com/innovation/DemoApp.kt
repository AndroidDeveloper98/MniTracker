package com.innovation

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltAndroidApp
class DemoApp : Application() {

    companion object{
        private var instance: DemoApp? = null
        /**
         * This function provide myApp instance
         *
         * @return instance
         */
        fun getInstance(): DemoApp? {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

}