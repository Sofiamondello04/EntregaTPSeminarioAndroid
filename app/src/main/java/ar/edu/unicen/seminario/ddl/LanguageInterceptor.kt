package ar.edu.unicen.seminario.ddl


import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

class LanguageInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val language = Locale.getDefault().language

        val originalRequest = chain.request()

        val newUrl = originalRequest.url().newBuilder()
            .addQueryParameter("language", language)
            .build()


        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}
