package com.hakangeyik.apexapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.hakangeyik.apexapp.R
import com.hakangeyik.apexapp.model.Character
import com.hakangeyik.apexapp.utill.getImageFromApi
import com.hakangeyik.apexapp.utill.placeHolderProgressBar
import com.hakangeyik.apexapp.view.CharacterListFragmentDirections
import kotlinx.android.synthetic.main.item_character.view.*

class CharacterAdapter(val characterList: ArrayList<Character>) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    class CharacterViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_character,parent,false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.view.listFragmentCharacterName.text = characterList[position].characterName
        holder.view.listFragmentCharacterRealName.text = characterList[position].characterRealName

        holder.view.setOnClickListener {
            val action = CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailsFragment(characterList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
        holder.view.listFragmentImageView.getImageFromApi(characterList[position].characterImage,
            placeHolderProgressBar(holder.view.context))
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    fun updateCharacterList(newCharacterList: List<Character>) {
        characterList.clear()
        characterList.addAll(newCharacterList)
        notifyDataSetChanged()
    }

}