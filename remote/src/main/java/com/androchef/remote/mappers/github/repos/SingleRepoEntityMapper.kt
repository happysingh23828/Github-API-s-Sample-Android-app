package com.androchef.remote.mappers.github.repos

import com.androchef.data.models.SingleRepoEntity
import com.androchef.remote.mappers.EntityMapper
import com.androchef.remote.models.github.repo.GithubSingleRepo

class SingleRepoEntityMapper : EntityMapper<GithubSingleRepo,SingleRepoEntity> {

    override fun mapFromModel(model: GithubSingleRepo): SingleRepoEntity {
        return SingleRepoEntity(
            repoName = model.name,
            repoDescription = model.description,
            openIssuesCount = model.openIssuesCount,
            forksCount = model.forksCount,
            defaultBranch = model.defaultBranch
        )
    }
}