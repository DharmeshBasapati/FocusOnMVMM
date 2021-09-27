package com.app.focusonmvmm.data.api

import com.app.focusonmvmm.data.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface FocusApiServices {

    @GET("products")
    fun getProducts(): Call<List<Product>>

}