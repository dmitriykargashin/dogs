package com.cardamon.dogs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cardamon.dogs.model.DogBreed
import com.cardamon.dogs.model.DogsApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel : ViewModel() {

    private val dogsService = DogsApiService()
    private val disposable = CompositeDisposable()


    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsListError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchFromRemote()
    }

    private fun fetchFromRemote() {
        loading.value = true
        disposable.add(
            dogsService.getDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<DogBreed>>() {
                    override fun onSuccess(dogList: List<DogBreed>) {
                        dogs.value = dogList
                        loading.value = false
                        dogsListError.value = false
                    }

                    override fun onError(e: Throwable) {
                        dogsListError.value = true
                        loading.value = false
                        e.printStackTrace()

                    }
                })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}