package com.raj.jadon.domain.trandingRepositori.useCase.model

data class TrendingRepoDao(
    val author: String,
    val avatar: String,
    val currentPeriodStars: Int,
    val description: String,
    val forks: Int,
    val language: String,
    val languageColor: String,
    val name: String,
)