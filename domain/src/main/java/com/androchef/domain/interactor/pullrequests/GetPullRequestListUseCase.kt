package com.androchef.domain.interactor.pullrequests

import com.androchef.domain.GitRepository
import com.androchef.domain.executor.PostExecutionThread
import com.androchef.domain.executor.ThreadExecutor
import com.androchef.domain.interactor.core.SingleUseCase
import com.androchef.domain.models.pullrequest.PullRequest
import io.reactivex.Single

class GetPullRequestListUseCase constructor(
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
            getPullRequestState(requestBody.state)
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
        val state: String
    )

    private fun getPullRequestState(state: String): PullRequest.State {
        return when (state) {
            "open" -> PullRequest.State.OPEN
            "closed" -> PullRequest.State.CLOSED
            else -> PullRequest.State.ALL
        }
    }
}