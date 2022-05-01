package com.gurtam.task.ui.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.gurtam.task.R
import com.gurtam.task.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setupNavController()
        setupToolbar()
    }

    private fun setupNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(mBinding.fragmentContainer.id) as NavHostFragment
        mNavController = navHostFragment.navController
    }

    private fun setupToolbar() {
        setSupportActionBar(mBinding.toolbar)

        val appBarConfig = AppBarConfiguration(mNavController.graph)
        mBinding.toolbar.setupWithNavController(mNavController, appBarConfig)
    }
}