package com.example.mvvm.data.repository

import android.content.Context
import android.util.Log
import com.example.mvvm.data.file.ReadFile
import com.example.mvvm.data.model.inbox
import com.example.mvvm.data.model.messages

class MainRepository(private val readFile: ReadFile) {
    private lateinit var message: List<messages>
    private var messageMap: Map<Int, List<messages>> = mapOf()

    suspend fun getChat(context: Context) = readFile.getChats(context)

    suspend fun getUser(context: Context) = readFile.getChats(context)

    suspend fun getMessage(context: Context, senderId: Int): List<messages>?  {
        val getInbox = getChat(context)
        getInbox.forEach{
            messageMap = getInbox.map { it.senderId to it.messages }.toMap()
        }
        Log.e("messageMap", messageMap.toString())
        return messageMap[senderId]
    }

}


