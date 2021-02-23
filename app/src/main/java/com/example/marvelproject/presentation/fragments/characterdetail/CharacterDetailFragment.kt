package com.example.marvelproject.presentation.fragments.characterdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.marvelproject.base.BaseExtraData
import com.example.marvelproject.base.BaseState
import com.example.marvelproject.data.NoCharacterException
import com.example.marvelproject.databinding.CharacterDetailFragmentBinding

class CharacterDetailFragment : Fragment() {


    private val viewModel: CharacterDetailViewModel by viewModels()

    lateinit var binding: CharacterDetailFragmentBinding

    private val args: CharacterDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= CharacterDetailFragmentBinding.inflate(layoutInflater,container,false)

        viewModel.getState().observe(viewLifecycleOwner,{ state ->

            when(state) {

                is BaseState.Normal -> {
                    onNormal(state.dataNormal as CharacterDetailState)
                }

                is BaseState.Error -> {
                    onError(state.error)
                }

                is BaseState.Loading -> {
                    onLoading(state.dataLoading)
                }
            }
        })

        setupView()

        viewModel.requestInformation(args.characterId)

        return binding.root
    }

    private fun onError(error: Throwable) {

        when(error){
            is NoCharacterException -> {

            }
            else ->{

            }
        }
    }

    private fun onLoading(dataLoading: BaseExtraData?) {

    }

    private fun onNormal(characterDetailState: CharacterDetailState) {

        characterDetailState.character?.let{ character ->
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

            //binding.webview.loadUrl(url.url)
            }

        }
    }

    private fun setupView() {

    }


}