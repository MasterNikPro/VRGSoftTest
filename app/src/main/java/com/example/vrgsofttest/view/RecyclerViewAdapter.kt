package com.example.vrgsofttest.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vrgsofttest.R
import com.example.vrgsofttest.data.RedditPost
import com.squareup.picasso.Picasso
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit
import com.squareup.picasso.Target

class RecyclerViewAdapter(private val posts: List<RedditPost>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = posts[position]

        if (itemsViewModel.is_video) {
            holder.thumbnailImageView.setImageResource(R.drawable.video_support)
        } else {
            Picasso.get().load(itemsViewModel.thumbnail).into(holder.thumbnailImageView)
            holder.thumbnailImageView.setOnClickListener {
                val openURL = Intent(Intent.ACTION_VIEW)
                openURL.data = Uri.parse(itemsViewModel.thumbnail)
                holder.itemView.context.startActivity(openURL)
            }
            holder.downloadedImageView.setOnClickListener {
                val imageUrl = itemsViewModel.thumbnail
                val file = File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    "${itemsViewModel.title}.jpg"
                )

                val target = object : Target {
                    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                        try {
                            val outputStream = FileOutputStream(file)
                            bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                            outputStream.close()

                        } catch (e: Exception) {
                            e.printStackTrace()

                        }
                    }

                    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {

                    }

                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    }
                }

                Picasso.get()
                    .load(imageUrl)
                    .into(target)
            }
        }

        holder.titleTextView.text = itemsViewModel.title
        holder.authorTextView.text = holder.itemView.context.getString(R.string.author_placeholder, itemsViewModel.author)
        holder.timeTextView.text = holder.itemView.context.getString(R.string.created_hours_ago_placeholder, getTimeAgo(itemsViewModel.created_utc))
        holder.commentsTextView.text = holder.itemView.context.getString(R.string.comments_placeholder, itemsViewModel.num_comments.toString())
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val thumbnailImageView: ImageView = itemView.findViewById(R.id.thumbnailImageView)
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val authorTextView: TextView = itemView.findViewById(R.id.authorTextView)
        val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
        val commentsTextView: TextView = itemView.findViewById(R.id.commentsTextView)
        val downloadedImageView: ImageView = itemView.findViewById(R.id.downloadImageView)
    }

    private fun getTimeAgo(createdUtc: Long): String {
        val currentTime = System.currentTimeMillis() / 1000
        val elapsedTimeInSeconds = currentTime - createdUtc
        val hours = TimeUnit.SECONDS.toHours(elapsedTimeInSeconds)
        return "$hours"
    }
}
