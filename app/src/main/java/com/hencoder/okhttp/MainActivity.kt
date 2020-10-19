package com.hencoder.okhttp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.vivo.lib.okhttp.*
import com.vivo.lib.okhttp.Interceptor
import okhttp3.*
import okhttp3.CertificatePinner
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Proxy


class MainActivity : AppCompatActivity() {

  val interceptors = mutableListOf<Interceptor>()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    interceptors += RetryInterceptor()
    interceptors += BridgeInterceptor()
    interceptors += CacheInterceptor()
    interceptors += ConnectInterceptor()
    interceptors += CallServerInterceptor()
    val url = "https://api.github.com/users/rengwuxian/repos"
    val hostname = "api.github.com"


    println("okhttpstart: ${System.currentTimeMillis()}")
    val client = OkHttpClient.Builder().eventListener(MyEventListener())
      .build()
    val request: Request = Request.Builder()
      .url(url)
      .build()
    client.newCall(request)
      .enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
          e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {

          println("okhttpresponse: ${System.currentTimeMillis()}")
          println("Response status code: ${response.code}")
        }
      })


    findViewById<Button>(R.id.textView).setOnClickListener {
      val originRequest = com.vivo.lib.okhttp.Request()
      val interceptorChain = RealInterceptorChain(0, interceptors)
      val response = interceptorChain.proceed(originRequest)
      println("response : ${response.code}, ${response.result}")

    }
  }

  class MyEventListener : EventListener() {

    override fun connectStart(call: Call, inetSocketAddress: InetSocketAddress, proxy: Proxy) {

      println("starttime: ${System.currentTimeMillis()}")
      super.connectStart(call, inetSocketAddress, proxy)
    }

    override fun connectionAcquired(call: Call, connection: Connection) {
      println("acquired: ${System.currentTimeMillis()}")
      super.connectionAcquired(call, connection)
    }

    override fun connectEnd(
      call: Call,
      inetSocketAddress: InetSocketAddress,
      proxy: Proxy,
      protocol: Protocol?
    ) {
      super.connectEnd(call, inetSocketAddress, proxy, protocol)
      println("connectEnd: ${System.currentTimeMillis()}")
    }
  }
}