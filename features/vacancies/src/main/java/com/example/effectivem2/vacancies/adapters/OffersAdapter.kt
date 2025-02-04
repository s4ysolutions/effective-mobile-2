package com.example.effectivem2.vacancies.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivem2.domain.models.Offer
import com.example.effectivem2.domain.models.OfferType
import com.example.effectivem2.vacancies.R
import com.google.android.material.textview.MaterialTextView
import java.net.URL

class OffersAdapter(
    offers: Array<Offer>,
) : RecyclerView.Adapter<OffersAdapter.OfferViewHolder>() {

    private val offers = mutableListOf(*offers)

    @SuppressLint("NotifyDataSetChanged")
    fun setOffers(offers: Array<Offer>) {
        this.offers.clear()
        this.offers.addAll(offers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.listitem_offer, parent, false)
        return OfferViewHolder(view)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val offer = offers[position]
        holder.bind(offer)
    }

    override fun getItemCount(): Int =
        offers.size

    inner class OfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val view = itemView
        private val context = itemView.context
        private val typeIconView: ImageView = itemView.findViewById(R.id.type)
        private val titleTextView: MaterialTextView = itemView.findViewById(R.id.title)
        private val buttonTextView: MaterialTextView = itemView.findViewById(R.id.button)

        fun bind(offer: Offer) {
            titleTextView.text = offer.title
            buttonTextView.text = offer.buttonText ?: ""
            typeIconView.setImageResource(when (offer.type) {
                OfferType.NEAR_VACANCY -> R.drawable.offer_location
                OfferType.TEMPORARY_JOB -> R.drawable.offer_check
                OfferType.LEVEL_UP_RESUME -> R.drawable.offer_star
                else -> 0
            })

            view.setOnClickListener {
                offer.link?.let { url ->
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url.toString()))
                    context.startActivity(intent)
                }
            }

        }
    }
}