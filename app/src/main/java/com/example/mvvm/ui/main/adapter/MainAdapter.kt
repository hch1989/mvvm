package com.example.mvvm.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.data.model.inbox
import com.example.mvvm.ui.main.event.ChatListClickListener
import kotlinx.android.synthetic.main.item_layout.view.*


class MainAdapter(private val chats: ArrayList<inbox>, val chatListClickListener: ChatListClickListener,
                  val activity: FragmentActivity) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(chat: inbox) {
            itemView.apply {
                textViewUserName.text = chat.senderName
                //textViewUserEmail.text = user.email
//                Glide.with(imageViewAvatar.context)
//                    .load(user.avatar)
//                    .into(imageViewAvatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =

        DataViewHolder(LayoutInflater.from(parent.context).inflate(com.example.mvvm.R.layout.item_layout, parent, false))

    override fun getItemCount(): Int = chats.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        //val chat = getItem(position)
        holder.itemView.setOnClickListener{
            chatListClickListener.onChatListItemClick(it,chats[position])
        }
        holder.bind(chats[position])
    }

    fun addChats(chats: List<inbox>) {
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