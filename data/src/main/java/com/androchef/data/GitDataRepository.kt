package com.androchef.data

import com.androchef.data.mapper.pull_request.PullRequestMapper
import com.androchef.data.mapper.single_repo.SingleRepoMapper
import com.androchef.data.store.GitDataStoreFactory
import com.androchef.domain.GitRepository
import com.androchef.domain.models.pullrequest.PullRequest
import com.androchef.domain.models.repo.GitSingleRepo
import io.reactivex.Single
import javax.inject.Inject

class GitDataRepository @Inject constructor(
    private val pullRequestMapper: PullRequestMapper,
    private val singleRepoMapper: SingleRepoMapper,
    private val gitDataStoreFactory: GitDataStoreFactory
) : GitRepository {

    override fun getUserGitRepositories(username: String): Single<List<GitSingleRepo>> {
        return gitDataStoreFactory.getRemoteDataStore()
            .getUserGitRepositories(username).map { listOfGitSingleRepos ->
                listOfGitSingleRepos.map { singleRepoMapper.mapFromEntity(it) }
            }
    }

    override fun getPullRequestList(
        username: String,
        repoName: String,
        state: PullRequest.State
    ): Single<List<PullRequest>> {
        return gitDataStoreFactory.getRemoteDataStore()
            .getPullRequestList(username, repoName, getPullRequestState(state))
            .map { listOfPullRequest ->
                listOfPullRequest.map { pullRequestMapper.mapFromEntity(it) }
            }
    }

    private fun getPullRequestState(state: PullRequest.State): String {
        return when (state) {
            PullRequest.State.OPEN -> "open"
            PullRequest.State.CLOSED -> "closed"
            else -> "all"
        }
    }
}