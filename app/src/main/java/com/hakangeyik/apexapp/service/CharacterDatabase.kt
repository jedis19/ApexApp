package com.hakangeyik.apexapp.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hakangeyik.apexapp.model.Character

@Database(entities = arrayOf(Character::class),version = 1)
abstract class CharacterDatabase : RoomDatabase() {

    abstract fun characterDao():CharacterDAO

    companion object{
        //Volatile tüm threadlere görünür hale getiriyor
        @Volatile private var instance:CharacterDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) =  instance?: synchronized(lock){
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context:Context) = Room.databaseBuilder(context.applicationContext,CharacterDatabase::class.java,"characterdatabase")
                .build()
    }
}