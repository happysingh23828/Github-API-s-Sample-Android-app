package com.androchef.remote.mappers.github.pull_request

import com.androchef.data.models.PullRequestEntity
import com.androchef.remote.mappers.EntityMapper
import com.androchef.remote.mappers.github.user.UserEntityMapper
import com.androchef.remote.models.github.pr.GithubPullRequest
import com.androchef.remote.models.github.user.GithubUser
import javax.inject.Inject

class PullRequestEntityMapper @Inject constructor(private val userEntityMapper: UserEntityMapper) :
    EntityMapper<GithubPullRequest, PullRequestEntity> {

    override fun mapFromModel(model: GithubPullRequest): PullRequestEntity {
        return PullRequestEntity(
            id = model.id?:-1,
            desc = model.body?:"No description ",
            user = userEntityMapper.mapFromModel(model.user ?: GithubUser()),
            title = model.title ?:"No title",
            createdAt = model.createdAt ?:"",
            closedAt = model.closedAt ?:""
        )
    }
}