/*
 * *
 *  * Created by prady on 3/29/22, 1:32 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 3/29/22, 1:32 PM
 *
 */

package com.prady.mvvm.templete.repository

import androidx.lifecycle.LiveData
import com.prady.mvvm.templete.data.local.Dummy
import com.prady.mvvm.templete.data.local.DummyDao
import com.prady.mvvm.templete.data.remote.ApiService

class DummyRepository(private val apiService: ApiService, private val dummyDao: DummyDao) {

    /**
     * Get the dummy data from the database
     *
     * @return LiveData<List<Dummy>>
     */
    fun getDummy(): LiveData<List<Dummy>> {
        return dummyDao.getDummy()
    }

    suspend fun insertDummy(dummy: Dummy) {
        dummyDao.insertDummy(dummy)
    }
}