package com.app.focusonmvmm.data.repository

import com.app.focusonmvmm.data.api.FocusApiHelper
import com.app.focusonmvmm.data.model.Product
import retrofit2.Call

class FocusRepository(private val focusApiHelper: FocusApiHelper) {

    fun getProducts(): Call<List<Product>> {
        return focusApiHelper.getProducts()
    }

}