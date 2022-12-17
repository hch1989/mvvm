package com.example.mvvm.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.R
import com.example.mvvm.R.*
import com.example.mvvm.data.model.inbox
import com.example.mvvm.ui.base.ViewModelFactory
import com.example.mvvm.ui.main.adapter.MainAdapter
import com.example.mvvm.ui.main.viewmodel.MainViewModel
import com.example.mvvm.utils.Status.ERROR
import com.example.mvvm.utils.Status.LOADING
import com.example.mvvm.utils.Status.SUCCESS
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.android.synthetic.main.inbox.progressBar
import kotlinx.android.synthetic.main.inbox.recyclerView


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstFragment = InboxFragment()
        supportFragmentManager.beginTransaction().replace(R.id.relativeLayout, firstFragment)
            .commit()
    }





}