package com.example.marvelproject.presentation.fragments.characterlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvelproject.R
import com.example.marvelproject.base.BaseExtraData
import com.example.marvelproject.base.BaseFragment
import com.example.marvelproject.base.BaseState
import com.example.marvelproject.databinding.FragmentCharacterListBinding
import com.example.marvelproject.presentation.fragments.characterdetail.CharacterDetailFragmentArgs


class CharacterListFragment() : BaseFragment<CharacterListState,CharacterListViewModel,FragmentCharacterListBinding>() {

    lateinit var mAdapter: CharacterListAdapter

    override val viewModelClass= CharacterListViewModel::class.java

    lateinit var vm: CharacterListViewModel



    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCharacterListBinding
            = FragmentCharacterListBinding::inflate

    override fun setupView(viewModel: CharacterListViewModel) {

        vm= viewModel
        //Setup RecyclerView
        mAdapter= CharacterListAdapter(listOf(),requireActivity()) { character->
            findNavController().navigate(CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(character.id))
        }

        binding.fragmentCharacterListRV.apply {
            adapter = mAdapter
            layoutManager=GridLayoutManager(requireActivity(),2)
            itemAnimator= DefaultItemAnimator()
        }

        //Setup Spinner

        ArrayAdapter.createFromResource(
                requireActivity(), R.array.fragment_character_list_spinner_array,android.R.layout.simple_spinner_item
        ).also { spinnerAdapter ->
            // Specify the layout to use when the list of choices appears
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.spinner.adapter = spinnerAdapter
        }

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int,id: Long) {

                if (position == 0){
                    vm.onActionChangeSpinnerValue("20")
                }else {
                    vm.onActionChangeSpinnerValue(parent.getItemAtPosition(position).toString()
                    )
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }

    override fun onLoading(dataLoading: BaseExtraData?) {

    }

    override fun onError(error: Throwable) {

    }

    override fun onNormal(data: CharacterListState) {
        mAdapter.updateList(data.characterList)
    }


}