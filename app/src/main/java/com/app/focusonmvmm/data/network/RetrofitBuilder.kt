package com.app.focusonmvmm.data.network

import com.app.focusonmvmm.data.api.FocusApiServices
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val BASE_URL = "https://fakestoreapi.com/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val focusApiServices: FocusApiServices = getRetrofit().create(FocusApiServices::class.java)
}