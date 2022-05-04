package com.gurtam.task.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.gurtam.task.databinding.FragmentArticleSingleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleArticleFragment : Fragment() {

    private var mBinding: FragmentArticleSingleBinding? = null
    private val args by navArgs<SingleArticleFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(mBinding == null) {
            mBinding = FragmentArticleSingleBinding.inflate(inflater).apply {
                article = args.article
                textArticleSourceLink.setOnClickListener { openSourcePageInBrowser() }
            }
        }
        return mBinding?.root
    }

    private fun openSourcePageInBrowser() {
        try {
            val sourceUrl = args.article.url
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(sourceUrl)
            }
            startActivity(intent)
        } catch (e: Exception){
            Toast.makeText(requireContext(), "Cannot open source page.", Toast.LENGTH_LONG).show()
        }
    }

}