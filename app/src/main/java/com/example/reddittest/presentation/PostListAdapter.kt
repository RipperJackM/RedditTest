package com.example.reddittest.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reddittest.R
import com.example.reddittest.databinding.PostListItemBinding
import com.example.reddittest.model.PostModel
import com.example.reddittest.utils.DateUtil
import com.example.reddittest.utils.AutoUpdatableAdapter
import kotlin.properties.Delegates

class PostListAdapter(private val onImageClickListener: (url: String?) -> Unit) : RecyclerView.Adapter<PostListAdapter.ViewHolder>(), AutoUpdatableAdapter {

    /* can have situation when all posts are without images. Add this hardcode for
        getting image and testing logic
     */
    private val testImage = "https://99px.ru/sstorage/53/2011/11/mid_27300_7363.jpg"

    private var posts: List<PostModel> by Delegates.observable(emptyList()) { _, old, new -> autoNotify(old, new) }


    override fun getItemCount() = posts.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
                PostListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onImageClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val postItem = posts[position]
        holder.bind(postItem, position)
    }

    fun setItems(postsItemList: List<PostModel>) {
        posts = postsItemList
    }

    inner class ViewHolder(private val binding: PostListItemBinding, private val onImageClickListener: (url: String?) -> Unit) :
            RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.postThumbnailImage.setOnClickListener(this)
        }

        fun bind(value: PostModel, position: Int) {
            with(value) {
                Glide
                        .with(itemView.context)
                        .load(testImage)
                        .thumbnail(0.5f)
                        .centerCrop()
                        .into(binding.postThumbnailImage)

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
                }
        }

        override fun onClick(v: View?) {
            onImageClickListener(testImage)
        }
    }
}