package com.example.effectivem2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
//import com.example.effectivem2.login.MainActivity
import com.example.effectivem2.vacancies.MainActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}