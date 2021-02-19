package com.example.marvelproject.characterlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marvelproject.databinding.FragmentCharacterListBinding


class CharacterListFragment : Fragment() {

    lateinit var binding: FragmentCharacterListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentCharacterListBinding.inflate(layoutInflater,container,false)

        return binding.root
    }

}