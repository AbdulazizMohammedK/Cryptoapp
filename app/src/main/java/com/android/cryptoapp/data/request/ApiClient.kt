package com.android.cryptoapp.data.request

import com.android.cryptoapp.utils.Constant.BASE_URL
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request


class ApiClient() {
    private val client = OkHttpClient()

    fun makeApiRequest(): Call {
        val request = Request.Builder()
            .url(BASE_URL)
            .build()

        return client.newCall(request)
    }
}