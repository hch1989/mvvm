package com.example.mvvm.ui.main.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.mvvm.data.model.inboxSorting
import com.example.mvvm.data.repository.MainRepository
import com.example.mvvm.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
    ) : ViewModel() {
//
//    private val uiState = MutableLiveData<UiState>(UiState.Loading)
//
//    suspend fun getChat(context: Context): List<inboxSorting> {
//        viewModelScope.launch {
//            uiState.value = UiState.Loading
//            val result = mainRepository.getChat(context)
//            uiState.value = when(result) {
//                is Result.Error<*> -> UiState.Error(result.message)
//                is Result.Success<*> -> UiState.Success(result)
//                else -> { UiState.Error("error")}
//            }
//
//        }
//
//
//    }

    fun getChat(context: Context) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getChat(context)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getMessage(context: Context, senderId: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getMessage(context, senderId)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getUserId(context: Context) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getUser(context)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    // Magical 🧙‍♀️
    sealed class UiState {
        object Loading: UiState()
        data class Error(val message: String): UiState()
        data class Success(val implants:List<inboxSorting>): UiState()
    }

}