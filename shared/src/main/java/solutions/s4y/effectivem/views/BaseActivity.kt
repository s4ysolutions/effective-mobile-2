package com.example.effectivem2.views

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView

open class BaseActivity: AppCompatActivity() {
    open fun setupBottomNavigationView(currentMenuItem: Int) {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val vacanciesSearch =
            navView.menu.findItem(R.id.navigation_vacancies_search)
        if (currentMenuItem == R.id.navigation_vacancies_search) {
            navView.selectedItemId = R.id.navigation_vacancies_search
            vacanciesSearch.setOnMenuItemClickListener(null)
        } else {
            vacanciesSearch.setOnMenuItemClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("effectivem2://vacancies/search")
                startActivity(intent)
                finish()
                true
            }
        }
        val hotels = navView.menu.findItem(R.id.navigation_vacancies_favorites)
        if (currentMenuItem == R.id.navigation_vacancies_favorites) {
            hotels.setOnMenuItemClickListener(null)
            navView.selectedItemId = R.id.navigation_vacancies_favorites
        } else {
            hotels.setOnMenuItemClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("effectivem2://vacancies/favorites")
                startActivity(intent)
                finish()
                true
            }
        }
        val profile = navView.menu.findItem(R.id.navigation_profile)
        if (currentMenuItem == R.id.navigation_profile) {
            profile.setOnMenuItemClickListener(null)
            navView.selectedItemId = R.id.navigation_profile
        } else {
            profile.setOnMenuItemClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("effectivem2://profile")
                startActivity(intent)
                finish()
                true
            }
        }
        val messages = navView.menu.findItem(R.id.navigation_messages)
        if (currentMenuItem == R.id.navigation_messages) {
            messages.setOnMenuItemClickListener(null)
            navView.selectedItemId = R.id.navigation_messages
        } else {
            messages.setOnMenuItemClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("effectivem2://messages")
                startActivity(intent)
                finish()
                true
            }
        }
        val responses = navView.menu.findItem(R.id.navigation_responses)
        if (currentMenuItem == R.id.navigation_responses) {
            responses.setOnMenuItemClickListener(null)
            navView.selectedItemId = R.id.navigation_responses
        } else {
            responses.setOnMenuItemClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("effectivem2://responses")
                startActivity(intent)
                finish()
                true
            }
        }
    }

    fun updateFavoritesItem(hasFavorites: Int) {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        if (hasFavorites > 0) {
            val badge = navView.getOrCreateBadge(R.id.navigation_vacancies_favorites)
            badge.isVisible = true
            badge.backgroundColor = ContextCompat.getColor(this, R.color.red)
            badge.number = hasFavorites
            //badge.number = null
        }else {
            navView.removeBadge(R.id.navigation_vacancies_favorites)
        }
    }
}