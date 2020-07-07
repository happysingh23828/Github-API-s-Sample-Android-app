package com.androchef.domain.interactor.user_repos

import com.androchef.domain.GitRepository
import com.androchef.domain.executor.PostExecutionThread
import com.androchef.domain.executor.ThreadExecutor
import com.androchef.domain.interactor.core.SingleUseCase
import com.androchef.domain.models.repo.GitSingleRepo
import io.reactivex.Single
import javax.inject.Inject

class GetUserRepositoryListUseCase @Inject constructor(
    private val gitRepository: GitRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<GetUserRepositoryListUseCase.Params, List<GitSingleRepo>>(
    threadExecutor,
    postExecutionThread
) {

    override fun buildUseCaseObservable(requestValues: Params?): Single<List<GitSingleRepo>> {
        return gitRepository.getUserGitRepositories(requestValues!!.username)
    }

    /**
     * [username] to fetch user's repositories
     */
    data class Params(
        val username: String
    )
}