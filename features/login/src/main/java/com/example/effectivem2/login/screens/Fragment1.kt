package com.example.effectivem2.login.screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.effectivem2.login.R
import com.example.effectivem2.login.databinding.FragmentLogin1Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Fragment1 : Fragment() {

    private var _binding: FragmentLogin1Binding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogin1Binding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.cont.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_to_login_2)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}