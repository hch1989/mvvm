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

class ChatAdapter(private val messages: ArrayList<messages>, val activity: FragmentActivity) : RecyclerView.Adapter<ChatAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(messages: messages) {
            itemView.apply {
                Log.e("messages.message",messages.message)
                textViewMessage.text = messages.message
                textViewTime.text = messages.time

                textViewMessage.gravity = Gravity.RIGHT
                textViewTime.gravity = Gravity.RIGHT

//                Glide.with(imageViewAvatar.context)
//                    .load(user.avatar)
//                    .into(imageViewAvatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =

        DataViewHolder(LayoutInflater.from(parent.context).inflate(com.example.mvvm.R.layout.item_layout_chat, parent, false))

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        //val chat = getItem(position)

        holder.bind(messages[position])
    }

    fun addMessages(messages: List<messages>) {
        this.messages.apply {
            clear()
            addAll(messages)
        }

    }
}