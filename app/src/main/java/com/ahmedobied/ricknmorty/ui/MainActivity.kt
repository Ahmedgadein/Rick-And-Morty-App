package com.ahmedobied.ricknmorty.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.ahmedobied.ricknmorty.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI();
    }

    private fun setupUI() {
        navController = Navigation.findNavController(this,R.id.nav_host_fragment)
        bottomNavigation.setupWithNavController(navController)
    }

    override fun onNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,null)
    }
}