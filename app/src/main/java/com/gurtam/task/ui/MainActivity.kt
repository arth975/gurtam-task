package com.gurtam.task.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.gurtam.task.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mNavController: NavController
    private val noInternetConnectionMessage = "No Internet Connection"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setupNavController()
        setupToolbar()
        checkInternetConnection()
    }

    private fun checkInternetConnection() {
        val manager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = manager.activeNetwork
        val cb = manager.getNetworkCapabilities(network)
        if (cb == null ||
            (!cb.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) &&
                    !cb.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
        )
            Toast.makeText(this, noInternetConnectionMessage, Toast.LENGTH_LONG).show()

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