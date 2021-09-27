package com.app.focusonmvmm.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.app.focusonmvmm.data.api.FocusApiHelper
import com.app.focusonmvmm.data.api.FocusApiServiceImpl
import com.app.focusonmvmm.data.model.Product
import com.app.focusonmvmm.databinding.ActivityMainBinding
import com.app.focusonmvmm.ui.base.FocusViewModelFactory
import com.app.focusonmvmm.ui.main.adapter.ProductsAdapter
import com.app.focusonmvmm.ui.main.viewmodel.FocusViewModel
import com.app.focusonmvmm.utils.Status

class MainActivity : AppCompatActivity() {

    private lateinit var focusViewModel: FocusViewModel
    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupViewModel()
        setupObserver()

        binding.floatingActionButton.setOnClickListener {
            when {
                binding.progressBar.isVisible -> {
                    binding.progressBar.visibility = View.GONE
                }
                else -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }

    }

    private fun setupUI() {
        binding.rvProducts.layoutManager = LinearLayoutManager(this)
        productsAdapter = ProductsAdapter(arrayListOf())
        binding.rvProducts.adapter = productsAdapter
    }

    private fun setupViewModel() {
        focusViewModel = ViewModelProvider(
            this, FocusViewModelFactory(
                FocusApiHelper(
                    FocusApiServiceImpl()
                )
            )
        ).get(FocusViewModel::class.java)
    }

    private fun setupObserver() {
        focusViewModel.getProducts().observe(this, {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.rvProducts.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvProducts.visibility = View.VISIBLE
                    it.data?.let { it1 -> renderList(it1) }
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvProducts.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun renderList(data: List<Product>) {
        productsAdapter.addData(data)
        productsAdapter.notifyDataSetChanged()
    }
}