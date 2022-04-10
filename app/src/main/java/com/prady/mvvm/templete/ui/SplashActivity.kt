/*
 * *
 *  * Created by prady on 3/29/22, 1:32 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 3/29/22, 1:02 PM
 *
 */

package com.prady.mvvm.templete.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.prady.mvvm.templete.databinding.ActivitySplashBinding
import com.prady.mvvm.templete.ui.mainActivity.MainActivity

class SplashActivity : AppCompatActivity() {
    var binding: ActivitySplashBinding? = null

    companion object {
        private const val SPLASH_TIME_OUT = 1500
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        Handler().postDelayed({
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish() }, SPLASH_TIME_OUT.toLong())
    }
}