package com.example.practicearquitecture.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.practicearquitecture.data.ProductRepository
import com.example.practicearquitecture.utils.Resource
import kotlinx.coroutines.Dispatchers

class ProductViewModel(private val repository: ProductRepository): ViewModel() {

    fun getProduct() = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try{
            emit(Resource.Success(repository.getProduct()))
        } catch(e: Exception){
            emit(Resource.Failure(e))
        }
    }
}

class ProductViewModelFactory(private val repo: ProductRepository): ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ProductRepository::class.java).newInstance(repo)
    }

}