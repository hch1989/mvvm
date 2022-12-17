package com.example.mvvm.data.model

data class Chat (
    val response: response,
        )

data class response (
    val userDetail: userDetail,
    val inbox: List<inbox>
        )

data class userDetail (
    val userId: Int,
    val userName: String
        )

data class inbox (
    val senderId: Int,
    val senderName: String,
    val messages: List<messages>
        )

data class messages (
    val senderId: Int,
    val message: String,
    val time: String
        )

data class inboxSorting (
    val senderId: Int,
    val senderName: String,
    val time: String
)

