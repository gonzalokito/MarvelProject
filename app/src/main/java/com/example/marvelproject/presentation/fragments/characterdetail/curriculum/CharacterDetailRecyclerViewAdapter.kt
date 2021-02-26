package com.example.marvelproject.presentation.fragments.characterdetail.curriculum


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelproject.databinding.ItemCurriculumBinding


class CharacterDetailRecyclerViewAdapter (private var myList: List<String>): RecyclerView.Adapter<CharacterDetailRecyclerViewAdapter.ViewHolder>() {


    class ViewHolder(val binding: ItemCurriculumBinding): RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view=ItemCurriculumBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item= myList[position]
        holder.binding.apply {
            itemCurriculumTextView.text = item
        }

    }

    override fun getItemCount()= myList.size

}