package com.example.mvvm.hilt

import com.example.mvvm.api.FakeApiService
import com.example.mvvm.data.file.ReadFile
import com.example.mvvm.data.file.ReadFileImpl
import com.example.mvvm.data.repository.MainRepository
import com.example.mvvm.data.repository.MainRepositoryImpl
import com.example.mvvm.di.ChatModule
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [ChatModule::class])
abstract class FakeApiServiceModule {

    @Binds
    @Singleton
    abstract fun providesJSON(fakeApiService: FakeApiService): ReadFile
//
//    @Binds
//    abstract fun getJsonSource(file: ReadFileImpl): ReadFile

    @Binds
    abstract fun getRepo(repo: MainRepositoryImpl): MainRepository
}