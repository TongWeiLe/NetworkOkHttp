package com.vivo.lib.okhttp

class CallServerInterceptor : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {

        println("CallServerInterceptor intercept")
        return Response()
    }
}