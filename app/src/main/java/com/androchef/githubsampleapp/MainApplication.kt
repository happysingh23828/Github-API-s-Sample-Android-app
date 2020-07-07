package com.androchef.githubsampleapp

import android.app.Application
import com.androchef.githubsampleapp.di.AppComponent
import com.androchef.githubsampleapp.di.DaggerAppComponent

class MainApplication : Application() {

    init {
        instance = this
    }

    var appComponent: AppComponent = DaggerAppComponent.builder().application(this).build()

    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        var instance: MainApplication? = null
        fun appComponent(): AppComponent {
            return instance!!.appComponent
        }
    }
}
