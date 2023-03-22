package com.zeek1910.recyclerviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        if (intent.hasExtra(MainActivity.SPECIALIST_KEY)){
            val item: Specialist? = intent.getSerializableExtra(MainActivity.SPECIALIST_KEY) as Specialist?
            Log.d("DetailsActivity",item.toString())
        }
    }
}