package com.example.marvelproject.presentation.fragments.characterlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.marvelproject.databinding.FragmentCharacterListBinding


class CharacterListFragment : Fragment() {

    lateinit var binding: FragmentCharacterListBinding

    val viewModel:CharacterListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentCharacterListBinding.inflate(layoutInflater,container,false)

        viewModel.requestInformation()
        return binding.root
    }

}