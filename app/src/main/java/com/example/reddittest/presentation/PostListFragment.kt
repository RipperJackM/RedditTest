package com.example.reddittest.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.reddittest.databinding.FragmentPostListBinding

class PostListFragment: Fragment() {

    companion object {
        val TAG: String = PostListFragment::class.java.simpleName
    }

    private var _binding: FragmentPostListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: PostListViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeResource()
        initializeObservers()
        initializeListeners()
    }

    private fun initializeListeners() = Unit

    private fun initializeObservers() = Unit

    private fun initializeResource() = Unit

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}