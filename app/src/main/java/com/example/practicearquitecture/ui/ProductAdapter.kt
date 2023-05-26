package com.example.practicearquitecture.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practicearquitecture.data.model.Products
import com.example.practicearquitecture.databinding.ListItemBinding

class ProductAdapter(
    private val productList: List<Products>,
    private val itemClickListener: OnProductClickListener
    ): RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnProductClickListener{
        fun onProductClick(products: Products)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ProductViewHolder(itemBinding, parent.context)

        itemBinding.root.setOnClickListener{
            val position = holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            itemClickListener.onProductClick(productList[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is ProductViewHolder -> holder.bind(productList[position])
        }
    }

    override fun getItemCount(): Int =productList.size

    private inner class ProductViewHolder(val binding: ListItemBinding, val context: Context): BaseViewHolder<Products>(binding.root){
        override fun bind(item: Products) {
            binding.tvTitle.text = item.title
            binding.tvDescription.text = item.description
            binding.tvPrice.text = item.price.toString()
        }

    }
}

abstract class BaseViewHolder<T>(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}