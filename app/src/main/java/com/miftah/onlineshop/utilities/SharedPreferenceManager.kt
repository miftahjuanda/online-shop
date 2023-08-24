package com.miftah.onlineshop.utilities

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.miftah.onlineshop.model.CurrentUser

internal final class SharedPreferenceManager(val context: Context) {

    private val PREFS_NAME = "sharedpref"
    val sharedPref: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveUSer(user: CurrentUser) {
        sharedPref.edit().putString("user", Gson().toJson(user)).apply()
    }

    fun getUser(): CurrentUser? {
        val data = sharedPref.getString("user", null)
        if (data == null) {
            return null
        }
        return Gson().fromJson(data, CurrentUser::class.java)
    }
}