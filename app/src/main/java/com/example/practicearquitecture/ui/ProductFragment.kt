package com.example.practicearquitecture.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.practicearquitecture.R
import com.example.practicearquitecture.data.ProductDataSource
import com.example.practicearquitecture.data.ProductRepositoryImp
import com.example.practicearquitecture.data.RetrofitClient
import com.example.practicearquitecture.data.model.Products
import com.example.practicearquitecture.databinding.FragmentProductBinding
import com.example.practicearquitecture.utils.Resource
import com.example.practicearquitecture.viewModel.ProductViewModel
import com.example.practicearquitecture.viewModel.ProductViewModelFactory


class ProductFragment : Fragment(R.layout.fragment_product), ProductAdapter.OnProductClickListener {

    private lateinit var binding: FragmentProductBinding

    private val viewModel by viewModels<ProductViewModel>{
        ProductViewModelFactory(ProductRepositoryImp(
            ProductDataSource(RetrofitClient.webservice)
        ))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductBinding.bind(view)

        viewModel.getProduct().observe(viewLifecycleOwner) { list ->
            when(list){
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvList.adapter = ProductAdapter(list.data.products, this@ProductFragment)
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

    }

    override fun onProductClick(products: Products) {
        val action = ProductFragmentDirections.actionProductFragmentToDetailFragment(
            products.id,
            products.title,
            products.description,
            products.price,
            products.thumbnail
        )
        findNavController().navigate(action)
    }

}