package com.androchef.githubsampleapp.ui.user_repositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.androchef.githubsampleapp.R
import com.androchef.presentation.viewmodel.GitDataViewModel

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
        return mView.rootView
    }

    private fun init() {
        gitDataViewModel =
            ViewModelProviders.of(requireActivity()).get(GitDataViewModel::class.java)
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