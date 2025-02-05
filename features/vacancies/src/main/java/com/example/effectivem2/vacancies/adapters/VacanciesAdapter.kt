package com.example.effectivem2.vacancies.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivem2.domain.models.Vacancy
import com.example.effectivem2.vacancies.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import java.text.SimpleDateFormat
import java.util.Locale


class VacanciesAdapter(
    vacancies: Array<Vacancy>,
    private val onFavoritesClick: (Vacancy) -> Unit,
    private val onRespondClick: (Vacancy) -> Unit,
    private val onVacancyClick: (Vacancy) -> Unit,
) : RecyclerView.Adapter<VacanciesAdapter.VacancyViewHolder>() {

    private val vacancies = mutableListOf(*vacancies)

    @SuppressLint("NotifyDataSetChanged")
    fun setVacancies(vacancies: List<Vacancy>) {
        this.vacancies.clear()
        this.vacancies.addAll(vacancies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.listitem_vacancy, parent, false)
        return VacancyViewHolder(view)
    }

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        val vacancy = vacancies[position]
        holder.bind(vacancy)
    }

    override fun getItemCount(): Int =
        vacancies.size

    inner class VacancyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val root = itemView
        private val context = itemView.context
        private val favoriteImageView: ImageView = itemView.findViewById(R.id.favorite)
        private val currentlyViewingTextView: MaterialTextView =
            itemView.findViewById(R.id.currently_viewing)
        private val titleTextView: MaterialTextView = itemView.findViewById(R.id.title)
        private val salaryTextView: MaterialTextView = itemView.findViewById(R.id.salary)
        private val addressTextView: MaterialTextView = itemView.findViewById(R.id.address)
        private val employerTextView: MaterialTextView = itemView.findViewById(R.id.employer)
        private val employerCheckImageView: ImageView =
            itemView.findViewById(R.id.id_employer_check)
        private val requirementsTextView: MaterialTextView =
            itemView.findViewById(R.id.requirements)
        private val requirementsIconImageView: ImageView =
            itemView.findViewById(R.id.image_requirements)
        private val dateTextView: MaterialTextView = itemView.findViewById(R.id.date)
        private val respondButton: MaterialButton = itemView.findViewById(R.id.respond)

        fun bind(vacancy: Vacancy) {
            // Bind title and other essential information
            titleTextView.text = vacancy.title
            salaryTextView.text = vacancy.salary.short ?: vacancy.salary.full ?: ""
            addressTextView.text = vacancy.address.town
            employerTextView.text = vacancy.company
            // TODO: resources
            requirementsTextView.text =
                "Опыт " + vacancy.experience.text
            val dt = vacancy.publishedDate
            if (dt != null) {
                dateTextView.text = "Опубликовано " + dateFormat.format(dt)
                dateTextView.visibility = View.VISIBLE
            } else {
                dateTextView.visibility = View.GONE
            }

            // TODO: label
            currentlyViewingTextView.text =
                if (vacancy.appliedNumber == null || vacancy.appliedNumber == 0)
                    "Никто не просматривает"
                else
                    context.resources.getQuantityString(
                        R.plurals.currently_viewing,
                        vacancy.appliedNumber?.toInt() ?: 0,
                        vacancy.appliedNumber?.toInt() ?: 0
                    )

            if (vacancy.isFavorite) {
                favoriteImageView.setImageResource(com.example.effectivem2.views.R.drawable.favorites_fill)
            } else {
                favoriteImageView.setImageResource(com.example.effectivem2.views.R.drawable.favorites)
            }

            favoriteImageView.setOnClickListener {
                onFavoritesClick(vacancy)
            }

            root.setOnClickListener {
                onVacancyClick(vacancy)
            }

            respondButton.setOnClickListener {
                onRespondClick(vacancy)
            }
        }
    }

    companion object {
        var dateFormat: SimpleDateFormat = SimpleDateFormat("d MMMM", Locale("ru", "RU"))
    }
}