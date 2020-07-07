package com.androchef.githubsampleapp.ui.landing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.androchef.githubsampleapp.R


class LandingFragment : Fragment() {

    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_landing, container, false)
        init()
        return mView.rootView
    }

    private fun init() {
        Toast.makeText(requireContext(), "Hello landing", Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance(): LandingFragment {
            return LandingFragment()
        }
    }
}