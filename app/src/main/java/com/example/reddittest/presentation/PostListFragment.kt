package com.example.reddittest.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reddittest.databinding.FragmentPostListBinding
import com.example.reddittest.model.PostModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PostListFragment : Fragment() {

    companion object {
        val TAG: String = PostListFragment::class.java.simpleName
    }

    private var _binding: FragmentPostListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: PostListViewModel
    private val listAdapter = PostListAdapter()

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

        initializeRecycler()
        initializeResource()
        initializeObservers()
        initializeListeners()
    }

    private fun initializeRecycler() = with(binding.postListRecycler) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = listAdapter
    }

    private fun initializeResource() {
        viewModel = ViewModelProvider(requireActivity()).get(PostListViewModel::class.java)
    }

    private fun initializeObservers() {
        viewModel.getAllPosts().observe(viewLifecycleOwner, ::updateAdapter)
    }

    private fun initializeListeners() {
        binding.updateListBtn.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                viewModel.getPostsFromNetwork()
            }
        }
    }

    private fun updateAdapter(list: List<PostModel>) = listAdapter.setItems(list)

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}