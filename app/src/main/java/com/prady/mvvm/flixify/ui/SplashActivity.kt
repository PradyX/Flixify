/*
 * *
 *  * Created by prady on 3/29/22, 1:32 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 3/29/22, 1:02 PM
 *
 */

package com.prady.mvvm.flixify.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.prady.mvvm.flixify.R
import com.prady.mvvm.flixify.databinding.ActivitySplashBinding
import com.prady.mvvm.flixify.ui.mainActivity.MainActivity

class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding
    lateinit var slideUp: Animation

    companion object {
        private const val SPLASH_TIME_OUT = 3500
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        slideUp = AnimationUtils.loadAnimation(this, R.anim.splah_slide_up)
        binding.animationView.startAnimation(slideUp)
        binding.animationView2.startAnimation(slideUp)

        Handler().postDelayed({
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }
}