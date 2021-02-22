package com.example.marvelproject.presentation.fragments.characterlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelproject.R
import com.example.marvelproject.data.model.Character
import com.example.marvelproject.databinding.ItemCharacterListBinding


class CharacterListAdapter (private var myList: List<Character>, private var context: Context, private val listener: (item: Character) -> Unit ): RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {


    class ViewHolder(val binding: ItemCharacterListBinding): RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view=ItemCharacterListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item= myList[position]
        holder.binding.apply {
            itemCharacterListTextViewName.text = item.name

            var urlerror = "https://image.freepik.com/free-vector/page-found-error-404_23-2147508324.jpg"

            var url = item.thumbnail.path.replace("http","https")+"."+item.thumbnail.extension
            Glide
                    .with(context)
                    .load(url ?: urlerror)
                    .centerCrop()
                    .placeholder(R.drawable.ic_sand_clock).
                    into(itemCharacterListImageViewLogo)


        }

        holder.itemView.setOnClickListener {
            listener.invoke(item)
        }

    }

    override fun getItemCount()= myList.size

    fun updateList (newList: List<Character>){
        myList = newList
        notifyDataSetChanged()
    }
}