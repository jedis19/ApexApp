package com.hakangeyik.apexapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hakangeyik.apexapp.R
import com.hakangeyik.apexapp.utill.getImageFromApi
import com.hakangeyik.apexapp.utill.placeHolderProgressBar
import com.hakangeyik.apexapp.viewmodel.CharacterDetailsViewModel
import kotlinx.android.synthetic.main.fragment_character_details.*


class CharacterDetailsFragment : Fragment() {

    private lateinit var viewModel : CharacterDetailsViewModel
    private var characterUuid =0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let{
            characterUuid = CharacterDetailsFragmentArgs.fromBundle(it).uuid
        }

        viewModel = ViewModelProviders.of(this).get(CharacterDetailsViewModel::class.java)
        viewModel.getDataFromRoom(characterUuid)

        observeLiveData()

    }

    private fun observeLiveData(){
        viewModel.characterLiveData.observe(viewLifecycleOwner, Observer { character ->

            character?.let{
                characterDetailsName.text = character.characterName
                characterDetailsRealName.text = character.characterRealName
                characterDetailsAge.text = character.characterAge
                characterDetailsHomePlanet.text = character.characterHomePlanet
                characterDetailsTacticalAbility.text = character.characterTacticalAbility
                characterDetailsPassiveAbility.text = character.characterPassiveAbility
                characterDetailsUltimateAbility.text = character.characterUltimateAbility
                context?.let{
                    characterDetailsImageView.getImageFromApi(character.characterImage, placeHolderProgressBar(it))
                }


            }

        })
    }


}