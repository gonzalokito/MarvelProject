package com.example.marvelproject.presentation.fragments.characterdetail.curriculum

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelproject.databinding.ItemCurriculumBinding

class CharacterDetailRecyclerViewAdapter (private var myList: List<String>,
                                          private val showButton:Boolean=false,
                                          private val myListener: (comic: String) -> Unit)
    : RecyclerView.Adapter<CharacterDetailRecyclerViewAdapter.ViewHolder>() {


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
            itemCurriculumButtonSee.visibility = if (showButton) View.VISIBLE else View.GONE
            itemCurriculumButtonSee.setOnClickListener {
                myListener.invoke(item)
            }
        }

    }

    override fun getItemCount()= myList.size

}