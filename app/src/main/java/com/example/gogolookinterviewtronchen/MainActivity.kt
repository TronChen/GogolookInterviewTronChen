package com.example.gogolookinterviewtronchen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gogolookinterviewtronchen.databinding.ActivityMainBinding
import com.example.gogolookinterviewtronchen.ext.getVmFactory
import com.example.gogolookinterviewtronchen.util.CurrentFragmentType
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val viewModel by viewModels<MainViewModel> { getVmFactory() }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val navController = this.findNavController(R.id.myNavHostFragment)

        // observe current fragment change, only for show info
        viewModel.currentFragmentType.observe(this, androidx.lifecycle.Observer {
            Log.i("Tron","~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
            Log.i("Tron","[${viewModel.currentFragmentType.value}]")
            Log.i("Tron","~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
        })

        binding.navigateUP.setOnClickListener {
            navController.navigateUp()
        }

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
