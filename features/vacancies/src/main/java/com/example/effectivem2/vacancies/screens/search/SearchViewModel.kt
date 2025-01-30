package com.example.effectivem2.vacancies.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is vacancies search Fragment"
    }
    val text: LiveData<String> = _text
}