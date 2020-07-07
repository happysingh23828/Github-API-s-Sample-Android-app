package com.androchef.presentation.views.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {

    private const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
    fun getDateTimeInDayMonthFormat(date: String): String {
        val dateFormat = SimpleDateFormat(date, Locale.ENGLISH)
        val inputDate = dateFormat.parse(date)
        val newDateFormat = SimpleDateFormat("dd - MM", Locale.ENGLISH)
        return newDateFormat.format(date)
    }
}