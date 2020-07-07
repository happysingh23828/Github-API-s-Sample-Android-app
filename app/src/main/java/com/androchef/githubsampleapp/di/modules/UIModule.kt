package com.androchef.githubsampleapp.di.modules

import com.androchef.domain.executor.PostExecutionThread
import com.androchef.githubsampleapp.UiThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UIModule {

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }
}
