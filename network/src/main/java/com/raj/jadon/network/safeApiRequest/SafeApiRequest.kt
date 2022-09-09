package com.raj.jadon.network.safeApiRequest

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.raj.jadon.network.RetrialPolicy
import com.raj.jadon.network.baseResponse.ErrorResponse
import com.raj.jadon.network.dataState.DataState
import com.raj.jadon.network.exception.NoInternetException
import com.raj.jadon.network.networkHelper.NetworkHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketTimeoutException
import javax.inject.Inject

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1)
}

open class SafeApiRequest
@Inject constructor(
    private val networkHelper: NetworkHelper
) {
    suspend fun <T : Any> apiRequest(
        maxRetry: Int = RetrialPolicy.Default.retryNum,
        retry: Int = 1,
        apiCall: suspend () -> T,
    ): DataState<T> {

        return when (val data = internalApiRequest(dataRequest = apiCall)) {
            is DataState.Success -> data
            is DataState.Error -> {
                if (retry < maxRetry)
                    apiRequest(maxRetry, retry, apiCall)
                else
                    data
            }
            DataState.Loading -> DataState.Loading
        }
    }

    private suspend inline fun <T : Any> internalApiRequest(
        crossinline dataRequest: suspend () -> T
    ): DataState<T> {
        return try {
            if (networkHelper.isNetworkConnected()) {
                val response = withContext(Dispatchers.IO) { dataRequest.invoke() }

                if (response is Response<*>) {

                    if (response.code() == 200)
                        DataState.Success(response)
                    else {
                        val errorResponse =
                            ErrorResponse(response.code(), true, getErrorMessage(response.code()))
                        val throwable = Throwable(message = errorResponse.message)
                        DataState.Error(Exception(throwable), errorMessage = errorResponse.message)
                    }
                } else
                    DataState.Success(response)

            } else {
                val throwable = NoInternetException("Please check your Internet Connection")
                DataState.Error(throwable, throwable.message.toString())
            }

        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    val errorResponse = convertErrorBody(throwable)
                    DataState.Error(
                        throwable,
                        errorMessage = errorResponse?.message.toString()
                    )
                }
                is SocketTimeoutException -> DataState.Error(
                    throwable,
                    getErrorMessage(ErrorCodes.SocketTimeOut.code)
                )

                else -> {
                    DataState.Error(throwable as Exception, throwable.message.toString())
                }
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
    return try {
        throwable.response()?.errorBody()?.let {

            val gson = Gson()
            val type = object : TypeToken<ErrorResponse>() {}.type
            val errorResponse: ErrorResponse = gson.fromJson(it.string(), type)
            errorResponse
        }
    } catch (exception: Exception) {
        ErrorResponse(throwable.code(), true, getErrorMessage(throwable.code()))
    }
}

private fun getErrorMessage(code: Int): String {
    return when (code) {
        ErrorCodes.SocketTimeOut.code -> "Timeout"
        400 -> "Bad Request"
        401 -> "Unauthorised"
        403 -> "Forbidden"
        404 -> "Not found"
        409 -> "Conflict"
        500 -> "Internal Server Error"
        else -> "Something went wrong"
    }
}