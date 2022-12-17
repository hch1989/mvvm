package com.example.mvvm.data.file

import android.content.Context
import com.example.mvvm.data.model.inbox

interface ReadFile {
     suspend fun getChats(context: Context): List<inbox>
}