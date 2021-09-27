package com.app.focusonmvmm.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.focusonmvmm.data.model.Product
import com.app.focusonmvmm.data.repository.FocusRepository
import com.app.focusonmvmm.utils.Resource
import retrofit2.Call
import retrofit2.Response


class FocusViewModel(private val focusRepository: FocusRepository) : ViewModel() {

    private val products = MutableLiveData<Resource<List<Product>>>()

    init {
        fetchProducts()
    }

    private fun fetchProducts() {

        //Loading
        products.postValue(Resource.loading(null))

        //Call API
        focusRepository.getProducts().enqueue(object : retrofit2.Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                products.postValue(Resource.success(response.body()))
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                products.postValue(Resource.error(null, t.message))
            }

        })


    }

    fun getProducts(): LiveData<Resource<List<Product>>> = products

}