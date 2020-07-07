package com.androchef.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.androchef.domain.interactor.pullrequests.GetPullRequestListUseCase
import com.androchef.domain.interactor.user_repos.GetUserRepositoryListUseCase
import com.androchef.domain.models.pullrequest.PullRequest
import com.androchef.domain.models.repo.GitSingleRepo
import com.androchef.presentation.base.BaseViewModel
import com.androchef.presentation.views.mappers.git_repos.SingleGitRepoViewMapper
import com.androchef.presentation.views.mappers.pull_requets.PullRequestViewMapper
import io.reactivex.observers.DisposableSingleObserver
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

class GitDataViewModel constructor(
    private val pullRequestViewMapper: PullRequestViewMapper,
    private val singleGitRepoViewMapper: SingleGitRepoViewMapper,
    private val getPullRequestListUseCase: GetPullRequestListUseCase,
    private val getUserRepositoryListUseCase: GetUserRepositoryListUseCase
) : BaseViewModel<GitDataState>() {

    private var state: GitDataState = GitDataState.Init
        set(value) {
            field = value
            publishState(value)
        }

    fun init() {
        state = GitDataState.Init
    }

    fun getUserAllRepositories(userName: String) {
        state = GitDataState.Loading("Fetching $userName's repositories")
        getUserRepositoryListUseCase
            .execute(object : DisposableSingleObserver<List<GitSingleRepo>>() {
                override fun onSuccess(t: List<GitSingleRepo>) {
                    state = GitDataState.GetUsersRepositoriesSuccess(t.map {
                        singleGitRepoViewMapper.mapToView(it)
                    })
                }

                override fun onError(e: Throwable) {
                    state = GitDataState.Error(e.transform().localizedMessage)
                }
            }, GetUserRepositoryListUseCase.Params(userName))
    }

    fun getPullRequestsForRepos(userName: String, repoName: String, prState: PullRequest.State) {
        state = GitDataState.Loading("Fetching $repoName's pull requests")
        getPullRequestListUseCase.execute(object : DisposableSingleObserver<List<PullRequest>>() {
            override fun onSuccess(t: List<PullRequest>) {
                state = GitDataState.GetPullRequestsSuccess(t.map {
                    pullRequestViewMapper.mapToView(it)
                })
            }

            override fun onError(e: Throwable) {
                state = GitDataState.Error(e.transform().localizedMessage)
            }
        }, GetPullRequestListUseCase.Params(userName, repoName, prState))
    }


    override val stateObservable: MutableLiveData<GitDataState> by lazy {
        MutableLiveData<GitDataState>()
    }
}

fun Throwable.transform(): Exception {
    return when (this) {
        is UnknownHostException,
        is InterruptedIOException,
        is ConnectException -> Exception("You 're currently offline. Please check your network connection and try again.")

        is SSLHandshakeException,
        is SocketTimeoutException -> Exception("We are unable to connect to our servers. Please check your connection and try again.")

        else -> Exception("Something went wrong please try again.")
    }
}