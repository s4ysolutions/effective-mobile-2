package com.example.effectivem2.vacancies.screens.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effectivem2.domain.models.Offer
import com.example.effectivem2.domain.models.Vacancy
import com.example.effectivem2.vacancies.R
import com.example.effectivem2.vacancies.databinding.FragmentVacanciesSearchBinding
import com.example.effectivem2.vacancies.adapters.OffersAdapter
import com.example.effectivem2.vacancies.adapters.VacanciesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val viewModel: SearchViewModel by viewModels()

    private var _binding: FragmentVacanciesSearchBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentVacanciesSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.more.setOnClickListener {
            findNavController().navigate(R.id.navigation_vacancies_search_more)
        }

        val offersAdapter = OffersAdapter(emptyArray<Offer>())
        binding.offers.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.offers.adapter = offersAdapter

        viewModel.offersLiveData.observe(viewLifecycleOwner) {
            offersAdapter.setOffers(it)
        }

        val vacanciesAdapter = VacanciesAdapter(emptyArray<Vacancy>(),
            onFavoritesClick = {vacancy: Vacancy ->
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
        }

        return root
    }

}