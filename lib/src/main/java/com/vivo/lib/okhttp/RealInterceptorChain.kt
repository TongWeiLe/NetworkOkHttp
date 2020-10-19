package com.vivo.lib.okhttp

class RealInterceptorChain(private val index:Int, val interceptors : List<Interceptor>) : Interceptor.Chain {

    internal fun copy(index: Int, interceptors: List<Interceptor>) = RealInterceptorChain(index, interceptors)

    override fun request(): Request {

        return Request()
    }

    override fun proceed(request: Request): Response {

        val interceptor = interceptors[index]
        val next = copy(index + 1, interceptors)
        return interceptor.intercept(next)
    }
}