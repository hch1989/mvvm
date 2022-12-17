package com.example.mvvm.ui.main.adapter

import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.data.model.inbox
import com.example.mvvm.data.model.messages
import kotlinx.android.synthetic.main.item_layout_chat.view.*

class ChatAdapter(private val messages: ArrayList<messages>, private var userId: Int) : RecyclerView.Adapter<ChatAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(messages: messages, userId: Int) {
            itemView.apply {
                textViewMessage.text = messages.message
                textViewTime.text = messages.time
                Log.e("userId1",userId.toString())
                if(messages.senderId == userId) {
                    textViewMessage.gravity = Gravity.RIGHT
                    textViewTime.gravity = Gravity.RIGHT
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =

        DataViewHolder(LayoutInflater.from(parent.context).inflate(com.example.mvvm.R.layout.item_layout_chat, parent, false))

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(messages[position], userId)
    }

    fun addMessages(messages: List<messages>, userId: Int?) {
        Log.e("userId2",userId.toString())
        if (userId != null) {

            this.userId = userId
        }
        this.messages.apply {
            clear()
            addAll(messages)
        }
    }
}