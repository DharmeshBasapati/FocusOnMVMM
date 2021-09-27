package com.app.focusonmvmm.data.api

import com.app.focusonmvmm.data.model.Product
import com.app.focusonmvmm.data.network.RetrofitBuilder
import retrofit2.Call

class FocusApiServiceImpl: FocusApiServices {

    override fun getProducts(): Call<List<Product>> {
        return RetrofitBuilder.focusApiServices.getProducts()
    }

}