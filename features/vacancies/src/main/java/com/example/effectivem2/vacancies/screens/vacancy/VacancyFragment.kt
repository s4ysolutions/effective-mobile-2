package com.example.effectivem2.vacancies.screens.vacancy

import android.content.res.ColorStateList
import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.effectivem2.domain.models.Vacancy
import com.example.effectivem2.vacancies.R
import com.example.effectivem2.vacancies.databinding.FragmentVacancyBinding
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

private val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

@AndroidEntryPoint
class VacancyFragment : Fragment() {
    private val viewModel: VacancyViewModel by viewModels()
    private var _binding: FragmentVacancyBinding? = null

    private val binding get() = _binding!!
    private var currentVacancy: Vacancy? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentVacancyBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        currentVacancy = arguments?.getSerializable("vacancy") as? Vacancy

        viewModel.vacanciesLiveData.observe(viewLifecycleOwner) { vacancies ->
            currentVacancy?.let { currentVacancy ->
                val vacancy = vacancies.firstOrNull { it.isSame(currentVacancy) }
                if (vacancy != null) {
                    updateVacancy(vacancy)
                }

            }
        }
        return root
    }

    private fun updateVacancy(vacancy: Vacancy) {
        binding.title.text = vacancy.title
        val income = vacancy.salary.full ?: vacancy.salary.short ?: ""
        binding.income.text =
            income.ifEmpty { getString(R.string.vacancy_salary_not_specified) }
        binding.experience.text = "Требуемый опыт: " + vacancy.experience.previewText
        binding.conditions.text = vacancy.schedules.joinToString(", ")
        val responses = vacancy.appliedNumber?.toInt() ?: 0
        binding.responsesCount.text = if (responses > 0)
            resources.getQuantityString(R.plurals.response_qty, responses, responses)
        else
            "Нет откликов"
        val views = vacancy.lookingNumber?.toInt() ?: 0
        binding.viewsCount.text = if (responses > 0) resources.getQuantityString(
            R.plurals.currently_viewing2, responses, responses
        ) else
            "Никто не просматривает"
        binding.employer.text = vacancy.company
        binding.address.text = vacancy.address.town + ", " + vacancy.address.street
        binding.description.text = vacancy.description
        binding.responsibilities.text = vacancy.responsibilities
        binding.favorite.setImageResource(if (vacancy.isFavorite) com.example.effectivem2.views.R.drawable.favorites_fill else com.example.effectivem2.views.R.drawable.favorites)
        binding.favorite.setOnClickListener {
            viewModel.toggleVacancyFavorite(vacancy)
        }
        val typedValue = TypedValue()
        context?.let { context ->
            val marging = if (context.theme.resolveAttribute(
                    com.example.effectivem2.views.R.attr.viewPaddingHalf,
                    typedValue,
                    true
                )
            ) {
                TypedValue.complexToDimensionPixelSize(
                    typedValue.data,
                    resources.displayMetrics
                )
            } else {
                8.dp
            }
            vacancy.questions.forEach { question ->
                val materialButton = MaterialButton(
                    context,
                    null,
                    com.example.effectivem2.views.R.attr.button_2
                ).apply {
                    id = View.generateViewId() // Generate a unique ID
                    text = question
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        32.dp
                    ).apply {
                        setMargins(0, 0, 0, marging)
                    }
                    backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            context,
                            com.example.effectivem2.views.R.color.grey_2
                        )
                    )
                }

                binding.ask.addView(materialButton)
            }
        }

    }

}