package com.example.effectivem2.vacancies.screens.favorites

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
import com.example.effectivem2.vacancies.adapters.VacanciesAdapter
import com.example.effectivem2.vacancies.databinding.FragmentVacanciesFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentVacanciesFavoritesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentVacanciesFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val vacanciesAdapter = VacanciesAdapter(emptyArray<Vacancy>(),
            onFavoritesClick = { vacancy: Vacancy ->
                if (vacancy.isFavorite)
                    viewModel.removeVacancyFromFavorites(vacancy)
            },
            onRespondClick = { vacancy: Vacancy ->
                val action = R.id.navigation_vacancy_apply
                val args = bundleOf("vacancy" to vacancy)
                findNavController().navigate(action, args)
            },
            onVacancyClick = { vacancy ->
                val action = R.id.navigation_vacancy
                val args = bundleOf("vacancy" to vacancy)
                findNavController().navigate(action, args)
            }
        )
        binding.vacancies.layoutManager =
            LinearLayoutManager(requireContext())
        binding.vacancies.adapter = vacanciesAdapter

        viewModel.vacanciesLiveData.observe(viewLifecycleOwner) {
            vacanciesAdapter.setVacancies(it)
            binding.favQty.text =
                if (it.size == 0)
                    "Ничего не выбрано"
                else
                    context?.resources?.getQuantityString(R.plurals.favorite_qty, it.size, it.size)
        }

        return root
    }
}