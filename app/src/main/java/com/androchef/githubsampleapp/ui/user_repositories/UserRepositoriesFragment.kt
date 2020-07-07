package com.androchef.githubsampleapp.ui.user_repositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.androchef.githubsampleapp.R
import com.androchef.githubsampleapp.extensions.toast
import com.androchef.presentation.viewmodel.GitDataState
import com.androchef.presentation.viewmodel.GitDataViewModel
import com.androchef.presentation.views.views.SingleRepoView

class UserRepositoriesFragment : Fragment() {

    private lateinit var mView: View

    private lateinit var userName: String

    private lateinit var gitDataViewModel: GitDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.getString(ARGS_USERNAME)?.let {
            userName = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_user_repositories, container, false)
        init()
        setObservers()
        fetchRepositories()
        return mView.rootView
    }

    private fun fetchRepositories() {
        gitDataViewModel.getUserAllRepositories(userName)
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
            is GitDataState.GetUsersRepositoriesSuccess -> showRepoList(state.lisOfRepos)
            is GitDataState.Error -> {
                toast(state.message)
                fragmentManager?.popBackStack()
            }
        }
    }

    private fun showRepoList(lisOfRepos: List<SingleRepoView>) {
        toast(lisOfRepos.toString())
    }

    companion object {
        private const val ARGS_USERNAME = "args_username"
        fun newInstance(userName: String): UserRepositoriesFragment {
            val bundle = Bundle()
            bundle.putString(ARGS_USERNAME, userName)
            val fragment = UserRepositoriesFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}