package com.example.effectivem2.vacancies.screens

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
class MainViewModel @Inject constructor(private val jobsService: JobsService) : ViewModel() {

    val hasFavoritesLiveData = jobsService.hasFavoritesStateFlow.asLiveData()

    init {
        viewModelScope.launch { jobsService.refreshVacancies() }
    }
}