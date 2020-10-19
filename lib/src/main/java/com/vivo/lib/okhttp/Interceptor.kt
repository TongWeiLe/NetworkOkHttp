package com.vivo.lib.okhttp

import java.io.IOException

interface Interceptor {

    fun intercept(chain: Chain) : Response

    interface Chain {

        fun request(): Request

        @Throws(IOException::class)
        fun proceed(request: Request): Response
    }
}