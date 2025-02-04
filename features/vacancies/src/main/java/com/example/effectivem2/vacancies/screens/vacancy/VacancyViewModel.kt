package com.example.effectivem2.vacancies.screens.vacancy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.effectivem2.domain.JobsService
import com.example.effectivem2.domain.models.Vacancy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VacancyViewModel @Inject constructor(private val jobsService: JobsService) : ViewModel() {
    val vacanciesLiveData = jobsService.vacanciesStateFlow.asLiveData()

    fun toggleVacancyFavorite(vacancy: Vacancy) {
        viewModelScope.launch {
            if (vacancy.isFavorite)
                jobsService.removeFav(vacancy)
            else
                jobsService.addFav(vacancy)
        }
    }
}