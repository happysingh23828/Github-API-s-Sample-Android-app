package com.androchef.githubsampleapp.ui.pull_requests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.androchef.domain.models.pullrequest.PullRequest
import com.androchef.githubsampleapp.R
import com.androchef.githubsampleapp.extensions.toast
import com.androchef.githubsampleapp.ui.pull_requests.adaptor.PullRequestListAdaptor
import com.androchef.presentation.viewmodel.GitDataState
import com.androchef.presentation.viewmodel.GitDataViewModel
import com.androchef.presentation.views.views.PullRequestView
import kotlinx.android.synthetic.main.fragment_pull_requests.view.*

class PullRequestsFragment : Fragment() {


    private lateinit var mView: View

    private lateinit var userName: String

    private lateinit var repoName: String

    private lateinit var gitDataViewModel: GitDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString(ARGS_USERNAME)?.let {
            userName = it
        }

        arguments?.getString(ARGS_REPO_NAME)?.let {
            repoName = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_pull_requests, container, false)
        init()
        setObservers()
        fetchPullRequests()
        return mView.rootView
    }

    private fun fetchPullRequests() {
        //Config for all states [open, closed, all].
        gitDataViewModel.getPullRequestsForRepos(userName, repoName, PullRequest.State.CLOSED)
    }

    private fun setObservers() {
        gitDataViewModel.stateObservable.observe(this, Observer {
            updateState(it)
        })
    }

    private fun init() {
        gitDataViewModel =
            ViewModelProviders.of(requireActivity()).get(GitDataViewModel::class.java)
    }


    private fun updateState(state: GitDataState) {
        when (state) {
            is GitDataState.GetPullRequestsSuccess -> showPrList(state.lisOfPullRequests)
            is GitDataState.Error -> {
                popUpFragment(state.message)
            }
        }
    }

    private fun showPrList(listOfPullRequests: List<PullRequestView>) {
        if (listOfPullRequests.isNotEmpty()) {
            mView.pullRequestRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            mView.pullRequestRecyclerView.adapter = PullRequestListAdaptor(listOfPullRequests)
        } else {
            popUpFragment(getString(R.string.info_no_pr_found))
        }
    }

    private fun popUpFragment(message: String) {
        toast(message)
        fragmentManager?.popBackStack()
    }


    companion object {
        const val TAG = "PullRequestsFragment"
        private const val ARGS_USERNAME = "args_username"
        private const val ARGS_REPO_NAME = "args_repo_name"
        fun newInstance(userName: String, repoName: String): PullRequestsFragment {
            val bundle = Bundle()
            bundle.putString(ARGS_USERNAME, userName)
            bundle.putString(ARGS_REPO_NAME, repoName)
            val fragment = PullRequestsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}