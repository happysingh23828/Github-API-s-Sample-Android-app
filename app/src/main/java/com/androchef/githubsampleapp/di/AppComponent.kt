package com.androchef.githubsampleapp.di

import android.app.Application
import com.androchef.githubsampleapp.di.modules.ApplicationModule
import com.androchef.githubsampleapp.di.modules.DomainModule
import com.androchef.githubsampleapp.di.modules.RemoteModule
import com.androchef.githubsampleapp.di.modules.UIModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        DomainModule::class,
        RemoteModule::class,
        UIModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
    
}