package com.androchef.domain.interactor.pullrequests

import com.androchef.domain.GitRepository
import com.androchef.domain.executor.PostExecutionThread
import com.androchef.domain.executor.ThreadExecutor
import com.androchef.domain.interactor.core.SingleUseCase
import com.androchef.domain.models.pullrequest.PullRequest
import io.reactivex.Single
import javax.inject.Inject

class GetPullRequestListUseCase @Inject constructor(
    private val gitRepository: GitRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<GetPullRequestListUseCase.Params, List<PullRequest>>(
    threadExecutor,
    postExecutionThread
) {

    override fun buildUseCaseObservable(requestValues: Params?): Single<List<PullRequest>> {
        val requestBody = requestValues!!
        return gitRepository.getPullRequestList(
            requestBody.username,
            requestBody.repositoryName,
            requestBody.state
        )
    }

    /**
     * [username] git username,
     * [repositoryName] repository's name to fetch its pull request.
     * [state] to filter Pull request's state [open, closed, all]
     */
    data class Params(
        val username: String,
        val repositoryName: String,
        val state: PullRequest.State
    )
}