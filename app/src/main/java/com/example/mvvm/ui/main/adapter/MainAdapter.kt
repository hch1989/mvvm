package com.example.mvvm.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.data.model.inboxSorting
import com.example.mvvm.extension.DateTimeFormatter
import com.example.mvvm.ui.main.event.ChatListClickListener
import kotlinx.android.synthetic.main.item_layout.view.*

class MainAdapter(private val chats: ArrayList<inboxSorting>, val chatListClickListener: ChatListClickListener,
                  ) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(chat: inboxSorting) {
            itemView.apply {
                textViewUserName.text = chat.senderName
                textViewLatestTime.text = chat.time.DateTimeFormatter()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =

        DataViewHolder(LayoutInflater.from(parent.context).inflate(com.example.mvvm.R.layout.item_layout, parent, false))

    override fun getItemCount(): Int = chats.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            chatListClickListener.onChatListItemClick(it,chats[position])
        }
        holder.bind(chats[position])
    }

    fun addChats(chats: List<inboxSorting>) {
        this.chats.apply {
            clear()
            addAll(chats)
        }

    }
}

fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(getAdapterPosition(), getItemViewType())
    }
    return this
}