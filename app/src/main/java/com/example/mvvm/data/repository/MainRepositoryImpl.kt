package com.example.mvvm.data.repository

import android.content.Context
import com.example.mvvm.data.Constant.Companion.SHARED_PREF
import com.example.mvvm.data.Constant.Companion.USERID
import com.example.mvvm.data.file.ReadFile
import com.example.mvvm.data.model.inboxSorting
import com.example.mvvm.data.model.messages
import com.example.mvvm.extension.ToDateFormatter
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val readFile: ReadFile
    ): MainRepository {
    private var messageMap: Map<Int, List<messages>> = mapOf()

    override suspend fun getChat(@ApplicationContext context: Context): List<inboxSorting> {
        var inboxSorting = readFile.getChats(context).map {
            inboxSorting(it.senderId,
                         it.senderName,
                         it.messages[it.messages.size-1].time)
        }

        inboxSorting = inboxSorting.sortedBy {
            it.time.ToDateFormatter()
        }.reversed()

        return inboxSorting
    }

    override suspend fun getUser(@ApplicationContext context: Context): Int {
        val s = context.getSharedPreferences(SHARED_PREF,Context.MODE_PRIVATE)
        return s.getInt(USERID, 0)
    }

    override suspend fun getMessage(context: Context, senderId: Int): List<messages>?  {
        val getInbox = readFile.getChats(context)
        getInbox.forEach{
            messageMap = getInbox.map { it.senderId to it.messages }.toMap()
        }
        return messageMap[senderId]
    }

}


