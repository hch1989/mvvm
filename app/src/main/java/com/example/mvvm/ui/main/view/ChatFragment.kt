package com.example.mvvm.ui.main.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.R
import com.example.mvvm.data.file.ReadFile
import com.example.mvvm.data.file.ReadFileImpl
import com.example.mvvm.data.model.messages
import com.example.mvvm.ui.base.ViewModelFactory
import com.example.mvvm.ui.main.adapter.ChatAdapter
import com.example.mvvm.ui.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.chat.*


class ChatFragment: Fragment() {
    private var senderId: Int = 0
    private lateinit var adapter: ChatAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.chat, container, false) as ViewGroup
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupViewModel()
        setupObserversMessage()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getInt("SENDER_ID")?.let {
            senderId = it
        }
    }

    private fun setupUI() {

        chatRecyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = ChatAdapter(arrayListOf() , requireActivity())
        chatRecyclerView.addItemDecoration(
            DividerItemDecoration(
                chatRecyclerView.context,
                (chatRecyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        chatRecyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ReadFileImpl())
        ).get(MainViewModel::class.java)
    }

    private fun setupObserversMessage() {

        viewModel.getMessage(requireContext(), senderId).observe(viewLifecycleOwner, Observer {
            chatRecyclerView.visibility = View.VISIBLE
            chatProgressBar.visibility = View.GONE
            Log.e("gg2", it.message.toString())

            it.data?.let { message ->
                Log.e("message_", message.toString())
                retrieveMessage(message) }
        })
    }

    private fun retrieveMessage(messages: List<messages>) {
        adapter.apply {
            addMessages(messages)
            notifyDataSetChanged()
        }
    }



}