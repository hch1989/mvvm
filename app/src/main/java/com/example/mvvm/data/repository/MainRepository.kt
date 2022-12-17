package com.example.mvvm.data.repository

import android.content.Context
import com.example.mvvm.data.model.inbox
import com.example.mvvm.data.model.inboxSorting
import com.example.mvvm.data.model.messages
import dagger.hilt.android.qualifiers.ApplicationContext

interface MainRepository {
    suspend fun getChat(@ApplicationContext context: Context): List<inboxSorting>
    suspend fun getUser(@ApplicationContext context: Context): Int
    suspend fun getMessage(context: Context, senderId: Int): List<messages>?
}