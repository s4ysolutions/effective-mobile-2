package com.example.effectivem2.data.dto

import java.net.URL
import java.util.Date

class Vacancy(
    val id: Id,
    val title: String,
    val link: URL?,
    val address: Address,
    val company: String,
    val experience: Experience,
    val publishedDate: Date?,
    val isFavorite: Boolean,
    val salary: Salary,
    val schedules: List<String>,
    val appliedNumber: Number?,
    val lookingNumber: Number?,
    val responsibilities: String?,
    val description: String?,
    val questions: List<String>,
)