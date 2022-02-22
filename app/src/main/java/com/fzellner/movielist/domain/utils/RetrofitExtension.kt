package com.fzellner.movielist.domain.utils

import com.fzellner.movielist.domain.exception.Failure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.lang.NullPointerException

fun <T> Response<T>.call(): Flow<T> = flow {
    val response = this@call
    when (response.isSuccessful){
        true -> emit(response.body() ?: throw NullPointerException())
        else -> throw when (response.code()){
            in 400..499 -> Failure.Client
            in 500..599 -> Failure.Server
            else -> Failure.Generic(response.message())
        }
    }
}