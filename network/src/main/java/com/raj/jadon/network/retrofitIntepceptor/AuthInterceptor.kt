package com.raj.jadon.network.retrofitIntepceptor

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
    @ApplicationContext private val context: Context,
) :
    Interceptor {
    @SuppressLint("HardwareIds")
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val token = "Bearer s02Z5mHzyn00HPwgsuAB+1jnQKEjcgAbmDU1BFEwrnM="
        run {
            //val token = "Bearer ${authSessionManager.accessToken.first()}"

            request.header("Authorization", token)
            request.header("appId", "in.probo.pro")
            request.header("x-device-os", "ANDROID")
            request.header(
                "x-device-id", Settings.Secure
                    .getString(context.contentResolver, Settings.Secure.ANDROID_ID)
            )
            request.header("Content-Type", "application/json")
            request.header("x-os-version", android.os.Build.VERSION.SDK_INT.toString())
            request.header("x-version-name", "5.0")
            request.header("x-version-code", "60")
        }

        return chain.proceed(request.build())
    }
}