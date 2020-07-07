package com.androchef.remote.models.github.pr


import com.androchef.remote.models.github.user.GithubUser
import com.google.gson.annotations.SerializedName

data class GithubPullRequest(

    @SerializedName("author_association")
    var authorAssociation: String? = "",

    @SerializedName("body")
    var body: String? = "",

    @SerializedName("closed_at")
    var closedAt: String? = "",

    @SerializedName("comments_url")
    var commentsUrl: String? = "",

    @SerializedName("commits_url")
    var commitsUrl: String? = "",

    @SerializedName("created_at")
    var createdAt: String? = "",

    @SerializedName("diff_url")
    var diffUrl: String? = "",

    @SerializedName("draft")
    var draft: Boolean? = false,

    @SerializedName("html_url")
    var htmlUrl: String? = "",

    @SerializedName("id")
    var id: Int? = 0,

    @SerializedName("issue_url")
    var issueUrl: String? = "",

    @SerializedName("locked")
    var locked: Boolean? = false,

    @SerializedName("merge_commit_sha")
    var mergeCommitSha: String? = "",

    @SerializedName("merged_at")
    var mergedAt: String? = "",

    @SerializedName("node_id")
    var nodeId: String? = "",

    @SerializedName("number")
    var number: Int? = 0,

    @SerializedName("patch_url")
    var patchUrl: String? = "",

    @SerializedName("review_comment_url")
    var reviewCommentUrl: String? = "",

    @SerializedName("review_comments_url")
    var reviewCommentsUrl: String? = "",

    @SerializedName("state")
    var state: String? = "",

    @SerializedName("statuses_url")
    var statusesUrl: String? = "",

    @SerializedName("title")
    var title: String? = "",

    @SerializedName("updated_at")
    var updatedAt: String? = "",

    @SerializedName("url")
    var url: String? = "",

    @SerializedName("user")
    var user: GithubUser? = GithubUser()
)