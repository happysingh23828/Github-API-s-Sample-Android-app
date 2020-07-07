package com.androchef.githubsampleapp.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.androchef.domain.GitRepository
import com.androchef.domain.interactor.pullrequests.GetPullRequestListUseCase
import com.androchef.domain.interactor.user_repos.GetUserRepositoryListUseCase
import com.androchef.githubsampleapp.MainApplication
import com.androchef.githubsampleapp.R
import com.androchef.githubsampleapp.extensions.addFragment
import com.androchef.githubsampleapp.extensions.createFactory
import com.androchef.githubsampleapp.ui.landing.LandingFragment
import com.androchef.presentation.viewmodel.GitDataViewModel
import com.androchef.presentation.views.mappers.git_repos.SingleGitRepoViewMapper
import com.androchef.presentation.views.mappers.pull_requets.PullRequestViewMapper
import javax.inject.Inject

class DashboardActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, DashboardActivity::class.java)
            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var getPullRequestListUseCase: GetPullRequestListUseCase

    @Inject
    lateinit var getUserRepositoryListUseCase: GetUserRepositoryListUseCase

    @Inject
    lateinit var pullRequestViewMapper: PullRequestViewMapper

    @Inject
    lateinit var singleRequestViewMapper: SingleGitRepoViewMapper

    private lateinit var gitDataViewModel: GitDataViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        init()
        loadLandingFragment()
    }

    private fun init() {
        MainApplication.appComponent().inject(this)
        val factory = GitDataViewModel(
            pullRequestViewMapper,
            singleRequestViewMapper,
            getPullRequestListUseCase,
            getUserRepositoryListUseCase
        ).createFactory()
        gitDataViewModel = ViewModelProviders.of(this, factory).get(
            GitDataViewModel::class.java
        )
    }

    private fun loadLandingFragment() {
        addFragment(R.id.mainFragmentContainer, LandingFragment.newInstance())
    }
}