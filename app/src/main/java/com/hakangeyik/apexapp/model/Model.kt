package com.hakangeyik.apexapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Character(

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val characterName:String?,

    @ColumnInfo(name = "realname")
    @SerializedName("realname")
    val characterRealName:String?,

    @ColumnInfo(name = "age")
    @SerializedName("age")
    val characterAge:String?,

    @ColumnInfo(name = "homeplanet")
    @SerializedName("homeplanet")
    val characterHomePlanet:String?,

    @ColumnInfo(name = "tacticalability")
    @SerializedName("tacticalability")
    val characterTacticalAbility:String?,

    @ColumnInfo(name = "passiveability")
    @SerializedName("passiveability")
    val characterPassiveAbility:String?,

    @ColumnInfo(name = "ultimateability")
    @SerializedName("ultimateability")
    val characterUltimateAbility:String?,
    @ColumnInfo(name = "image")
    @SerializedName("image")
    val characterImage:String?
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}
