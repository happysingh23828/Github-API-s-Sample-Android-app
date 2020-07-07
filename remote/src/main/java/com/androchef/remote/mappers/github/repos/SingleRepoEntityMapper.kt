package com.androchef.remote.mappers.github.repos

import com.androchef.data.models.SingleRepoEntity
import com.androchef.remote.mappers.EntityMapper
import com.androchef.remote.models.github.repo.GithubSingleRepo
import javax.inject.Inject

class SingleRepoEntityMapper @Inject constructor() : EntityMapper<GithubSingleRepo,SingleRepoEntity> {

    override fun mapFromModel(model: GithubSingleRepo): SingleRepoEntity {
        return SingleRepoEntity(
            repoName = model.name?:"",
            repoDescription = model.description?:"",
            openIssuesCount = model.openIssuesCount?:0,
            forksCount = model.forksCount?:0,
            defaultBranch = model.defaultBranch?:""
        )
    }
}