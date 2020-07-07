package com.androchef.remote.mappers.github.user

import com.androchef.data.models.UserEntity
import com.androchef.remote.mappers.EntityMapper
import com.androchef.remote.models.github.user.GithubUser
import javax.inject.Inject

class UserEntityMapper @Inject constructor() : EntityMapper<GithubUser, UserEntity> {

    override fun mapFromModel(model: GithubUser): UserEntity {
        return UserEntity(
            userName = model.login,
            profilePic = model.avatarUrl
        )
    }
}