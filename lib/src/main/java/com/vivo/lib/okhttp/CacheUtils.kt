package com.vivo.lib.okhttp


object CacheUtils {

    var response : Response? = null

    fun saveResponse( response: Response) {



        this.response = response
    }
}