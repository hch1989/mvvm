package com.example.mvvm

import android.content.Context
import android.util.Log
import com.example.mvvm.data.Constant
import com.example.mvvm.data.model.Chat
import com.example.mvvm.data.model.inbox
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

object JsonProvider {

    inline fun objectFromJsonFileWithType(filePath: String): Chat {
        Log.e("file",fileAsString(filePath))
        val listCountryType = object : TypeToken<Chat>() {}.type
        val getJson: Chat = Gson().fromJson(fileAsString(filePath), listCountryType)

        return getJson

    }


//    inline fun objectFromJsonFileWithType(context: Context): List<inbox> {
//        var jsonString: String
//        try {
//            jsonString = context.assets.open("chat_test.json")
//                .bufferedReader()
//                .use { it.readText() }
//            //Log.e("jsonString", jsonString)
//        } catch (ioException: IOException) {
//            Log.e("ioException", ioException.toString())
//            //AppLogger.d(ioException)
//        }
//
//        val listCountryType = object : TypeToken<Chat>() {}.type
//        val getJson: Chat = Gson().fromJson(jsonString, listCountryType)
//
//
//        return getJson.response.inbox
//    }

    fun fileAsString(filePath: String): String {
        Log.e("filePath",filePath)
        return this::class.java
            .getResourceAsStream(filePath)!!
            .bufferedReader()
            .use { it.readText() }
    }
}