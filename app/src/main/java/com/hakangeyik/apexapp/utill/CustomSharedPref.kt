package com.hakangeyik.apexapp.utill

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class CustomSharedPref {

    companion object{
        private val PREFERENCES_TIME = "preferences_time"
        private var sharedPreferences:SharedPreferences? = null

        @Volatile private var instance: CustomSharedPref? = null

        private val lock = Any()
        operator fun invoke(context: Context):CustomSharedPref = instance?: synchronized(lock){
            instance?: makeCustomSharedPref(context).also {
                instance = it
            }
        }

        private fun makeCustomSharedPref(context: Context):CustomSharedPref{
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return CustomSharedPref()
        }
    }

    fun saveTime(time:Long){
        sharedPreferences?.edit(commit = true){
            putLong(PREFERENCES_TIME,time)
        }

    }

    fun getTime() = sharedPreferences?.getLong(PREFERENCES_TIME,0)

}