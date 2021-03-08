package com.hakangeyik.apexapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hakangeyik.apexapp.model.Character
import com.hakangeyik.apexapp.service.CharacterDatabase
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(application: Application) : BaseViewModel(application) {

    val characterLiveData = MutableLiveData<Character>()

    fun getDataFromRoom(uuid:Int){

        launch {
            val dao = CharacterDatabase(getApplication()).characterDao()
            val character = dao.getCharacter(uuid)
            characterLiveData.value = character
        }

    }

}