package com.androchef.githubsampleapp.extensions

fun String?.isValid(): Boolean {
    return this != null && this.isNotEmpty() && this.isNotBlank()
}