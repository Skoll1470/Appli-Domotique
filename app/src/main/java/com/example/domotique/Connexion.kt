package com.example.domotique
import com.loopj.android.http.*

class Connexion {


    companion object Factory {

        private val client = AsyncHttpClient()
        public fun get_request(
            url: String,
            params: RequestParams?,
            responseHandler: AsyncHttpResponseHandler
        ) {
            client.get(url, params, responseHandler)
        }

        public fun post(
            url: String,
            params: RequestParams?,
            responseHandler: AsyncHttpResponseHandler
        ) {
            client.get(url, params, responseHandler)
        }
    }
}