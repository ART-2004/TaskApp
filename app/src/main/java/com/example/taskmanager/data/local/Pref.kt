package com.example.taskmanager.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Pref(private val context: Context) {

    private val pref = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)

    //onBoarding
    fun isUserSeen(): Boolean {
        return pref.getBoolean(SEEN_KEY, false)
    }

    fun saveSeen() {
        pref.edit().putBoolean(SEEN_KEY, true).apply()
    }

    //name
    fun getName(): String {
        return pref.getString(NAME_KEY, "").toString()
    }

    fun saveName(name: String) {
        pref.edit().putString(NAME_KEY, name).apply()
    }

    //image
    fun saveImage(image: String){
        pref.edit().putString(IMAGE_KEY, image).apply()
    }

    fun getImage(): String?{
        return pref.getString(IMAGE_KEY, "")
    }

    companion object {
        const val PREF_NAME = "Task.name"
        const val SEEN_KEY = "seen.key"
        const val NAME_KEY = "name.key"
        const val IMAGE_KEY = "image.key"
    }
}