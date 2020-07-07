package com.androchef.domain.models.pullrequest

import com.androchef.domain.models.user.User

data class PullRequest(
    var id : Int = -1,
    var desc : String = "",
    var title: String = "",
    var user: User = User(),
    var closedAt: String = "",
    var createdAt: String = ""
) {
    enum class State {
        OPEN, CLOSED, ALL
    }
}