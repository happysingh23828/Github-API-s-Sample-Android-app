package com.androchef.githubsampleapp.di.modules

import com.androchef.data.repositories.GitRemote
import com.androchef.githubsampleapp.BuildConfig
import com.androchef.remote.GitRemoteRepositoryImp
import com.androchef.remote.services.GitServiceFactory
import com.androchef.remote.services.github.GithubService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteModule {

    @Provides
    @Singleton
    fun provideGitRemote(gitRemoteImp: GitRemoteRepositoryImp): GitRemote {
        return gitRemoteImp
    }

    @Provides
    @Singleton
    fun provideGitService(): GithubService {
        return GitServiceFactory.create(
            BuildConfig.DEBUG,
            BuildConfig.BASE_URL
        )
    }
}
