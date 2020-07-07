package com.androchef.githubsampleapp.ui.pull_requests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.androchef.githubsampleapp.R
import com.androchef.presentation.viewmodel.GitDataViewModel

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
        return mView.rootView
    }

    private fun init() {
        gitDataViewModel =
            ViewModelProviders.of(requireActivity()).get(GitDataViewModel::class.java)
    }


    companion object {
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