package com.example.vrgsofttest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vrgsofttest.R
import com.example.vrgsofttest.data.RedditPost
import com.example.vrgsofttest.domain.GetTopPostUseCase
import com.example.vrgsofttest.model.MainFragmentViewModel
import com.example.vrgsofttest.model.MainFragmentViewModelFactory
import com.example.vrgsofttest.view.RecyclerViewAdapter
import org.koin.android.ext.android.inject


class MainFragment : Fragment() {
private lateinit var  mainFragmentViewModel:MainFragmentViewModel
    private  val getTopPostUseCase: GetTopPostUseCase by inject()
    private  val savedStateHandle: SavedStateHandle by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_main, container, false)
        val recyclerView= view.findViewById<RecyclerView>(R.id.recyclerView)
        var data: ArrayList<RedditPost>
        val factory = MainFragmentViewModelFactory(getTopPostUseCase,savedStateHandle)
        mainFragmentViewModel = ViewModelProvider(this, factory)[MainFragmentViewModel::class.java]

        mainFragmentViewModel.redditPosts.observe(viewLifecycleOwner) { posts ->
            data = posts as ArrayList<RedditPost>
            val state = mainFragmentViewModel.getState()
            if(state==data){
                mainFragmentViewModel.setState(data)
            }
            recyclerView.layoutManager = LinearLayoutManager(view.context)
            val adapter = RecyclerViewAdapter(data)
            recyclerView.adapter = adapter

        }
        mainFragmentViewModel.fetchTopPost()

        return view
    }
}
