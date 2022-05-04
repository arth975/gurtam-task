package com.gurtam.task.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gurtam.task.R
import com.gurtam.task.databinding.FragmentNewsSourcesBinding
import com.gurtam.task.models.NewsSourceUI
import com.gurtam.task.ui.adapters.NewsSourceAdapter
import com.gurtam.task.ui.viewmodels.NewsSourcesViewModel
import com.gurtam.task.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsSourcesFragment : Fragment() {

    private var mBinding: FragmentNewsSourcesBinding? = null
    private val mNewsSourcesViewModel by viewModels<NewsSourcesViewModel>()
    private lateinit var mNewsSourceAdapter: NewsSourceAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mBinding == null) {
            mBinding = FragmentNewsSourcesBinding.inflate(inflater)
            setupNewsSourcesList()
            setupNewsSourcesObserver()
            mNewsSourcesViewModel.fetchNewsSources()
        }

        return mBinding?.root
    }

    private fun setupNewsSourcesObserver() {
        mNewsSourcesViewModel.newsSourceLiveData.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success<List<NewsSourceUI>> -> {
                    mNewsSourceAdapter.newsSources = resource.data
                }
                is Resource.Error -> {
                    /*Toast.makeText(requireContext(), resource.message, Toast.LENGTH_LONG).show()*/
                }
            }
        }
    }

    private fun setupNewsSourcesList() {
        mNewsSourceAdapter = NewsSourceAdapter(::onNewsSourceItemClick)
        mBinding?.rvNewsSources?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mNewsSourceAdapter
        }
    }

    private fun onNewsSourceItemClick(newsSource: NewsSourceUI) {
        val action = NewsSourcesFragmentDirections.actionNewsSourcesFragmentToNewsListFragment(
            newsSource = newsSource,
            newsSourceName = newsSource.name ?: ""
        )
        findNavController().navigate(action)
    }
}