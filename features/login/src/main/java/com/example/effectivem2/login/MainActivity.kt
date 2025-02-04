package com.example.effectivem2.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.effectivem2.login.databinding.ActivityLoginMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
/*
        val navigationHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_profile) as NavHostFragment
        val navController = navigationHost.navController

        NavController.OnDestinationChangedListener { _, destination, arguments ->
            Log.d("NavigationDebug", "Navigated to ${destination.label} with args: $arguments")
        }.also { navController.addOnDestinationChangedListener(it) }

        setupBottomNavigationView(com.example.effectivem2.views.R.id.navigation_profile)
 */

    }
}