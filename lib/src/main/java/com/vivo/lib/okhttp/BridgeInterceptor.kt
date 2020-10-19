package com.vivo.lib.okhttp

class BridgeInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {


        println("BridgeInterceptor intercept")
        val realInterceptorChain = chain as RealInterceptorChain

        return realInterceptorChain.proceed(realInterceptorChain.request())
    }
}