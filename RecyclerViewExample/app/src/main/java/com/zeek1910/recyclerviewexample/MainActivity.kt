package com.zeek1910.recyclerviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var specialistList: RecyclerView

    private val specialistAdapter = SpecialistAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        specialistList = findViewById(R.id.specialistList)
        specialistList.adapter = specialistAdapter
        specialistList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false )

        specialistAdapter.setItems(ListData.getData())

    }
}