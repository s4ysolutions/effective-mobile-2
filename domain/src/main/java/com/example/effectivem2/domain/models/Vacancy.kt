package com.example.effectivem2.domain.models

import com.example.effectivem2.data.dto.Id
import java.io.Serializable
import java.net.URL
import java.util.Date

class Vacancy(private val dto: com.example.effectivem2.data.dto.Vacancy,private val favorite: Boolean) : Serializable {
    internal val id: Id
        get() = dto.id
    val title: String
        get() = dto.title
    val link: URL?
        get() = dto.link
    val address: Address
        get() = Address(dto.address)
    val company: String
        get() = dto.company
    val experience: Experience
        get() = Experience(dto.experience)
    val publishedDate: Date?
        get() = dto.publishedDate
    val isFavorite: Boolean
        get() = favorite
    val salary: Salary
        get() = Salary(dto.salary)
    val schedules: List<String>
        get() = dto.schedules
    val appliedNumber: Number?
        get() = dto.appliedNumber
    val lookingNumber: Number?
        get() = dto.lookingNumber
    val responsibilities: String?
        get() = dto.responsibilities
    val description: String?
        get() = dto.description
    val questions: List<String>
        get() = dto.questions

    fun isSame(other: Vacancy): Boolean =
        other.id == id
}
