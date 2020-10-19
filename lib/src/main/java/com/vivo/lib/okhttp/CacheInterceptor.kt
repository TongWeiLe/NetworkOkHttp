package com.vivo.lib.okhttp

class CacheInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {


        println("CacheInterceptor intercept")
        if (CacheUtils.response != null) {

            println("cache is not null, return directly")
            return CacheUtils.response!!
        }
        val realInterceptorChain = chain as RealInterceptorChain

        val response = realInterceptorChain.proceed(realInterceptorChain.request())

        CacheUtils.saveResponse(response)

        return response
    }
}