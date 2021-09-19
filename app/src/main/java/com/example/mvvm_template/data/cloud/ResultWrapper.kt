package com.example.mvvm_template.data.cloud

import com.example.mvvm_template.model.response.ErrorStatus


sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class Error(val status: ErrorStatus? = null, val code: Int? = null, val error: String? = null): ResultWrapper<Nothing>()
}