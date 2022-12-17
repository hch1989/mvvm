package com.example.mvvm.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.data.file.ReadFile
import com.example.mvvm.data.repository.MainRepository
import com.example.mvvm.ui.main.viewmodel.MainViewModel


class ViewModelFactory(private val readFile: ReadFile) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(readFile)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}