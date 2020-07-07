package com.androchef.data.mapper.pull_request

import com.androchef.data.mapper.Mapper
import com.androchef.data.models.PullRequestEntity
import com.androchef.data.models.UserEntity
import com.androchef.domain.models.pullrequest.PullRequest
import com.androchef.domain.models.user.User

class PullRequestMapper : Mapper<PullRequestEntity, PullRequest> {

    override fun mapFromEntity(type: PullRequestEntity): PullRequest {
        return PullRequest(
            id = type.id,
            closedAt = type.closedAt,
            createdAt = type.createdAt,
            title = type.title,
            user = User(type.user.userName, type.user.profilePic)
        )
    }

    override fun mapToEntity(type: PullRequest): PullRequestEntity {
        return PullRequestEntity(
            id = type.id,
            closedAt = type.closedAt,
            createdAt = type.createdAt,
            title = type.title,
            user = UserEntity(type.user.userName, type.user.profilePic)
        )
    }
}