package com.example.effectivem2.vacancies.screens.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effectivem2.domain.models.Vacancy
import com.example.effectivem2.vacancies.R
import com.example.effectivem2.vacancies.databinding.FragmentVacanciesSearchMoreBinding
import com.example.effectivem2.vacancies.adapters.VacanciesAdapter
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchMoreFragment : Fragment() {
    private val viewModel: SearchViewModel by viewModels()

    private var _binding: FragmentVacanciesSearchMoreBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVacanciesSearchMoreBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val searchLayout: TextInputLayout = binding.searchLayout
        searchLayout.setStartIconOnClickListener {
            findNavController().popBackStack()
        }

        val vacanciesAdapter = VacanciesAdapter(
            emptyArray<Vacancy>(),
            onFavoritesClick = { vacancy: Vacancy ->
                if (vacancy.isFavorite)
                    viewModel.removeVacancyFromFavorites(vacancy)
                else
                    viewModel.addVacancyToFavorites(vacancy)
            },
            onRespondClick = { vacancy: Vacancy ->
                val action = R.id.navigation_vacancy
                val args = bundleOf("vacancy" to vacancy)
                findNavController().navigate(action, args)
            })

        binding.vacancies.layoutManager =
            LinearLayoutManager(requireContext())
        binding.vacancies.adapter = vacanciesAdapter

        viewModel.vacanciesLiveData.observe(viewLifecycleOwner) {
            vacanciesAdapter.setVacancies(it)
            binding.qty.text = if (it.size>0)
                resources.getQuantityString(R.plurals.vacancies_qty, it.size, it.size)
            else
                "Нет вакансий"
            binding.qty.visibility = View.VISIBLE
        }

        return root
    }

}