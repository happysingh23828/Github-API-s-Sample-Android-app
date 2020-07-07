package com.androchef.presentation.views.utils

import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {

    const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ"

    fun getDayWithMonthName(input: String): String {
        val localDateTime = LocalDateTime.parse(input, DateTimeFormat.forPattern(DATE_FORMAT))
        return SimpleDateFormat("dd MMM''yy").format(localDateTime.toDate().time)
    }
}