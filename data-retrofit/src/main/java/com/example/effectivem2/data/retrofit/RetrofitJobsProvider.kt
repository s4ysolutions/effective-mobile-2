package com.example.effectivem2.data.retrofit

import android.content.Context
import com.example.effectivem2.data.JobsProvider
import com.example.effectivem2.data.dto.Id
import com.example.effectivem2.data.dto.Offer
import com.example.effectivem2.data.dto.Vacancy
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitJobsProvider(context: Context) : JobsProvider {
    private val retrofitClient = RetrofitClient.getInstance(context)

    override fun queryOffers(): Single<Array<Offer>> =
        retrofitClient.getOffers().subscribeOn(Schedulers.io()).map { jsonOffers ->
            Array(jsonOffers.size) { index ->
                jsonOffers[index].dto
            }
        }.delay(DELAY, java.util.concurrent.TimeUnit.MILLISECONDS)

    override fun queryVacancies(): Single<Array<Vacancy>> =
        retrofitClient.getVacancies().subscribeOn(Schedulers.io())
            .map { jsonVacancies ->
                Array(jsonVacancies.size) { index ->
                    jsonVacancies[index].dto
                }
            }.delay(DELAY, java.util.concurrent.TimeUnit.MILLISECONDS)

    override fun queryVacancy(id: Id): Single<Vacancy> =
        queryVacancies().flatMap { vacancies ->
            val vacancy = vacancies.firstOrNull { it.id == id }
            if (vacancy != null) {
                Single.just(vacancy)
            } else {
                Single.error(NoSuchElementException("No vacancy found with id: $id"))
            }
        }

    companion object {
        private val DELAY get() = 500L + (System.currentTimeMillis() % 10) * 50L
    }
}