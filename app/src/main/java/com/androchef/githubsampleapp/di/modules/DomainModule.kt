package com.androchef.githubsampleapp.di.modules

import com.androchef.data.GitDataRepository
import com.androchef.data.executor.JobExecutor
import com.androchef.domain.GitRepository
import com.androchef.domain.executor.ThreadExecutor
import com.androchef.domain.interactor.core.SingleUseCase
import com.androchef.domain.interactor.pullrequests.GetPullRequestListUseCase
import com.androchef.domain.interactor.user_repos.GetUserRepositoryListUseCase
import com.androchef.domain.models.pullrequest.PullRequest
import com.androchef.domain.models.repo.GitSingleRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun provideGitRepository(gitDataRepository: GitDataRepository): GitRepository {
        return gitDataRepository
    }

    @Provides
    @Singleton
    fun provideGetUserRepositoriesUseCase(
        getUserRepositoryListUseCase: GetUserRepositoryListUseCase
    ): SingleUseCase<GetUserRepositoryListUseCase.Params, List<GitSingleRepo>> {
        return getUserRepositoryListUseCase
    }

    @Provides
    @Singleton
    fun provideGetPullRequestListUseCase(
        getPullRequestListUseCase: GetPullRequestListUseCase
    ): SingleUseCase<GetPullRequestListUseCase.Params, List<PullRequest>> {
        return getPullRequestListUseCase
    }

    @Provides
    @Singleton
    fun bindThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }
}
