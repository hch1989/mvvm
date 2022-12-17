package com.example.mvvm.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.data.file.ReadFile
import com.example.mvvm.data.file.ReadFileImpl
import com.example.mvvm.data.model.inbox
import com.example.mvvm.ui.base.ViewModelFactory
import com.example.mvvm.ui.main.adapter.MainAdapter
import com.example.mvvm.ui.main.event.ChatListClickListener
import com.example.mvvm.ui.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.inbox.*

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
        setupUI()

        //setupObservers()
        setupViewModel()
        setupObserversChat()

    }

    private fun setupUI() {

        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = MainAdapter(arrayListOf(), listener , requireActivity())
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

    private fun setupObserversChat() {

            viewModel.getChat(requireContext()).observe(viewLifecycleOwner, Observer {
            recyclerView.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            it.data?.let { chats -> retrieveChat(chats) }
        })
    }

    private fun retrieveChat(chats: List<inbox>) {
        adapter.apply {
            addChats(chats)
            notifyDataSetChanged()
        }
    }

    override fun onChatListItemClick(view: View, user: inbox) {
        Toast.makeText(requireContext(), user.senderName + "", Toast.LENGTH_SHORT).show()
        //val fragment: Fragment = ChatFragment()
        val fragment = newInstance(user.senderId)
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(com.example.mvvm.R.id.relativeLayout, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    companion object {
        @JvmStatic
        fun newInstance(senderId: Int) = ChatFragment().apply {
            arguments = Bundle().apply {

                putInt("SENDER_ID", senderId)
            }
        }
    }
}