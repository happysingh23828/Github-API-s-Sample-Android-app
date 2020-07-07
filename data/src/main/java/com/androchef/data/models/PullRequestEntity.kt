package com.androchef.data.models

data class PullRequestEntity(
    var id: Int = -1,
    var title: String = "",
    var user: UserEntity = UserEntity(),
    var closedAt: String = "",
    var createdAt: String = ""
)