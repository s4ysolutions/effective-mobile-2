package com.example.effectivem2.data

import com.example.effectivem2.data.dto.Id
import com.example.effectivem2.data.dto.Offer
import com.example.effectivem2.data.dto.Vacancy
import io.reactivex.rxjava3.core.Single

interface JobsProvider {
    fun queryOffers(): Single<Array<Offer>>
    fun queryVacancies(): Single<Array<Vacancy>>
    fun queryVacancy(id: Id): Single<Vacancy>
}
