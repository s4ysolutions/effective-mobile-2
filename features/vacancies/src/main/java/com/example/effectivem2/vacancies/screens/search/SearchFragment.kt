package com.example.effectivem2.vacancies.screens.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.effectivem2.vacancies.databinding.FragmentVacanciesSearchBinding

class SearchFragment : Fragment() {

    private var _binding: FragmentVacanciesSearchBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val searchViewModel =
            ViewModelProvider(this).get(SearchViewModel::class.java)

        _binding = FragmentVacanciesSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.profileTextHome
        searchViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

}