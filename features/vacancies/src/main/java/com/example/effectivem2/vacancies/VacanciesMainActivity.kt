package com.example.effectivem2.vacancies

import android.os.Bundle
import com.example.effectivem2.vacancies.databinding.ActivityMainBinding
import com.example.effectivem2.views.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class VacanciesMainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        setupBottomNavigationView(
            navView,
            if (intent.data?.path == "/favorites")
                com.example.effectivem2.views.R.id.navigation_vacancies_favorites
            else
                com.example.effectivem2.views.R.id.navigation_vacancies_search
        )
    }
}