package com.androchef.githubsampleapp.ui.landing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.androchef.githubsampleapp.R
import com.androchef.githubsampleapp.extensions.addFragmentBackStack
import com.androchef.githubsampleapp.extensions.isValid
import com.androchef.githubsampleapp.extensions.toast
import com.androchef.githubsampleapp.ui.user_repositories.UserRepositoriesFragment
import kotlinx.android.synthetic.main.fragment_landing.view.*


class LandingFragment : Fragment() {

    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_landing, container, false)
        onClicks()
        return mView.rootView
    }

    private fun onClicks() {
        mView.btnContinue.setOnClickListener {
            val userName = mView.edtUserName.editableText.toString()
            if (userName.isValid()) {
                goToNextFragment(userName)
            } else {
                toast(getString(R.string.info_valid_username))
            }
        }
    }

    private fun goToNextFragment(userName: String) {
        addFragmentBackStack(
            R.id.mainFragmentContainer, UserRepositoriesFragment.newInstance(userName),
            TAG
        )
    }

    companion object {
        private const val TAG = "LandingFragment"
        fun newInstance(): LandingFragment {
            return LandingFragment()
        }
    }
}