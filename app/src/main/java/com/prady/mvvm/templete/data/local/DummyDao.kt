/*
 * *
 *  * Created by prady on 3/29/22, 1:32 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 3/29/22, 1:32 PM
 *
 */

package com.prady.mvvm.templete.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DummyDao {

    /**
     * It returns a LiveData object of List of Dummy objects
     */
    @Query("SELECT * from tb_name")
    fun getDummy(): LiveData<List<Dummy>>

    @Insert
    suspend fun insertDummy(dummy: Dummy)
}