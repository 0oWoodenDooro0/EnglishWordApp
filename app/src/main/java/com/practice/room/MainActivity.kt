package com.practice.room

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.practice.room.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.wordsToolbar)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_nav_controller) as NavHostFragment
        val navController = navHostFragment.findNavController()
//        Log.d("MainActivity", navController.toString())
        appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.wordsToolbar.setupWithNavController(navController, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_nav_controller) as NavHostFragment
        val navController = navHostFragment.findNavController()
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}