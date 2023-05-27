package com.example.vrgsofttest.di

import com.example.vrgsofttest.data.redditapi.GetTopPostImpl
import com.example.vrgsofttest.data.redditapi.RedditApi
import com.example.vrgsofttest.domain.GetTopPostUseCase
import com.example.vrgsofttest.fragments.MainFragmentViewModel
import com.example.vrgsofttest.network.NetworkClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<RedditApi> { NetworkClient.redditApi }
    single<GetTopPostUseCase>{ GetTopPostImpl() }
    viewModel {MainFragmentViewModel(get()) }
}