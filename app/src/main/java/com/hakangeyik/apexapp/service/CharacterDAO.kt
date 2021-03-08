package com.hakangeyik.apexapp.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.hakangeyik.apexapp.model.Character

@Dao
interface CharacterDAO {
    //database access işlemleri burda yapılıcak

    @Insert
    suspend fun insertAll(vararg character:Character):List<Long>
    
    @Query("SELECT * FROM character")
    suspend fun getAllCharacters():List<Character>

    @Query("SELECT * FROM character WHERE uuid = :characterId")
    suspend fun getCharacter(characterId:Int):Character

    @Query("DELETE FROM character")
    suspend fun deleteAllCharacters()
}