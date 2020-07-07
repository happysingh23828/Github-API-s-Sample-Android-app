package com.androchef.githubsampleapp.ui.pull_requests.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androchef.githubsampleapp.R
import com.androchef.githubsampleapp.extensions.isValid
import com.androchef.presentation.views.views.PullRequestView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_single_pull_request_item.view.*

class PullRequestListAdaptor(private val listOfPrs: List<PullRequestView>) :
    RecyclerView.Adapter<PullRequestListAdaptor.PullRequestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        return PullRequestViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_single_pull_request_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return listOfPrs.size
    }

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        holder.bind(listOfPrs[position])
    }

    inner class PullRequestViewHolder(private val mView: View) : RecyclerView.ViewHolder(mView) {

        fun bind(pullRequestView: PullRequestView) {
            mView.tvPrTitle.text = pullRequestView.prTitle

            pullRequestView.user.profilePic.let {
                if (it.isValid())
                    Picasso.get().load(it).into(mView.ivUserProfile)
            }

            mView.tvUserName.text =
                mView.context.getString(R.string.lable_by).plus(" ")
                    .plus(pullRequestView.user.userName)

            mView.tvCreatedAt.text = pullRequestView.createdAt
            mView.tvClosedAt.text = pullRequestView.closedAt
        }
    }
}