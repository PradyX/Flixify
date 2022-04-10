/*
 * *
 *  * Created by prady on 3/29/22, 1:32 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 3/29/22, 12:37 PM
 *
 */

package com.prady.srmgpc_user.helpers

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(var context: Context) {
    var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    var editor: SharedPreferences.Editor = sharedPreferences.edit()

    companion object {
        private const val PREF_NAME = "data"
    }

    var userName: String?
        get() = sharedPreferences.getString("username", "")
        set(userName) {
            editor.putString("username", userName)
            editor.commit()
        }

    var email: String?
        get() = sharedPreferences.getString("email", "")
        set(email) {
            editor.putString("email", email)
            editor.commit()
        }

    var profilePic: String?
        get() = sharedPreferences.getString("profilePic", "")
        set(pic) {
            editor.putString("profilePic", pic)
            editor.commit()
        }

    var mobile: String?
        get() = sharedPreferences.getString("mobile", "")
        set(mobile) {
            editor.putString("mobile", mobile)
            editor.commit()
        }

    var userid: String?
        get() = sharedPreferences.getString("userid", "")
        set(userid) {
            editor.putString("userid", userid)
            editor.commit()
        }

    var city: String?
        get() = sharedPreferences.getString("city", "")
        set(city) {
            editor.putString("city", city)
            editor.commit()
        }

    fun clear() {
        editor.clear()
        editor.apply()
        editor.commit()
    }
}