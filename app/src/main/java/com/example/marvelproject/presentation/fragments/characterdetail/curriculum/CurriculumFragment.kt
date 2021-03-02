package com.example.marvelproject.presentation.fragments.characterdetail.curriculum

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.marvelproject.base.BaseExtraData
import com.example.marvelproject.base.BaseFragment
import com.example.marvelproject.base.BaseState
import com.example.marvelproject.data.NoCharacterException
import com.example.marvelproject.data.model.Character
import com.example.marvelproject.data.model.Item
import com.example.marvelproject.databinding.CharacterDetailFragmentBinding
import com.example.marvelproject.databinding.FragmentCurriculumBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurriculumFragment (private val items: List<Item>,
                          private val showButton:Boolean=false,
                          private val myListener: (comic: String) -> Unit) : Fragment() {

    lateinit var binding: FragmentCurriculumBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding= FragmentCurriculumBinding.inflate(layoutInflater,container,false)

        setupView()

        return binding.root
    }

    private fun setupView() {

        val listToShow: MutableList<String> = mutableListOf()
        items.forEach{
            listToShow.add(it.name)
        }

        binding.fragmentCharacterListRV.apply {
            adapter=CharacterDetailRecyclerViewAdapter(listToShow,showButton,myListener)
            itemAnimator=DefaultItemAnimator()
            layoutManager=LinearLayoutManager(requireActivity())
        }
    }


}