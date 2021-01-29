package com.example.reddittest.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reddittest.databinding.PostListItemBinding
import com.example.reddittest.model.PostModel
import com.example.reddittest.utils.AutoUpdatableAdapter
import kotlin.properties.Delegates

class PostListAdapter: RecyclerView.Adapter<PostListAdapter.ViewHolder>(), AutoUpdatableAdapter {

    private var posts: List<PostModel> by Delegates.observable(emptyList()) { _, old, new -> autoNotify(old, new) }

    override fun getItemCount() = posts.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PostListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    inner class ViewHolder(private val binding: PostListItemBinding) : RecyclerView.ViewHolder(binding.root)
}