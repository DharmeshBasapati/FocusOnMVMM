package com.app.focusonmvmm.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.app.focusonmvmm.R
import com.app.focusonmvmm.data.model.Product
import com.app.focusonmvmm.databinding.RowItemProductsRoundCutBinding
import com.bumptech.glide.Glide
import java.util.*

class ProductsAdapter(private var productsList: List<Product>) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    inner class ViewHolder(val rowItemProductsBinding: RowItemProductsRoundCutBinding) :
        RecyclerView.ViewHolder(rowItemProductsBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        RowItemProductsRoundCutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(productsList[position]) {
                rowItemProductsBinding.tvProductTitle.text = productTitle
                rowItemProductsBinding.chipProductCategory.text = productCategory.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                }
                rowItemProductsBinding.chipProductRating.text = rating.rate.toString()
                rowItemProductsBinding.tvProductPrice.text =
                    holder.itemView.context.getString(R.string.label_price, productPrice.toString())
                Glide.with(holder.itemView.context).load(productImage)
                    .into(rowItemProductsBinding.ivProductImage)

                itemView.setOnClickListener {
                    if (rowItemProductsBinding.lavSparkle.isVisible) {
                        rowItemProductsBinding.lavSparkle.visibility = View.GONE
                    } else {
                        rowItemProductsBinding.lavSparkle.visibility = View.VISIBLE
                    }
                }

            }
        }
    }

    override fun getItemCount() = productsList.size

    fun addData(data: List<Product>) {
        productsList = data
    }
}