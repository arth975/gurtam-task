package com.gurtam.task.utils

sealed class Resource<T> {
    companion object {
        fun <T> success(data: T): Resource<T> = Success(data)

        fun <T> error(e: Throwable, message: String?): Resource<T> = Error(e, message)
    }

    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val e: Throwable, val message: String?) : Resource<T>()
}
