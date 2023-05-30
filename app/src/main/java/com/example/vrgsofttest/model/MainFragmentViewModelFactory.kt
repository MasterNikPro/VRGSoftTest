package com.example.vrgsofttest.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vrgsofttest.domain.GetTopPostUseCase

class MainFragmentViewModelFactory(
    private val getTopPostUseCase: GetTopPostUseCase, private val savedStateHandle: SavedStateHandle
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainFragmentViewModel::class.java)) {
            return MainFragmentViewModel(getTopPostUseCase, savedStateHandle) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}