package com.example.practicearquitecture.data

import com.example.practicearquitecture.data.model.ListProduct

interface ProductRepository {

    suspend fun getProduct(): ListProduct
}