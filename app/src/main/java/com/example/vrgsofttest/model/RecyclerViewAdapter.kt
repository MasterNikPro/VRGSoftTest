package com.example.vrgsofttest.model

import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vrgsofttest.R
import com.example.vrgsofttest.data.RedditPost
import com.squareup.picasso.Picasso
import java.io.InputStream
import java.net.URL
import java.util.concurrent.TimeUnit

class RecyclerViewAdapter(private val posts:List<RedditPost>):
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = posts[position]

        // sets the image to the imageview from our itemHolder class
        //holder.imageView.
        if(ItemsViewModel.is_video==true){
            holder.thumbnailImageView.setImageResource(R.drawable.video_support)
        }else{
            Picasso.get().load(ItemsViewModel.thumbnail).into(holder.thumbnailImageView)
        }



        // sets the text to the textview from our itemHolder class
        holder.titleTextView.text = ItemsViewModel.title
        holder.authorTextView.text ="Author:"+ ItemsViewModel.author
        holder.timeTextView.text="Created"+getTimeAgo(ItemsViewModel.created_utc)+"hourse ago"
        holder.commentsTextView.text="Comments:"+ItemsViewModel.num_comments.toString()
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return posts.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val thumbnailImageView: ImageView = itemView.findViewById(R.id.thumbnailImageView)
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val authorTextView: TextView = itemView.findViewById(R.id.authorTextView)
        val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
        val commentsTextView:TextView= itemView.findViewById(R.id.commentsTextView)
    }
    fun getTimeAgo(createdUtc: Long): String {
        val currentTime = System.currentTimeMillis() / 1000
        val elapsedTimeInSeconds = currentTime - createdUtc

        val hours = TimeUnit.SECONDS.toHours(elapsedTimeInSeconds)

        return "$hours"
    }

}