package com.gurtam.task.domain.models

sealed class ResultOf<out T>() {

    companion object {
        fun <T> success(data: T): ResultOf<T> = Success(data)

        fun <T> error(e: Throwable, message: String?): ResultOf<T> = Error(e, message)
    }

    class Success<T>(val data: T) : ResultOf<T>()
    class Error<T>(val e: Throwable, val message: String?) : ResultOf<T>()
}
