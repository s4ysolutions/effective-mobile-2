package com.example.effectivem2.responses

import android.os.Bundle
import com.example.effectivem2.responses.databinding.ActivityResponsesMainBinding
import com.example.effectivem2.views.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityResponsesMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResponsesMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        setupBottomNavigationView(navView, com.example.effectivem2.views.R.id.navigation_responses)

    }
}