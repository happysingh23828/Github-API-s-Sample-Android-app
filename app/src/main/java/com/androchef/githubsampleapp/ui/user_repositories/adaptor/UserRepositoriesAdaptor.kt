package com.androchef.githubsampleapp.ui.user_repositories.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androchef.githubsampleapp.R
import com.androchef.presentation.views.views.SingleRepoView
import kotlinx.android.synthetic.main.layout_single_user_repository_item.view.*

class UserRepositoriesAdaptor(private val listOfRepos: List<SingleRepoView>) :
    RecyclerView.Adapter<UserRepositoriesAdaptor.UserRepositoriesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRepositoriesViewHolder {
        return UserRepositoriesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_single_user_repository_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return listOfRepos.size
    }

    override fun onBindViewHolder(holder: UserRepositoriesViewHolder, position: Int) {
        holder.bind(listOfRepos[position])
    }

    inner class UserRepositoriesViewHolder(private val mView: View) :
        RecyclerView.ViewHolder(mView) {

        fun bind(repoView: SingleRepoView) {
            mView.tvRepoName.text = repoView.repoName
            mView.tvRepoDesc.text = repoView.repoDescription
            mView.tvRepoForkCount.text = repoView.forksCount.toString()
        }
    }
}