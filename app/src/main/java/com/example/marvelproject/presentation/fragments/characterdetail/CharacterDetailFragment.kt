package com.example.marvelproject.presentation.fragments.characterdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.marvelproject.base.BaseExtraData
import com.example.marvelproject.base.BaseFragment
import com.example.marvelproject.base.BaseState
import com.example.marvelproject.data.NoCharacterException
import com.example.marvelproject.databinding.CharacterDetailFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment() : BaseFragment<CharacterDetailState,CharacterDetailViewModel,CharacterDetailFragmentBinding>() {

    override val viewModelClass= CharacterDetailViewModel::class.java

    lateinit var vm: CharacterDetailViewModel

    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun setupView(viewModel: CharacterDetailViewModel) {
        vm=viewModel
        vm.requestInformation(args.characterId)
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> CharacterDetailFragmentBinding
            = CharacterDetailFragmentBinding::inflate


    override fun onLoading(dataLoading: BaseExtraData?) {

    }

    override fun onError(error: Throwable) {
        when(error){
            is NoCharacterException -> {
            }
            else ->{
            }
        }
    }

    override fun onNormal(baseViewState: CharacterDetailState) {
        baseViewState.character?.let{ character ->
            binding.textViewTitle.text=character.name
            binding.textViewDescription.text=character.description

            character.urls.firstOrNull()?.let{ url ->
                binding.webview.loadUrl(url.url.replace("http","https"))
                binding.webview.webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                        view.loadUrl(url)
                        return true
                    }
                }

                val viewPager = binding.pager
                val pagerAdapter = CharacterDetailViewPageAdapter(this, character){
                    findNavController().navigate(CharacterDetailFragmentDirections.actionCharacterDetailFragmentToComicDetailFragment(it))
                }
                viewPager.adapter = pagerAdapter

                TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->
                    when(position){
                        0->{tab.text="Comics"}
                        1->{tab.text="Series"}
                        2->{tab.text="Stories"}
                        else->{tab.text=""}
                    }
                }.attach()

            }

        }
    }


}