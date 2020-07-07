package com.androchef.data.store

import com.androchef.data.models.PullRequestEntity
import com.androchef.data.models.SingleRepoEntity
import com.androchef.data.models.request.PullRequestGetBody
import com.androchef.data.repositories.GitDataStore
import com.androchef.data.repositories.GitRemote
import io.reactivex.Single
import javax.inject.Inject

class GitRemoteDataStore @Inject constructor(
    private val gitRemote: GitRemote
) : GitDataStore {


    override fun getUserGitRepositories(username: String): Single<List<SingleRepoEntity>> {
        return gitRemote.getUserGitRepositories(username)
    }

    override fun getPullRequestList(
        username: String,
        repoName: String,
        state: String
    ): Single<List<PullRequestEntity>> {
        return gitRemote.getPullRequestList(PullRequestGetBody(username, repoName, state))
    }
}