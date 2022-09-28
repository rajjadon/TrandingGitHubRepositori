package com.raj.jadon.domain.model

data class ClosedPullRequestUser(
    val title: String = "",
    val created_date: String = "",
    val closed_date: String = "",
    val user: User
)

data class User(
    val name: String,
    val avatar_url: String = ""
)