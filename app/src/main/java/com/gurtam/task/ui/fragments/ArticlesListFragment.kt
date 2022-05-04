package com.gurtam.task.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.gurtam.task.databinding.FragmentArticlesListBinding
import com.gurtam.task.models.ArticleUI
import com.gurtam.task.ui.adapters.ArticlesPagingDataAdapter
import com.gurtam.task.ui.viewmodels.ArticlesListViewModel
import com.gurtam.task.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticlesListFragment : Fragment() {

    private var mBinding: FragmentArticlesListBinding? = null
    private val mArticlesListViewModel by viewModels<ArticlesListViewModel>()
    private var mArticlesPagingAdapter: ArticlesPagingDataAdapter? = null
    private val args by navArgs<ArticlesListFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mBinding == null) {
            mArticlesListViewModel.fetchArticles(args.newsSource.id)
            mBinding = FragmentArticlesListBinding.inflate(inflater)
            setupArticlesList()
            setupArticlesPagingDataObserver()
        }
        return mBinding?.root
    }

    private fun setupArticlesList() {
        mArticlesPagingAdapter = ArticlesPagingDataAdapter(::onArticleItemClick)
        mBinding?.rvArticles?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mArticlesPagingAdapter
        }
    }

    private fun onArticleItemClick(article: ArticleUI) {
        val action = ArticlesListFragmentDirections
            .actionNewsListFragmentToSingleNewsFragment(article, args.newsSourceName)
        findNavController().navigate(action)
    }

    private fun setupArticlesPagingDataObserver() {
        mArticlesListViewModel.articlePagingDataLiveData.observe(viewLifecycleOwner) { resource ->
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                when (resource) {
                    is Resource.Success -> {
                            mArticlesPagingAdapter?.submitData(resource.data)
                    }

                    is Resource.Error -> {
                        Toast.makeText(requireContext(), resource.message, Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
    }
}