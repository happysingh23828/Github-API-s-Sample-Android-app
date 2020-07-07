package com.androchef.remote.services.github

import com.androchef.remote.models.github.pr.GithubPullRequest
import com.androchef.remote.models.github.repo.GithubSingleRepo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("users/{username}/repos")
    fun getUserRepositories(
        @Path("username") username: String
    ): Single<List<GithubSingleRepo>>

    @GET("/repos/{username}/{repo_name}/pulls")
    fun getPullRequestForGithubRepo(
        @Path("username") username: String,
        @Path("repo_name") repoName: String,
        @Query("state") state: String = "all"
    ) : Single<List<GithubPullRequest>>

}
