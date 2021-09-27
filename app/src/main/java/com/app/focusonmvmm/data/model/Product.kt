package com.app.focusonmvmm.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id") val productId: Int,
    @SerializedName("title") val productTitle: String,
    @SerializedName("description") val productDescription: String,
    @SerializedName("category") val productCategory: String,
    @SerializedName("price") val productPrice: Double,
    @SerializedName("image") val productImage: String,
    @SerializedName("rating") val rating: Rating
)
