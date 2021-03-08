package com.hakangeyik.apexapp.service

import com.hakangeyik.apexapp.model.Character
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharacterAPIService {
    //https://raw.githubusercontent.com/jedis19/datajson/main/mydata.json
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private val api = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CharacterAPI::class.java)

    fun getData() : Call<List<Character>>{
        return api.getCharacter()
    }

}