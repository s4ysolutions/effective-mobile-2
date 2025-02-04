package com.example.effectivem2.vacancies

import android.os.Bundle
import androidx.activity.viewModels
import com.example.effectivem2.vacancies.databinding.ActivityVacanciesMainBinding
import com.example.effectivem2.vacancies.screens.MainViewModel
import com.example.effectivem2.views.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityVacanciesMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVacanciesMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val navView: BottomNavigationView = binding.navView
        setupBottomNavigationView(
         //   navView,
            if (intent.data?.path == "/favorites")
                com.example.effectivem2.views.R.id.navigation_vacancies_favorites
            else
                com.example.effectivem2.views.R.id.navigation_vacancies_search
        )

        viewModel.hasFavoritesLiveData.observe(this){
            updateFavoritesItem(it)
        }
    }
}