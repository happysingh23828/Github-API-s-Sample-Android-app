package com.androchef.data.mapper.user

import com.androchef.data.mapper.Mapper
import com.androchef.data.models.UserEntity
import com.androchef.domain.models.user.User

class UserMapper : Mapper<UserEntity, User> {

    override fun mapFromEntity(type: UserEntity): User {
        return User(
            userName = type.userName,
            profilePic = type.profilePic
        )
    }

    override fun mapToEntity(type: User): UserEntity {
        return UserEntity(
            userName = type.userName,
            profilePic = type.profilePic
        )
    }
}