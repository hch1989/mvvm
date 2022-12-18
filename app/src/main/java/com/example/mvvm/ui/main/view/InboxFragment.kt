package com.example.mvvm.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.data.file.ReadFileImpl
import com.example.mvvm.data.model.inboxSorting
import com.example.mvvm.ui.base.ViewModelFactory
import com.example.mvvm.ui.main.adapter.MainAdapter
import com.example.mvvm.ui.main.event.ChatListClickListener
import com.example.mvvm.ui.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.inbox.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

class InboxFragment: Fragment(), ChatListClickListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    lateinit var listener: ChatListClickListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(com.example.mvvm.R.layout.inbox, container, false) as ViewGroup
        listener = this
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getActivity()?.setTitle("Inbox")
        setupUI()
        setupViewModel()
        setupObserversChat()
    }

    private fun setupUI() {

        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = MainAdapter(arrayListOf(), listener)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ReadFileImpl())
        ).get(MainViewModel::class.java)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun setupObserversChat() {

        viewModel.getChat(requireContext())
        viewModel.getChat(requireContext()).value?.data?.let { retrieveChat(it) }
            viewModel.getChat(requireContext()).observe(viewLifecycleOwner, Observer {
            recyclerView.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            it.data?.let { chats -> retrieveChat(chats) }
        })
    }

    private fun retrieveChat(chats: List<inboxSorting>) {
        adapter.apply {
            addChats(chats)
            notifyDataSetChanged()
        }
    }

    override fun onChatListItemClick(view: View, user: inboxSorting) {
        val fragment = newInstance(user.senderId, user.senderName)
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(com.example.mvvm.R.id.relativeLayout, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    companion object {
        @JvmStatic
        fun newInstance(senderId: Int, senderName: String) = ChatFragment().apply {
            arguments = Bundle().apply {
                putString("SENDER_NAME", senderName)
                putInt("SENDER_ID", senderId)
            }
        }
    }
}