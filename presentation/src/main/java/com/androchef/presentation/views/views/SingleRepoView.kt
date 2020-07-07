package com.androchef.presentation.views.views

data class SingleRepoView(
    var repoName : String = "",
    var repoDescription : String = "",
    var openIssuesCount : Int = -1,
    var forksCount : Int = -1,
    var defaultBranch : String = ""
)