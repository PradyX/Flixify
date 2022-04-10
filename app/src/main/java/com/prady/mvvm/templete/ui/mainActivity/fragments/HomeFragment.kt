/*
 * *
 *  * Created by prady on 3/29/22, 1:32 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 3/29/22, 1:32 PM
 *
 */

package com.prady.mvvm.templete.ui.mainActivity.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.prady.mvvm.templete.data.local.DummyDatabase
import com.prady.mvvm.templete.data.remote.ApiService
import com.prady.mvvm.templete.data.remote.RetrofitHelper
import com.prady.mvvm.templete.databinding.HomeFragmentBinding
import com.prady.mvvm.templete.repository.DummyRepository

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: HomeFragmentBinding

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

        return binding.root
    }


}