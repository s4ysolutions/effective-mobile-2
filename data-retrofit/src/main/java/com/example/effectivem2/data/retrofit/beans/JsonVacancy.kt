package com.example.effectivem2.data.retrofit.beans

import com.example.effectivem2.data.retrofit.beans.conversions.toURL
import com.example.effectivem2.data.dto.UuidId
import com.example.effectivem2.data.dto.Vacancy
import com.example.effectivem2.data.retrofit.beans.conversions.toDate

class JsonVacancy(
    private val id: String,
    private val title: String,
    private val link: String?,
    private val address: JsonAddress,
    private val company: String,
    private val experience: JsonExperience,
    private val publishedDate: String,
    private val isFavorite: Boolean,
    private val salary: JsonSalary,
    private val schedules: MutableList<String>,
    private val appliedNumber: Number?,
    private val lookingNumber: Number?,
    private val responsibilities: String?,
    private val description: String?,
    private val questions: MutableList<String>,
) {
    val dto: Vacancy
        get() = Vacancy(
            UuidId(id),
            title,
            link?.toURL(),
            address.dto,
            company,
            experience.dto,
            publishedDate.toDate(),
            isFavorite,
            salary.dto,
            schedules,
            appliedNumber,
            lookingNumber,
            responsibilities,
            description,
            questions,
        )
}

