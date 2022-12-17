package com.example.mvvm.data.file

import android.content.Context
import android.util.Log
import com.example.mvvm.data.model.Chat
import com.example.mvvm.data.model.inbox
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class ReadFileImpl: ReadFile {
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
        return getJson.response.inbox
    }
}