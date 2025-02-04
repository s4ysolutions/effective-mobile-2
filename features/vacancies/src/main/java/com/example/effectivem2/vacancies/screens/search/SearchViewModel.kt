package com.example.effectivem2.vacancies.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.effectivem2.domain.JobsService
import com.example.effectivem2.domain.models.Vacancy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val jobsService: JobsService) : ViewModel() {
    val offersLiveData =
        jobsService.queryOffers().onStart { emit(emptyArray()) }.asLiveData()

    val vacanciesLiveData = jobsService.vacanciesStateFlow.asLiveData()

    fun addVacancyToFavorites(vacancy: Vacancy) {
        viewModelScope.launch {
            jobsService.addFav(vacancy)
        }
    }

    fun removeVacancyFromFavorites(vacancy: Vacancy) {
        viewModelScope.launch {
            jobsService.removeFav(vacancy)
        }
    }

    init {
        viewModelScope.launch { jobsService.refreshVacancies() }
    }
}