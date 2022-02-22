package com.fzellner.movielist.domain.exception

sealed class Failure : Throwable() {
    object Client: Failure()
    object Server: Failure()
    class Generic(override val message: String?) : Failure()

}