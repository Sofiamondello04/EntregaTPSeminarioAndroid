package ar.edu.unicen.seminario.ddl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ApiKeyInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()

        val newUrl = originalRequest.url().newBuilder()
            .addQueryParameter("api_key", Constants.API_KEY)
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}