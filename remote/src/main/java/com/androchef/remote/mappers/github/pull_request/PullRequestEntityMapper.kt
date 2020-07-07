package com.androchef.remote.mappers.github.pull_request

import com.androchef.data.models.PullRequestEntity
import com.androchef.remote.mappers.EntityMapper
import com.androchef.remote.mappers.github.user.UserEntityMapper
import com.androchef.remote.models.github.pr.GithubPullRequest

class PullRequestEntityMapper(private val userEntityMapper: UserEntityMapper) :
    EntityMapper<GithubPullRequest, PullRequestEntity> {

    override fun mapFromModel(model: GithubPullRequest): PullRequestEntity {
        return PullRequestEntity(
            id = model.id,
            user = userEntityMapper.mapFromModel(model.user),
            title = model.title,
            createdAt = model.createdAt,
            closedAt = model.closedAt
        )
    }
}