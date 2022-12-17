package com.example.mvvm.data.file

import android.content.Context
import android.util.Log
import com.example.mvvm.data.model.Chat
import com.example.mvvm.data.model.inbox
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

interface ReadFile {
     suspend fun getChats(context: Context): List<inbox>
}