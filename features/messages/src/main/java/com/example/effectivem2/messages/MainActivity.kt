package com.example.effectivem2.messages

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.effectivem2.messages.databinding.ActivityMessagesMainBinding
import com.example.effectivem2.views.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMessagesMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMessagesMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navigationHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity) as NavHostFragment
        val navController = navigationHost.navController

        NavController.OnDestinationChangedListener { _, destination, arguments ->
            Log.d("NavigationDebug", "Navigated to ${destination.label} with args: $arguments")
        }.also { navController.addOnDestinationChangedListener(it) }

        setupBottomNavigationView(com.example.effectivem2.views.R.id.navigation_messages)

    }
}