package com.example.vrgsofttest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vrgsofttest.R
import com.example.vrgsofttest.data.RedditPost
import com.example.vrgsofttest.model.MainFragmentViewModel
import com.example.vrgsofttest.view.RecyclerViewAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {
private val mainFragmentViewModel by viewModel<MainFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_main, container, false)
        val recyclerView= view.findViewById<RecyclerView>(R.id.recyclerView)
        var data: ArrayList<RedditPost>

        mainFragmentViewModel.redditPosts.observe(viewLifecycleOwner) { posts ->
            data = posts as ArrayList<RedditPost>
            recyclerView.layoutManager = LinearLayoutManager(view.context)
            val adapter = RecyclerViewAdapter(data)
            recyclerView.adapter = adapter

        }
        mainFragmentViewModel.fetchTopPost()

        return view
    }
}
