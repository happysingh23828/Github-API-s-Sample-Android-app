package com.androchef.domain

import com.androchef.domain.models.pullrequest.PullRequest
import com.androchef.domain.models.repo.GitSingleRepo
import io.reactivex.Single

interface GitRepository {

    fun getUserGitRepositories(username: String): Single<List<GitSingleRepo>>

    fun getPullRequestList(
        prId: Int,
        state: PullRequest.State = PullRequest.State.ALL
    ): Single<List<PullRequest>>
}