package com.androchef.presentation.views.views

data class PullRequestView (
    var id : Int = -1,
    var prTitle: String = "",
    var prDesc: String = "",
    var user: UserView = UserView(),
    var closedAt: String = "",
    var createdAt: String = ""
)