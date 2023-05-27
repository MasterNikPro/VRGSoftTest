package com.example.vrgsofttest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.vrgsofttest.R
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
        mainFragmentViewModel.redditPosts.observe(viewLifecycleOwner, Observer { posts ->
            for (post in posts) {
                val author = post.author
                val commentQuantity = post.num_comments
                val thumbnailImage = post.thumbnail
                val dateOfCreation = post.created_utc
                val title = post.title

                // Display the post information in the UI
                // ...
            }
        })
        mainFragmentViewModel.fetchTopPost()
        return view
    }
}
