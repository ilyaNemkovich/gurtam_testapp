package com.gurtam.news.data.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.net.URI

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = getRequestWithToken(chain)
        return chain.proceed(request)
    }

    private fun getRequestWithToken(chain: Interceptor.Chain): Request {
        val original = chain.request()

        return original.newBuilder()
            .url(appendApiKeyQueryToUrl(original.url.toString()))
            .header("Content-Type", "application/json")
            .method(original.method, original.body)
            .build()
    }

    private fun appendApiKeyQueryToUrl(uri: String): String {
        val oldUri = URI(uri)
        val apiKey = "apiKey=1524b1362b7f4339b780cf1c813b75de"
        var newQuery: String = oldUri.query.let { it ?: "" }
        newQuery += if (newQuery.isEmpty()) apiKey else "&$apiKey"
        return URI(
            oldUri.scheme, oldUri.authority,
            oldUri.path, newQuery, oldUri.fragment
        ).toString()
    }
}