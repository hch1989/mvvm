package com.example.mvvm.api

import android.content.Context
import com.example.mvvm.JsonProvider
import com.example.mvvm.data.Constant
import com.example.mvvm.data.file.ReadFile
import com.example.mvvm.data.model.inbox
import javax.inject.Inject

class FakeApiService @Inject constructor() : ReadFile {

    var failUserApi: Boolean = false
    var wrongResponse: Boolean = false

    companion object {
        private const val CHAT_JSON = "/json/chat.json"
    }

    override suspend fun getChats(context: Context): List<inbox> {

        val fakeResponse: List<inbox> = JsonProvider.objectFromJsonFileWithType(CHAT_JSON).response.inbox

        return fakeResponse
        if (failUserApi) throw Exception("Api failed")

        if (wrongResponse) return fakeResponse.apply {
            listOf<inbox>()
        }

    }


}