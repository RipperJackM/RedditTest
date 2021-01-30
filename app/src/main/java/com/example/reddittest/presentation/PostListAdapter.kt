package com.example.reddittest.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reddittest.R
import com.example.reddittest.databinding.PostListItemBinding
import com.example.reddittest.model.PostModel
import com.example.reddittest.repository.DateUtil
import com.example.reddittest.utils.AutoUpdatableAdapter
import kotlin.properties.Delegates

class PostListAdapter : RecyclerView.Adapter<PostListAdapter.ViewHolder>(), AutoUpdatableAdapter {

    private var posts: List<PostModel> by Delegates.observable(emptyList()) { _, old, new -> autoNotify(old, new)
    }

    override fun getItemCount() = posts.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
                PostListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val postItem = posts[position]
        holder.bind(postItem, position)
    }

    fun setItems(postsItemList: List<PostModel>) {
        posts = postsItemList
    }

    inner class ViewHolder(private val binding: PostListItemBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(value: PostModel, position: Int) {
            with(value) {
                binding.postNumber.text = (position + 1).toString()
                binding.authorName.text = "Author: ${this.authorName}"
                binding.postTitle.text = this.title ?: "Title is empty"
                binding.postBodyText.text = this.body ?: "Body is empty"
                binding.postCreationDate.text = itemView.context.getString(
                        R.string.date_util_hours_ago,
                        DateUtil.dateInHoursAgo(this.date!!)
                )
                binding.postCommentCount.text = itemView.context.getString(R.string.post_item_comments_count, this.commentsCount
                        ?: 0)
                Glide
                        .with(itemView.context)
                        .load(this.imageUrl)
                        .into(binding.postThumbnailImage)
            }
        }
    }
}