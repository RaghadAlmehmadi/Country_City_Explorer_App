package com.example.countrycityexplorer.data.apiservice

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor // Import HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.UUID
import java.util.concurrent.TimeUnit




object RetrofitInstance {
    private const val BASE_URL = "https://countriesnow.space/api/v0.1/"

    // Custom Interceptor to Add Headers
    class AppVersionInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
                .addHeader("App-Version", "1.0.0") // Fetch app version
                .addHeader("Platform", "Android") // Optional: Add platform info
                .build()
            return chain.proceed(request)
        }
    }

    // Interceptor for Adding Authentication Token
    class AuthInterceptor(private val tokenProvider: () -> String?) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val token = tokenProvider.invoke()
            val request = chain.request().newBuilder()

            // Add Authorization Header if Token Exists
            token?.let {
                request.addHeader("Authorization", "Bearer $it")
            }

            return chain.proceed(request.build())
        }
    }

    // Interceptor for Generating Authorization Header with Random UUID
    object AuthorizationInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val requestWithHeader = chain.request()
                .newBuilder()
                .header("Authorization", "Raghad 1222222") // Generate random UUID
                .build()
            return chain.proceed(requestWithHeader)
        }
    }

    // Logging Interceptor for Debugging API Requests/Responses
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // Log full request/response
    }

    // OkHttpClient with Interceptors and Timeout Handling
    private val client = OkHttpClient.Builder()
        .addInterceptor(AppVersionInterceptor()) // Add App-Version & Platform headers
        .addInterceptor(AuthorizationInterceptor) // Add Authorization with Random UUID
        .addInterceptor(loggingInterceptor) // Enable logging for debugging
        .connectTimeout(30, TimeUnit.SECONDS) // Handle connection timeouts
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    // Retrofit Instance with OkHttpClient
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client) // Attach OkHttpClient
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }


}
