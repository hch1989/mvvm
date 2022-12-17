package com.example.mvvm.ui.main.event

import android.view.View
import com.example.mvvm.data.model.inboxSorting

interface ChatListClickListener {
    fun onChatListItemClick(view: View, user: inboxSorting)
}