/*
 * *
 *  * Created by prady on 3/29/22, 1:32 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 3/29/22, 1:32 PM
 *
 */

package com.prady.mvvm.templete.ui.mainActivity.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prady.mvvm.templete.data.local.Dummy
import com.prady.mvvm.templete.repository.DummyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val dummyRepository: DummyRepository) : ViewModel() {

    /**
     * Get dummy data from repository and return it as LiveData
     *
     * @return A LiveData object that will be observed in the activity.
     */
    fun getDummyData(): LiveData<List<Dummy>> {
        return dummyRepository.getDummy()
    }

    /**
     * Inserts a dummy data into the database
     *
     * @param dummy Dummy is the parameter that we're passing to the insertDummyData function.
     */
    fun insertDummyData(dummy: Dummy) {
        viewModelScope.launch(Dispatchers.IO) {
            dummyRepository.insertDummy(dummy)
        }
    }
}