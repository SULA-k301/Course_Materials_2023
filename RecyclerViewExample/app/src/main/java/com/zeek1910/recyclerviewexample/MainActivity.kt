package com.zeek1910.recyclerviewexample

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var specialistList: RecyclerView

    private val specialistAdapter = SpecialistAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        specialistAdapter.clickListener = object : SpecialistAdapter.OnItemClickListener {
            override fun onItemClick(item: Specialist) {
                startActivity(Intent(this@MainActivity, DetailsActivity::class.java).apply {
                    putExtra(SPECIALIST_KEY, item)
                })
            }
        }
        specialistAdapter.longClickListener = { position ->
            Toast.makeText(this@MainActivity, "Long click position - $position", Toast.LENGTH_SHORT)
                .show()
        }

        specialistList = findViewById(R.id.specialistList)
        specialistList.adapter = specialistAdapter
        specialistList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        specialistAdapter.setItems(ListData.getData())

    }

    companion object{
        const val SPECIALIST_KEY = "SPECIALIST_KEY"
    }
}