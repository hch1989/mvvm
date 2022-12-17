package com.example.mvvm.di

import com.example.mvvm.data.file.ReadFile
import com.example.mvvm.data.file.ReadFileImpl
import com.example.mvvm.data.repository.MainRepository
import com.example.mvvm.data.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class) // Scope dependencies
@Module
abstract class ChatModule {

    @Binds
    abstract fun getChatSource(repo: MainRepositoryImpl): MainRepository

    @Binds
    abstract fun getJsonSource(file: ReadFileImpl): ReadFile
}