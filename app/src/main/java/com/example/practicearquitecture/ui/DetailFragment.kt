package com.example.practicearquitecture.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.practicearquitecture.R
import com.example.practicearquitecture.databinding.FragmentDetailBinding
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.admanager.AdManagerInterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private lateinit var binding: FragmentDetailBinding
    private val args by navArgs<DetailFragmentArgs>()
    private var bannerDetail = AdsBanner()
    private var interstitialAd = AdsInterstitial()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        val appContext = context!!.applicationContext


        //Ads Functions
        bannerDetail.adBanner(appContext, binding)
        interstitialAd.adInterstitial(appContext)

        //SafeArgs
        Glide.with(requireContext()).load(args.thumbnail).into(binding.detailImage)
        binding.tvTitleDetail.text = args.title
        binding.detailDescription.text = args.description
        binding.detailPrice.text = args.price.toString()

        //Click Button Back to list
        binding.btnDetail.setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToProductFragment())
        }

        //Click button show Ad INTERSTITIAL
        binding.btnAds.setOnClickListener {
            interstitialAd.showAdInterstitial(requireContext())
        }
    }
}