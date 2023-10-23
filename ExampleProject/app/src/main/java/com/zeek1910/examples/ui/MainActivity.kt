package com.zeek1910.examples.ui

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
//    private lateinit var recyclerView: RecyclerView

    private val meditationAdapter = MeditationAdapter()

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
//        recyclerView = findViewById(R.id.recyclerView)

        //setupRecyclerView()
        //meditationAdapter.setItems(getItems())

//        buttonLogOut.setOnClickListener {
//            val intent = Intent(this, SignInActivity::class.java)
//            startActivity(intent)
//            appSettings.logout()
//        }
    }

    private fun setupRecyclerView() {
//        recyclerView.adapter = meditationAdapter
//        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun getItems(): List<MeditationItem> {
        return listOf(
            MeditationItem(
                "Meditation 101",
                "Techniques, Benefits, and a Beginner’s How-To",
                R.drawable.image_1,
                "testUrl1"
            ),
            MeditationItem(
                "Cardio Meditation",
                "Basics of Yoga for Beginners or Experienced Professionals",
                R.drawable.image_2,
                "testUrl2"
            ),
            MeditationItem(
                "Meditation 101",
                "Techniques, Benefits, and a Beginner’s How-To",
                R.drawable.image_1,
                "testUrl1"
            ),
            MeditationItem(
                "Cardio Meditation",
                "Basics of Yoga for Beginners or Experienced Professionals",
                R.drawable.image_2,
                "testUrl2"
            ),
            MeditationItem(
                "Meditation 101",
                "Techniques, Benefits, and a Beginner’s How-To",
                R.drawable.image_1,
                "testUrl1"
            ),
            MeditationItem(
                "Cardio Meditation",
                "Basics of Yoga for Beginners or Experienced Professionals",
                R.drawable.image_2,
                "testUrl2"
            ),
            MeditationItem(
                "Meditation 101",
                "Techniques, Benefits, and a Beginner’s How-To",
                R.drawable.image_1,
                "testUrl1"
            ),
            MeditationItem(
                "Cardio Meditation",
                "Basics of Yoga for Beginners or Experienced Professionals",
                R.drawable.image_2,
                "testUrl2"
            ),
            MeditationItem(
                "Meditation 101",
                "Techniques, Benefits, and a Beginner’s How-To",
                R.drawable.image_1,
                "testUrl1"
            ),
            MeditationItem(
                "Cardio Meditation",
                "Basics of Yoga for Beginners or Experienced Professionals",
                R.drawable.image_2,
                "testUrl2"
            ),
            MeditationItem(
                "Meditation 101",
                "Techniques, Benefits, and a Beginner’s How-To",
                R.drawable.image_1,
                "testUrl1"
            ),
            MeditationItem(
                "Cardio Meditation",
                "Basics of Yoga for Beginners or Experienced Professionals",
                R.drawable.image_2,
                "testUrl2"
            )
        )
    }
}