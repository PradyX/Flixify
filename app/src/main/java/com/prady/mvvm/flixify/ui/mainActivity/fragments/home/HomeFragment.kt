/*
 * *
 *  * Created by prady on 3/29/22, 1:32 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 3/29/22, 1:32 PM
 *
 */

package com.prady.mvvm.flixify.ui.mainActivity.fragments.home

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.prady.mvvm.flixify.R
import com.prady.mvvm.flixify.data.local.DummyDatabase
import com.prady.mvvm.flixify.data.remote.ApiService
import com.prady.mvvm.flixify.data.remote.RetrofitHelper
import com.prady.mvvm.flixify.databinding.HomeFragmentBinding
import com.prady.mvvm.flixify.repository.DummyRepository
import com.prady.mvvm.flixify.ui.mainActivity.fragments.home.slider.SliderAdapter
import com.prady.mvvm.flixify.ui.mainActivity.fragments.home.slider.SliderItemsModel

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: HomeFragmentBinding
    private val sliderHandler = Handler()
    private val sliderRunnable =
        Runnable {
            binding.viewPagerImageSlider.currentItem = binding.viewPagerImageSlider.currentItem + 1
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)

        /* This is how we inject the dependencies into the view model. */
        val apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val dao = DummyDatabase.getDatabase(requireActivity()).dummyDao()
        val repository = DummyRepository(apiService, dao)

        homeViewModel =
            ViewModelProvider(this, HomeViewModelFactory(repository)).get(HomeViewModel::class.java)
        homeViewModel.getDummyData().observe(viewLifecycleOwner, Observer {
            Log.e("dummy", it.toString())
        })
        setupSlider()
        return binding.root
    }

    private fun setupSlider() {
        val sliderItems: MutableList<SliderItemsModel> = ArrayList()
        sliderItems.add(SliderItemsModel(R.drawable.dummy_user))
        sliderItems.add(SliderItemsModel(R.drawable.dummy_user))
        sliderItems.add(SliderItemsModel(R.drawable.dummy_user))
        sliderItems.add(SliderItemsModel(R.drawable.dummy_user))

        binding.viewPagerImageSlider.setAdapter(
            SliderAdapter(
                sliderItems,
                binding.viewPagerImageSlider
            )
        )
        binding.viewPagerImageSlider.setClipToPadding(false)
        binding.viewPagerImageSlider.setClipChildren(false)
        binding.viewPagerImageSlider.setOffscreenPageLimit(3)
        binding.viewPagerImageSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        binding.viewPagerImageSlider.setPageTransformer(compositePageTransformer)
        binding.viewPagerImageSlider.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable, 4000) //slide duration
            }
        })
    }

    override fun onPause() {
        super.onPause()
        sliderHandler.removeCallbacks(sliderRunnable)
    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRunnable, 2000)
    }

}