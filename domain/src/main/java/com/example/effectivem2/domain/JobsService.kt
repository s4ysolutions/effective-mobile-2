package com.example.effectivem2.domain

import com.example.effectivem2.data.FavsProvider
import com.example.effectivem2.data.JobsProvider
import com.example.effectivem2.domain.models.Offer
import com.example.effectivem2.domain.models.Vacancy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.rx3.asFlow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * It is over-engineered for sake of demo purposes.
 *
 * It is suggested JobsProvider has RxJava API
 * and FavsProvider has coroutines API.
 *
 * The service unify both APIs and provides a Coroutines/Flow-based API.
 *
 * Instead of having a separate isFavorite API forwarded to FavsProvider
 * the results of REST Jobs API and local database API are combined
 * into the only Vacancy (list of) model that is exposed via StateFlow
 * that is easy to observe by a service consumer.
 *
 * This might be inefficient if many vacancies are fetched but it is inefficient
 * in its own and should be avoided anyway.
 *
 * Arrays are used because of more efficient memory usage
 * List are used for simplicity and readability.
 */

@Singleton
class JobsService @Inject constructor(
    private val jobsProvider: JobsProvider,
    private val favsProvider: FavsProvider
) {
    private val _vacanciesStateFlow = MutableStateFlow(emptyList<Vacancy>())
    private val _hasFavoritesStateFlow = MutableStateFlow<Int>(0)

    val vacanciesStateFlow: StateFlow<List<Vacancy>> = _vacanciesStateFlow
    val hasFavoritesStateFlow: StateFlow<Int> = _hasFavoritesStateFlow

    fun queryOffers(): Flow<Array<Offer>> = jobsProvider.queryOffers().map {
        Array(it.size) { index -> Offer(it[index]) }
    }.toObservable().asFlow()

    /*
     * The methods belo can be used both as a launch-and-forget due to its side-effect
     * of updating the state of vacanciesStateFlow and as a returnable value
     */

    suspend fun addFav(vacancy: Vacancy): List<Vacancy> {
        favsProvider.addFav(vacancy.id)
        return refreshVacancies()
    }

    suspend fun removeFav(vacancy: Vacancy): List<Vacancy> {
        favsProvider.removeFav(vacancy.id)
        return refreshVacancies()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun refreshVacancies(): List<Vacancy> {
        val vacancies = jobsProvider.queryVacancies()
            .toObservable()
            .asFlow()
            .flatMapLatest { dtoVacancies ->
                flow {
                    val vacancies = dtoVacancies.map { dto ->
                        Vacancy(dto, favsProvider.isFav(dto.id))
                    }
                    emit(vacancies)
                }
            }.first()
        _vacanciesStateFlow.value = vacancies
        _hasFavoritesStateFlow.value = vacancies.filter { it.isFavorite }.size
        return vacancies
    }

    /* kept for test/debug purposes
    companion object {
        private var instance: JobsService? = null
        fun getSingleton(jobsProvider: JobsProvider): JobsService =
            instance ?: run {
                instance = JobsService(jobsProvider)
                getSingleton(jobsProvider)
            }
    }
     */
}