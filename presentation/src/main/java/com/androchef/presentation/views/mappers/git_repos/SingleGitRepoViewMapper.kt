package com.androchef.presentation.views.mappers.git_repos

import com.androchef.domain.models.repo.GitSingleRepo
import com.androchef.presentation.mapper.Mapper
import com.androchef.presentation.views.views.SingleRepoView
import javax.inject.Inject

class SingleGitRepoViewMapper @Inject constructor() : Mapper<SingleRepoView, GitSingleRepo> {

    override fun mapToView(type: GitSingleRepo): SingleRepoView {
        return SingleRepoView(
            repoName = type.repoName,
            repoDescription = type.repoDescription,
            forksCount = type.forksCount
        )
    }
}