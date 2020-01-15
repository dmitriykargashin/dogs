package com.cardamon.dogs.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.cardamon.dogs.R

class MainActivity : AppCompatActivity() {

    private lateinit var navContoller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        navContoller = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this, navContoller)


    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navContoller,null)
    }

}
