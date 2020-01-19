package com.cardamon.dogs.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cardamon.dogs.model.DogBreed
import com.cardamon.dogs.model.DogDatabase
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : BaseViewModel(application) {
    val dogLiveData = MutableLiveData<DogBreed>()

    fun fetch() {
        val dog = DogBreed("1", "Corgi", "15 years", "breedGroup", "breedFor", "temperament", "")
        dogLiveData.value = dog

    }

    fun fetchByDogUuid(uuid: Int) {


        launch {
            val dog = DogDatabase(getApplication()).dogDao().getDog(uuid)

            dogLiveData.value = dog
            Toast.makeText(getApplication(), "From DB", Toast.LENGTH_SHORT).show()

        }


    }


}