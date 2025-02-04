package com.example.effectivem2.data.retrofit.beans

import com.example.effectivem2.data.dto.OfferType
import com.example.effectivem2.data.retrofit.beans.conversions.toURL
import com.example.effectivem2.data.dto.StringId
import com.example.effectivem2.data.dto.Offer
import kotlin.random.Random
import kotlin.random.nextULong

class JsonOffer(
    private val id: String?,
    private val title: String,
    private val link: String,
    private val button: JsonButton? = null
) {
    val dto get() = Offer(
        StringId(id ?:Random.nextULong().toString()),
        when(id) {
            "near_vacancies" -> OfferType.NEAR_VACANCY
            "level_up_resume" -> OfferType.LEVEL_UP_RESUME
            "temporary_job" -> OfferType.TEMPORARY_JOB
            else -> OfferType.NONE
        },
        title,
        link.toURL(),
        button?.text
    )
}