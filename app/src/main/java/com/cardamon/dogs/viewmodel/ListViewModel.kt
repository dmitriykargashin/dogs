package com.cardamon.dogs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cardamon.dogs.model.DogBreed

class ListViewModel : ViewModel() {
    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsListError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        val dog1 = DogBreed("1", "Corgi", "15 years", "breedGroup", "breedFor", "temperament", "")
        val dog2 = DogBreed("2", "Kolli", "10 years", "breedGroup", "breedFor", "temperament", "")
        val dog3 =
            DogBreed("3", "Rotwailer", "20 years", "breedGroup", "breedFor", "temperament", "")
        val dogsList = arrayListOf(dog1, dog2, dog3)

        dogs.value = dogsList
        dogsListError.value = false
        loading.value = false

    }

}