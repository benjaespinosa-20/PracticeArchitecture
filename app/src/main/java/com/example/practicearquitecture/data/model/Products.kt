package com.example.practicearquitecture.data.model

data class Products(
    var id: Int = -1,
    var title: String = "",
    var description: String = "",
    var price: Int = -1,
    var thumbnail: String = ""
)

data class ListProduct(
    var products: List<Products> = listOf()
)
