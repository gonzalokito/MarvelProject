package com.example.marvelproject.presentation.fragments.characterlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvelproject.R
import com.example.marvelproject.base.BaseExtraData
import com.example.marvelproject.base.BaseState
import com.example.marvelproject.databinding.FragmentCharacterListBinding


class CharacterListFragment : Fragment() {

    private lateinit var binding: FragmentCharacterListBinding

    private lateinit var mAdapter: CharacterListAdapter

    private val viewModel:CharacterListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        binding= FragmentCharacterListBinding.inflate(layoutInflater,container,false)

        viewModel.getState().observe(viewLifecycleOwner,{ state ->

        when(state) {

            is BaseState.Normal -> {
                onNormal(state.dataNormal as CharacterListState)
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

        viewModel.requestInformation()
        return binding.root
    }

    private fun setupView() {

        //Setup RecyclerView
        mAdapter= CharacterListAdapter(listOf(),requireActivity()) {

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

                viewModel.onActionChangeSpinnerValue(parent.getItemAtPosition(position).toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }

    private fun onNormal(characterListState: CharacterListState) {
        mAdapter.updateList(characterListState.characterList)
    }

    private fun onLoading(dataLoading: BaseExtraData?) {

    }

    private fun onError(error: Throwable) {

    }



}