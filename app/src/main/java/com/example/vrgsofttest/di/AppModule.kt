package com.example.vrgsofttest.di

import com.example.vrgsofttest.data.GetTopPostImpl
import com.example.vrgsofttest.network.RedditApi
import com.example.vrgsofttest.domain.GetTopPostUseCase
import com.example.vrgsofttest.model.MainFragmentViewModel
import com.example.vrgsofttest.network.NetworkClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { NetworkClient.redditApi }
    single<GetTopPostUseCase> { GetTopPostImpl() }
    viewModel { MainFragmentViewModel(get()) }
}