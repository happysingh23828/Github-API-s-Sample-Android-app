package com.androchef.presentation.views.mappers.pull_requets

import com.androchef.domain.models.pullrequest.PullRequest
import com.androchef.presentation.mapper.Mapper
import com.androchef.presentation.views.mappers.user.UserViewMapper
import com.androchef.presentation.views.utils.DateTimeUtils
import com.androchef.presentation.views.views.PullRequestView
import javax.inject.Inject

class PullRequestViewMapper @Inject constructor(private val userViewMapper: UserViewMapper) :
    Mapper<PullRequestView, PullRequest> {

    override fun mapToView(type: PullRequest): PullRequestView {
        return PullRequestView(
            id = type.id,
            prDesc = type.desc,
            closedAt = getValidDate(type.closedAt),
            createdAt = getValidDate(type.createdAt),
            user = userViewMapper.mapToView(type.user),
            prTitle = type.title
        )
    }

    private fun getValidDate(date : String) : String {
        return if(date.isValid()){
            DateTimeUtils.getDayWithMonthName(date)
        } else
            "No date available."
    }
}

fun String?.isValid(): Boolean {
    return this != null && this.isNotEmpty() && this.isNotBlank()
}