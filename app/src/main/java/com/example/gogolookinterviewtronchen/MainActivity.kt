package com.example.gogolookinterviewtronchen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.example.gogolookinterviewtronchen.databinding.ActivityMainBinding
import com.example.gogolookinterviewtronchen.ext.getVmFactory
import com.example.gogolookinterviewtronchen.util.CurrentFragmentType

class MainActivity : AppCompatActivity() {

    val viewModel by viewModels<MainViewModel> { getVmFactory() }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // observe current fragment change, only for show info
        viewModel.currentFragmentType.observe(this, androidx.lifecycle.Observer {
            Log.i("Tron","~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
            Log.i("Tron","[${viewModel.currentFragmentType.value}]")
            Log.i("Tron","~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
        })

        setupNavController()
    }

    private fun setupNavController(){
        findNavController(R.id.myNavHostFragment).addOnDestinationChangedListener{ navController: NavController, _: NavDestination, _: Bundle? ->
            viewModel.currentFragmentType.value = when(navController.currentDestination?.id) {
                R.id.homeFragment -> CurrentFragmentType.HOME
                R.id.searchFragment -> CurrentFragmentType.SEARCH
                R.id.imageRFragment -> CurrentFragmentType.IMAGE_RESULT
                else -> viewModel.currentFragmentType.value
            }
        }
    }
}
