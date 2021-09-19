package com.example.mvvm_template.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm_template.R
import com.example.mvvm_template.databinding.ItemCharacterBinding
import com.example.mvvm_template.model.Character

class CharacterAdapter(private val onCharacterClickListener: OnCharacterClickListener) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {
    private var characters = ArrayList<Character>()

    inner class CharacterViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context))
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.binding.characterName.text = characters[position].name

        Glide.with(holder.itemView.context)
            .load(characters[position].img)
            .placeholder(R.drawable.ic_baseline_person_24)
            .centerCrop()
            .into(holder.binding.characterImage)

        holder.itemView.setOnClickListener {
            onCharacterClickListener.onCharacterClick(characters[position].char_id)
        }

    }

    override fun getItemCount(): Int {
        return characters.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(new_characters: ArrayList<Character>) {
        characters = new_characters
        notifyDataSetChanged()
    }


    interface OnCharacterClickListener {
        fun onCharacterClick(character_id: Int)
    }
}