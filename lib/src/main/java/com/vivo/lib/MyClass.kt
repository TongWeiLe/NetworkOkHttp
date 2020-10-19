package com.vivo.lib

import com.vivo.lib.okhttp.*


fun main() {

    val originRequest = Request()
    val interceptors = mutableListOf<Interceptor>()
    interceptors += RetryInterceptor()
    interceptors += BridgeInterceptor()
    interceptors += CacheInterceptor()
    interceptors += ConnectInterceptor()
    interceptors += CallServerInterceptor()

    val interceptorChain = RealInterceptorChain(0, interceptors)

    val response = interceptorChain.proceed(originRequest)
    println("response : ${response.code}, ${response.result}")

}
class MyClass {
}