package com.vivo.lib.okhttp

class RetryInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        println("RetryInterceptor intercept")
        val realInterceptorChain = chain as RealInterceptorChain

        return realInterceptorChain.proceed(realInterceptorChain.request())
    }
}