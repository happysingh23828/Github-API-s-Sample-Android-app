package com.androchef.remote

import com.androchef.data.models.PullRequestEntity
import com.androchef.data.models.SingleRepoEntity
import com.androchef.data.models.request.PullRequestGetBody
import com.androchef.data.repositories.GitRemote
import com.androchef.remote.mappers.github.pull_request.PullRequestEntityMapper
import com.androchef.remote.mappers.github.repos.SingleRepoEntityMapper
import com.androchef.remote.services.github.GithubService
import io.reactivex.Single

class GitRemoteRepositoryImp constructor(
    private val githubService: GithubService,
    private val pullRequestEntityMapper: PullRequestEntityMapper,
    private val singleRepoEntityMapper: SingleRepoEntityMapper
) : GitRemote {

    override fun getUserGitRepositories(username: String): Single<List<SingleRepoEntity>> {
        return githubService.getUserRepositories(username)
            .map { listOfPRRemoteModels ->
                listOfPRRemoteModels.map { singleRepoEntityMapper.mapFromModel(it) }
            }
    }

    override fun getPullRequestList(pullRequestGetBody: PullRequestGetBody): Single<List<PullRequestEntity>> {
        return githubService.getPullRequestForGithubRepo(
            pullRequestGetBody.userName,
            pullRequestGetBody.repositoryName,
            pullRequestGetBody.state
        )
            .map { listOfRemoteRepos ->
                listOfRemoteRepos.map { pullRequestEntityMapper.mapFromModel(it) }
            }
    }
}