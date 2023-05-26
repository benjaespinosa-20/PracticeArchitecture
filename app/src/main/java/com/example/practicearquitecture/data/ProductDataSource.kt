package com.example.practicearquitecture.data

import com.example.practicearquitecture.data.model.ListProduct

class ProductDataSource(private val webService: WebService) {

    suspend fun getProduct(): ListProduct = webService.getProducts()
}