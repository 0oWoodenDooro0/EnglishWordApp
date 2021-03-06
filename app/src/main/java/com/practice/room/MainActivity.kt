package com.practice.room

import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.practice.room.data.FragmentChange
import com.practice.room.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private val viewModel: WordsViewModel by viewModels()
    private var _menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.wordsToolbar)
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.fragmentNavController.id) as NavHostFragment
        navController = navHostFragment.findNavController()
        appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.wordsToolbar.setupWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.wordsFragment -> {
                    viewModel.onEvent(WordsEvent.ChangeFragment(FragmentChange.wordsFragment))
                }
                R.id.insertWordFragment -> {
                    viewModel.onEvent(WordsEvent.ChangeFragment(FragmentChange.insertWordFragment))
                }
                R.id.randomWordFragment -> {
                    viewModel.onEvent(WordsEvent.RandomWord)
                    viewModel.onEvent(WordsEvent.ChangeFragment(FragmentChange.randomWordFragment))
                }
            }
        }

        viewModel.fragment.observe(this) { fragment ->
            _menu?.let { menu ->
                when (fragment) {
                    FragmentChange.wordsFragment -> {
                        menu.findItem(R.id.random).isVisible = true
                        menu.findItem(R.id.insert).isVisible = true
                    }
                    else -> {
                        menu.findItem(R.id.random).isVisible = false
                        menu.findItem(R.id.insert).isVisible = false
                    }
                }
            }
        }

        binding.wordsToolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.random -> {
                    navHostFragment.findNavController()
                        .navigate(R.id.action_wordsFragment_to_randomWordFragment)
                }
                R.id.insert -> {
                    navHostFragment.findNavController()
                        .navigate(R.id.action_wordsFragment_to_insertWordFragment)
                }
            }
            false
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_nav_controller) as NavHostFragment
        val navController = navHostFragment.findNavController()
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        _menu = menu
        return true
    }

    override fun onBackPressed() {
        if (navController.currentDestination?.id == R.id.wordsFragment) {
            return
        }
        super.onBackPressed()
    }
}