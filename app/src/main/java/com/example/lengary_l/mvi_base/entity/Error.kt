package com.example.lengary_l.mvi_base.entity

/**
 * Created by CoderLengary
 */
sealed class Error(message: String): Throwable(message) {
    class MessageError(val value: String) : Error(value)
    class ThrowableError(val throwable: Throwable) : Error(throwable.localizedMessage)
}