package com.example.effectivem2.vacancies.screens.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavoritesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is vacancies favorites Fragment"
    }
    val text: LiveData<String> = _text
}