/*
 * *
 *  * Created by prady on 3/29/22, 1:32 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 3/29/22, 1:32 PM
 *
 */

package com.prady.mvvm.templete.ui.mainActivity.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prady.mvvm.templete.repository.DummyRepository


/* This is a factory class that creates a ViewModel */
class HomeViewModelFactory(private val dummyRepository: DummyRepository) :
    ViewModelProvider.Factory {

    /* This is a method that returns a ViewModel. */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(dummyRepository) as T
    }

}