package com.example.machinetesttask.utils


sealed class Resource<T>(
    val response: T? = null,
    val message: String? = null
) {

    class Sucess<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}