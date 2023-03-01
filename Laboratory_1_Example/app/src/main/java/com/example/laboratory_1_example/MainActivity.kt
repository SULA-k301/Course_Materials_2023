package com.example.laboratory_1_example

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var task1xInputField: EditText
    private lateinit var task1nInputField: EditText
    private lateinit var task1GetResultButton: Button
    private lateinit var task1ResultField: TextView

    private lateinit var task2InputField: EditText
    private lateinit var task2GetResultButton: Button
    private lateinit var task2ResultField: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        task1xInputField = findViewById(R.id.task1xInputField)
        task1nInputField = findViewById(R.id.task1nInputField)
        task1GetResultButton = findViewById(R.id.task1GetResultButton)
        task1ResultField = findViewById(R.id.task1ResultField)

        task2InputField = findViewById(R.id.task2InputField)
        task2GetResultButton = findViewById(R.id.task2GetResultButton)
        task2ResultField = findViewById(R.id.task2ResultField)

        task1xInputField.addTextChangedListener {
            task1ResultField.isVisible = false
        }
        task1nInputField.addTextChangedListener {
            task1ResultField.isVisible = false
        }
        task2InputField.addTextChangedListener {
            task2ResultField.isVisible = false
        }

        task1GetResultButton.setOnClickListener {
            val xValue = task1xInputField.text.toString()
            val nValue = task1nInputField.text.toString()
            if (xValue.isEmpty() || nValue.isEmpty()) {
                Toast.makeText(this, "Input fields must not be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val result = getTask1Result(xValue.toFloat(), nValue.toInt())
            showTask1Result(result)
        }
        task2GetResultButton.setOnClickListener {
            val text = task2InputField.text.toString()
            if (text.isEmpty()) {
                Toast.makeText(this, "Input field must not be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val result = getTask2Result(text)
            showTask2Result(result)
        }
    }

    private fun getTask1Result(x: Float, n: Int): Double {
        var result = 0.0
        for (k in 1..n) {
            result += (x.pow(k) + 1) / factorial((k - 1))
        }
        return result
    }

    private fun getTask2Result(text: String) = text.count { "\\d".toRegex().matches(it.toString()) }

    private fun showTask1Result(result: Double) {
        task1ResultField.text = "Sum = $result"
        task1ResultField.isVisible = true
    }

    private fun showTask2Result(result: Int) {
        task2ResultField.text = "Number count = $result"
        task2ResultField.isVisible = true
    }

    private fun factorial(n: Int): Double = if (n < 2) 1.0 else n * factorial(n - 1)
}