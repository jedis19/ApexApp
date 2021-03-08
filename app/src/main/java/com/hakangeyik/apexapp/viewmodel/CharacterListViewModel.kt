package com.hakangeyik.apexapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hakangeyik.apexapp.model.Character
import com.hakangeyik.apexapp.service.CharacterAPIService
import com.hakangeyik.apexapp.service.CharacterDatabase
import com.hakangeyik.apexapp.utill.CustomSharedPref
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterListViewModel(application: Application) : BaseViewModel(application) {

    private var characterAPIService = CharacterAPIService()
    private var customPreferences = CustomSharedPref(getApplication())
    private var refreshTime = 10*60*1000*1000*1000L // 10 dakikayı nano saniye yapma


    val characters = MutableLiveData<List<Character>>()
    val characterError = MutableLiveData<Boolean>()
    val characterLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        val updateTime = customPreferences.getTime()
         if (updateTime !=null && updateTime !=0L && System.nanoTime()-updateTime<refreshTime) {
            getDataFromSQLite()

         }else{
             getDataFromAPI()
         }


    }

    fun refreshDataFromApi(){
        getDataFromAPI()
    }

    private fun getDataFromSQLite(){
        characterLoading.value = true
        launch {
            val characters = CharacterDatabase(getApplication()).characterDao().getAllCharacters()
            showCharacters(characters)
            Toast.makeText(getApplication(),"Characters From SQLite",Toast.LENGTH_LONG).show()
        }
    }

    private fun getDataFromAPI(){

        characterLoading.value = true

        characterAPIService.getData().enqueue(object : Callback<List<Character>>{
            override fun onResponse(
                call: Call<List<Character>>,
                response: Response<List<Character>>
            ) {
                if (response.isSuccessful){
                    response.body()?.let{
                        saveInSQLite(it)
                        Toast.makeText(getApplication(),"Characters From API",Toast.LENGTH_LONG).show()
                    }
                }

            }

            override fun onFailure(call: Call<List<Character>>, t: Throwable) {
                characterLoading.value = false
                characterError.value = false
                t.printStackTrace()
            }

        })



    }

    private fun showCharacters(characterList:List<Character>){
        characters.value = characterList
        characterError.value = false
        characterLoading.value = false
    }

    private fun saveInSQLite(list:List<Character>){

        launch {
            val dao = CharacterDatabase(getApplication()).characterDao()
            dao.deleteAllCharacters()

            val listLong = dao.insertAll(*list.toTypedArray())//Var arg'a çevirmek için ! yani tekil hale getiriyor
            var i = 0
            while(i<listLong.size){
                list[i].uuid = listLong[i].toInt()
                i++
            }
            showCharacters(list)
        }
        customPreferences.saveTime(System.nanoTime())
    }

}