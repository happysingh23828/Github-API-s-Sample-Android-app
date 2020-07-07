package com.androchef.data.repositories

import com.androchef.data.models.PullRequestEntity
import com.androchef.data.models.SingleRepoEntity
import io.reactivex.Single

interface GitDataStore {
    fun getUserGitRepositories(username: String): Single<List<SingleRepoEntity>>

    fun getPullRequestList(
        username: String,
        repoName: String,
        state: String
    ): Single<List<PullRequestEntity>>
}