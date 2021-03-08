package com.hakangeyik.apexapp.service

import com.hakangeyik.apexapp.model.Character
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface CharacterAPI {
    //https://raw.githubusercontent.com/jedis19/datajson/main/mydata.json
    @GET("jedis19/datajson/main/mydata.json")
    fun getCharacter():Call<List<Character>>
}