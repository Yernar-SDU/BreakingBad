package com.example.mvvm_template.data.cloud

import com.example.mvvm_template.model.response.ErrorResponse
import com.example.mvvm_template.model.response.ErrorStatus
import com.example.mvvm_template.R
import com.example.mvvm_template.core.App
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is UnknownHostException -> {
                    ResultWrapper.Error(
                        ErrorStatus.NO_CONNECTION, null, App.instance.getString(
                            R.string.no_internet_connection
                        )
                    )
                }
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse =
                        convertErrorBody(
                            throwable
                        )
                    ResultWrapper.Error(ErrorStatus.BAD_RESPONSE, code, errorResponse)
                }
                is SocketTimeoutException -> {
                    ResultWrapper.Error(
                        ErrorStatus.TIMEOUT,
                        null,
                        App.instance.getString(R.string.socket_timeout_exception)
                    )
                }
                else -> {
                    ResultWrapper.Error(ErrorStatus.NOT_DEFINED, null, throwable.message)
                }
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): String? {
    return try {
        throwable.response()?.errorBody()?.string()?.let {
            Gson().fromJson(it, ErrorResponse::class.java).error
        }
    } catch (exception: Exception) {
        null
    }
}