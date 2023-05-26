package com.example.practicearquitecture.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.practicearquitecture.R
import com.example.practicearquitecture.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding

    private val args by navArgs<DetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)

        Glide.with(requireContext()).load(args.thumbnail).into(binding.detailImage)
        binding.tvTitleDetail.text = args.title
        binding.detailDescription.text = args.description
        binding.detailPrice.text = args.price.toString()

        binding.btnDetail.setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToProductFragment())
        }

    }

}