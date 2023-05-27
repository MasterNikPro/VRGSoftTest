package com.example.vrgsofttest.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vrgsofttest.R
import com.example.vrgsofttest.data.RedditData
import com.example.vrgsofttest.data.RedditPost
import com.example.vrgsofttest.model.RecyclerViewAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {
private val mainFragmentViewModel by viewModel<MainFragmentViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_main, container, false)
        val recyclerView= view.findViewById<RecyclerView>(R.id.recyclerView)
        var data = ArrayList<RedditPost>()

        mainFragmentViewModel.redditPosts.observe(viewLifecycleOwner, Observer { posts ->
            data= posts as ArrayList<RedditPost>
            Log.d("kek",data.toString())
            recyclerView.layoutManager = LinearLayoutManager(view.context)
            val adapter = RecyclerViewAdapter(data)
            recyclerView.adapter=adapter

        })
        mainFragmentViewModel.fetchTopPost()

        return view
    }
}
