/*
 * *
 *  * Created by prady on 3/29/22, 1:32 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 3/29/22, 1:02 PM
 *
 */

package com.prady.mvvm.flixify.ui.mainActivity

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.crowdfire.cfalertdialog.CFAlertDialog
import com.google.android.material.navigation.NavigationBarView
import com.prady.mvvm.flixify.R
import com.prady.mvvm.flixify.databinding.ActivityMainBinding
import com.prady.mvvm.flixify.ui.mainActivity.fragments.favourite.FavouriteFragment
import com.prady.mvvm.flixify.ui.mainActivity.fragments.home.HomeFragment
import com.prady.mvvm.flixify.ui.mainActivity.fragments.search.SearchFragment
import com.prady.mvvm.flixify.utils.ConnectionLiveData
import com.prady.srmgpc_user.helpers.PreferenceManager

class MainActivity : AppCompatActivity() {

    private lateinit var preferenceManager: PreferenceManager
    private lateinit var binding: ActivityMainBinding
    private lateinit var connectionLiveData: ConnectionLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceManager = PreferenceManager(this)
        preferenceManager.userName = "Prady"
        Log.e("pref_test", preferenceManager.userName.toString())

//        if(savedInstanceState == null){
//            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()
//        }

        connectionLiveData = ConnectionLiveData(this)
        connectionLiveData.observe(this) { isNetworkAvailable ->
            isNetworkAvailable?.let {
                updateUI(it)
            }
        }

        navigationBarView()
    }

    private fun navigationBarView() {
        binding.bottomNavigation.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_page -> {
                    supportFragmentManager.beginTransaction()
                        .setCustomAnimations(
                            R.anim.slide_in_from_left,
                            R.anim.fade_out,
                            R.anim.fade_in,
                            R.anim.slide_out_left
                        )
                        .replace(R.id.fragment_container, HomeFragment()).commit()
                }
                R.id.search_page -> {
                    supportFragmentManager.beginTransaction()
                        .setCustomAnimations(
                            R.anim.down_to_up,
                            R.anim.fade_out,
                            R.anim.fade_in,
                            R.anim.up_to_down
                        )
                        .replace(R.id.fragment_container, SearchFragment()).commit()
                }
                R.id.favourite_page -> {
                    supportFragmentManager.beginTransaction()
                        .setCustomAnimations(
                            R.anim.slide_in_right,
                            R.anim.fade_out,
                            R.anim.fade_in,
                            R.anim.slide_out_to_right
                        )
                        .replace(R.id.fragment_container, FavouriteFragment()).commit()
                }
            }
            true
        })
    }

    private fun updateUI(it: Boolean) {
        if (it) {
            Log.e("Network", "Available")
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.down_to_up,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.up_to_down
                )
                .replace(R.id.fragment_container, HomeFragment()).commit()
        } else {
            Log.e("Network", "Not Available")

            CFAlertDialog.Builder(this)
                .setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT)
                .setHeaderView(R.layout.dialog_header_no_connection)
                .setTitle("No Internet Connection")
                .setMessage("Please check your internet connection")
                .addButton(
                    "Dismiss", -1, -1,
                    CFAlertDialog.CFAlertActionStyle.NEGATIVE,
                    CFAlertDialog.CFAlertActionAlignment.JUSTIFIED
                ) { dialog: DialogInterface, which: Int -> dialog.dismiss() }
                .show()

        }
    }
}