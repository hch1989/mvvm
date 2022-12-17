package com.example.mvvm.data.file

import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings.Global.getString
import android.util.Log
import com.example.mvvm.R
import com.example.mvvm.data.Constant.Companion.SHARED_PREF
import com.example.mvvm.data.Constant.Companion.USERID
import com.example.mvvm.data.model.Chat
import com.example.mvvm.data.model.inbox
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import javax.inject.Inject


class ReadFileImpl @Inject constructor(): ReadFile {
    override suspend fun getChats(context: Context): List<inbox> {
        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("chat.json")
                .bufferedReader()
                .use { it.readText() }
            //Log.e("jsonString", jsonString)
        } catch (ioException: IOException) {
            Log.e("ioException", ioException.toString())
            //AppLogger.d(ioException)
        }

        val listCountryType = object : TypeToken<Chat>() {}.type
        val getJson: Chat = Gson().fromJson(jsonString, listCountryType)

        val sharedPreference = context.getSharedPreferences(
            SHARED_PREF, Context.MODE_PRIVATE).edit()
        sharedPreference.putInt(USERID, getJson.response.userDetail.userId)
        sharedPreference.apply()

        return getJson.response.inbox
    }


}