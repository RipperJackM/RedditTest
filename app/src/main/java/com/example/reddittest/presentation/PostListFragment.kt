package com.example.reddittest.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reddittest.databinding.FragmentPostListBinding
import com.example.reddittest.model.PostModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class PostListFragment : Fragment() {

    companion object {
        val TAG: String = PostListFragment::class.java.simpleName
    }

    private var _binding: FragmentPostListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: PostListViewModel
    private lateinit var listAdapter: PostListAdapter

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
        initializeRecycler()
        initializeObservers()
        initializeListeners()
    }

    private fun initializeRecycler() = with(binding.postListRecycler) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = listAdapter
    }

    private fun initializeResource() {
        viewModel = ViewModelProvider(requireActivity()).get(PostListViewModel::class.java)
        listAdapter = PostListAdapter(this::onImageClick)
    }

    private fun initializeObservers() {
        viewModel.getAllPosts().observe(viewLifecycleOwner, ::updateAdapter)
        viewModel._networkResult.observe(viewLifecycleOwner, ::updateLoadingState)
    }

    private fun initializeListeners() {
        binding.updateListBtn.setOnClickListener {
            binding.postListFragmentLoadingView.showView()
            it.isClickable = false
            try {
                GlobalScope.launch(Dispatchers.IO) {
                    viewModel.getPostsFromNetwork()
                }
            } catch (e: Exception) {
            }
        }
    }

    private fun updateLoadingState(isSuccess: Boolean) {
        isSuccess.apply {
            binding.updateListBtn.isClickable = true
            if (this) binding.postListFragmentLoadingView.hideView()
            else {
                binding.postListFragmentLoadingView.hideView()
                Toast.makeText(requireContext(), "Network error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateAdapter(list: List<PostModel>) = listAdapter.setItems(list)

    private fun onImageClick(url: String?) {
        binding.mediaDetailFragment.showMediaDetail(requireActivity(), url, binding.updateListBtn)
        binding.updateListBtn.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}