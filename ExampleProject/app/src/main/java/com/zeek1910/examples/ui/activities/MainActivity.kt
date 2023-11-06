package com.zeek1910.examples.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zeek1910.examples.R
import com.zeek1910.examples.data.AppSettings
import com.zeek1910.examples.models.MeditationItem

class MainActivity : AppCompatActivity() {

    private lateinit var appSettings: AppSettings

//    private lateinit var buttonLogOut: Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            .setupWithNavController(navController)


       // appSettings = (application as App).appSettings

//        buttonLogOut = findViewById(R.id.buttonLogOut)




//        buttonLogOut.setOnClickListener {
//            val intent = Intent(this, SignInActivity::class.java)
//            startActivity(intent)
//            appSettings.logout()
//        }
    }




}