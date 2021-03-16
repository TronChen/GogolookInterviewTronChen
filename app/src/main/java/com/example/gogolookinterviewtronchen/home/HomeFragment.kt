package com.example.gogolookinterviewtronchen.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gogolookinterviewtronchen.R
import com.example.gogolookinterviewtronchen.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.cardView.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionGlobalSearchFragment())
        }

        return binding.root
    }
}