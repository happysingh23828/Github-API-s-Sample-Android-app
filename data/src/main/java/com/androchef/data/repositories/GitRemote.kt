package com.androchef.data.repositories

import com.androchef.data.models.PullRequestEntity
import com.androchef.data.models.SingleRepoEntity
import com.androchef.data.models.request.PullRequestGetBody
import io.reactivex.Single

interface GitRemote {
    fun getUserGitRepositories(username: String): Single<List<SingleRepoEntity>>

    fun getPullRequestList(pullRequestGetBody: PullRequestGetBody): Single<List<PullRequestEntity>>
}