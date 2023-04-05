package com.zeek1910.fragmentsexample

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var buttonFirstFragmentToTopContainer: Button
    private lateinit var buttonFirstFragmentToBottomContainer: Button
    private lateinit var buttonSecondFragmentToTopContainer: Button
    private lateinit var buttonSecondFragmentToBottomContainer: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonFirstFragmentToTopContainer = findViewById(R.id.firstFragmentToTopContainerButton)
        buttonFirstFragmentToBottomContainer =
            findViewById(R.id.firstFragmentToBottomContainerButton)
        buttonSecondFragmentToTopContainer = findViewById(R.id.secondFragmentToTopContainerButton)
        buttonSecondFragmentToBottomContainer =
            findViewById(R.id.secondFragmentToBottomContainerButton)
        buttonFirstFragmentToTopContainer.setOnClickListener(this)
        buttonFirstFragmentToBottomContainer.setOnClickListener(this)
        buttonSecondFragmentToTopContainer.setOnClickListener(this)
        buttonSecondFragmentToBottomContainer.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.firstFragmentToTopContainerButton -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.topFragmentContainer, FirstFragment())
                    .commit()
            }
            R.id.firstFragmentToBottomContainerButton -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.botFragmentContainer, FirstFragment())
                    .commit()
            }
            R.id.secondFragmentToTopContainerButton -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.topFragmentContainer, SecondFragment())
                    .commit()
            }
            R.id.secondFragmentToBottomContainerButton -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.botFragmentContainer, SecondFragment())
                    .commit()
            }
        }
    }
}