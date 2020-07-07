package com.androchef.presentation.viewmodel

import com.androchef.presentation.views.views.PullRequestView
import com.androchef.presentation.views.views.SingleRepoView

sealed class GitDataState {

    object Init : GitDataState()

    data class Loading(val message: String) : GitDataState()

    data class Error(var message: String) : GitDataState()

    data class GetUsersRepositoriesSuccess(
        var lisOfRepos: List<SingleRepoView>
    ) : GitDataState()

    data class GetPullRequestsSuccess(
        var lisOfPullRequests: List<PullRequestView>
    ) : GitDataState()
}