package com.purwadhika.sharedpreferencessimpleproject

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceUtils {

    companion object{

        private const val PREFERENCES_KEY = "preferences_key"
        private const val SHARED_PREFERENCES_KEY = "${BuildConfig.APPLICATION_ID}.${PREFERENCES_KEY}"

        fun getSharedPreferences(context: Context): SharedPreferences {
            return context.getSharedPreferences(SHARED_PREFERENCES_KEY,Context.MODE_PRIVATE)
        }

        fun getStoredUsername(context: Context) :String?{
            return getSharedPreferences(context).getString(context.getString(R.string.username_key),"")
        }

        fun getStoredPassword(context: Context) :String?{
            return getSharedPreferences(context).getString(context.getString(R.string.password_key),"")
        }

        fun storeUsername(context: Context, username :String){
            val sharedPref = getSharedPreferences(context)
            with(sharedPref.edit()){
                putString(context.getString(R.string.username_key), username)
                apply()
            }
        }

        fun storePassword(context: Context, password :String){
            val sharedPref = getSharedPreferences(context)
            with(sharedPref.edit()){
                putString(context.getString(R.string.password_key), password)
                apply()
            }
        }

    }
}