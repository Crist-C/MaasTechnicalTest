package com.ccastro.maas.domain.model

sealed class Response<out T> {
    object Loading: Response<Nothing>()
    data class Success<out T> (val data: T?, val message: String = "", val wasSuccess: Boolean = false): Response<T>()
    data class Fail<out T> (val exception: Exception?, val message: String = "", val wasSuccess: Boolean = false): Response<T>()
}