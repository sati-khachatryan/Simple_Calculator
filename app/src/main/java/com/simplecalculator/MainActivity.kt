package com.simplecalculator

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton


class MainActivity : AppCompatActivity() {

    lateinit var resultTxt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultTxt = findViewById(R.id.txt_result)
    }

    var firstNumber = ""
    var secondNumber: String = ""
    var operator: String? = null
    private val firstStringBuilder = StringBuilder()
    private val secondStringBuilder = StringBuilder()

    fun clickNumber(view: View) {
        val textOfNumber = (view as AppCompatButton).text.toString()
        when (view.id) {
            R.id.txt_zero, R.id.txt_one, R.id.txt_two,
            R.id.txt_three, R.id.txt_four, R.id.txt_five,
            R.id.txt_six, R.id.txt_seven, R.id.txt_eight,
            R.id.txt_nine, R.id.txt_dot -> handleNumberClick(textOfNumber)
        }
    }

    private fun handleNumberClick (text : String) {
        if (operator == null) {
            firstStringBuilder.append(text)
            firstNumber = firstStringBuilder.toString()
            resultTxt.append(text)
        } else {
            secondStringBuilder.append(text)
            secondNumber = secondStringBuilder.toString()
            resultTxt.append(text)
        }


    }

    fun clickOperator(view: View) {
        val textOfOperator = (view as AppCompatButton).text.toString()
        when (view.id) {
            R.id.txt_percent, R.id.txt_plus, R.id.txt_minus,
            R.id.txt_multy, R.id.txt_divide -> operator = textOfOperator

        }
        resultTxt.append(operator)
    }

    fun clickEqual(view: View) {
        var result = 0.0
        when (operator) {
            "+" -> {result = firstNumber.toDouble() + secondNumber.toDouble()}
            "-" -> {result = firstNumber.toDouble() - secondNumber.toDouble()}
            "x" -> {result = firstNumber.toDouble() * secondNumber.toDouble() }
            "÷" -> { if (secondNumber.toDouble() == 0.0) throw NullPointerException("Cannot be divided by 0")
                     else result = firstNumber.toDouble() / secondNumber.toDouble()}
            "%" -> {result = firstNumber.toDouble() * secondNumber.toDouble() / 100}
        }

        resultTxt.text = result.toString()

    }

    fun clickClean(view: View) {
        firstStringBuilder.clear()
        secondStringBuilder.clear()
        firstNumber = ""
        secondNumber = ""
        operator = null
        resultTxt.text = ""
    }

    fun clickBack (view: View) {
        val length = resultTxt.length()
        if(length > 0)
            resultTxt.text = resultTxt.text.subSequence(0,length-1)

    }




}



