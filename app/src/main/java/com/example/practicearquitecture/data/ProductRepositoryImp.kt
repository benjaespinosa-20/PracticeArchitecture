package com.example.practicearquitecture.data

import com.example.practicearquitecture.data.model.ListProduct

class ProductRepositoryImp(private val dataSource: ProductDataSource): ProductRepository {
    override suspend fun getProduct(): ListProduct = dataSource.getProduct()
}