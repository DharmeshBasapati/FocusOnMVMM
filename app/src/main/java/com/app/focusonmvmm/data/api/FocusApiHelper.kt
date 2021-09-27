package com.app.focusonmvmm.data.api

class FocusApiHelper(private val focusApiServices: FocusApiServices) {

    fun getProducts() = focusApiServices.getProducts()

}