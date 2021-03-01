package com.example.marvelproject.presentation.fragments.comicdetail

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.marvelproject.R
import com.example.marvelproject.base.BaseExtraData
import com.example.marvelproject.base.BaseFragment
import com.example.marvelproject.databinding.ComicDetailFragmentBinding

class ComicDetailFragment() :BaseFragment<ComicDetailState,ComicDetailViewModel,ComicDetailFragmentBinding>() {

    override val viewModelClass: Class<ComicDetailViewModel> = ComicDetailViewModel::class.java

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ComicDetailFragmentBinding
            = ComicDetailFragmentBinding::inflate

    private lateinit var vm: ComicDetailViewModel

    private val args: ComicDetailFragmentArgs by navArgs()

    override fun setupView(viewModel: ComicDetailViewModel) {
        vm=viewModel
        vm.requestInformation(args.comicId)
    }

    override fun onNormal(baseViewState: ComicDetailState) {
        binding.comicDetailFragmentTextViewName.text= baseViewState.comic?.title ?:
        getString(R.string.comicDetailFragmentTextNoTitle)
        binding.comicDetailFragmentTextDescription.text=baseViewState.comic?.description ?:
        getString(R.string.comicDetailFragmentTextNoDescription)
        binding.progressBar.visibility= View.GONE
    }

    override fun onLoading(dataLoading: BaseExtraData?) {
        binding.progressBar.visibility= View.VISIBLE

    }

    override fun onError(error: Throwable) {
        binding.comicDetailFragmentTextViewName.setTextColor(Color.RED)
        binding.comicDetailFragmentTextDescription.text= getString(R.string.error_message,error)
    }


}