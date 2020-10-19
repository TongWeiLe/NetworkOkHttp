package com.vivo.lib.okhttp

class ConnectInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        println("ConnectInterceptor intercept")
        val realInterceptorChain = chain as RealInterceptorChain

        return realInterceptorChain.proceed(realInterceptorChain.request())
    }
}