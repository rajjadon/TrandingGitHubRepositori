package com.raj.jadon.domain.dataState

sealed class DataState<out T> {
    data class Success<out T>(val baseResponseData: T) : DataState<T>()
    data class Error(val exception: Exception, val errorMessage: String) :
        DataState<Nothing>()

    object Loading : DataState<Nothing>()
}