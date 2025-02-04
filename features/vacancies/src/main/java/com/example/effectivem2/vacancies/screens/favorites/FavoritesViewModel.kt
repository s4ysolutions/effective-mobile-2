package com.example.effectivem2.vacancies.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.effectivem2.domain.JobsService
import com.example.effectivem2.domain.models.Vacancy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val jobsService: JobsService) : ViewModel() {

    val vacanciesLiveData =
        jobsService.vacanciesStateFlow.map { it.filter { v -> v.isFavorite } }.asLiveData()

    fun removeVacancyFromFavorites(vacancy: Vacancy) {
        viewModelScope.launch {
            jobsService.removeFav(vacancy)
        }
    }

    init {
        viewModelScope.launch { jobsService.refreshVacancies() }
    }
}