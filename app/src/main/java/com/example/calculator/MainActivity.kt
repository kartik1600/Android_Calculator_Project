package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.calculator.databinding.ActivityMainBinding
import kotlin.math.pow
import kotlin.math.sqrt


class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val ViewModel : MainVM?=null
    private var num1: Double? = null
    private var num2: Double? = null
    private var symbol: String? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.blue)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.blue)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        numbersKeys()
        operationKeys()
        result()
    }

    //this function is used to take numeric input from the user
    @SuppressLint("SetTextI18n")
    private fun numbersKeys() {
        binding.btn1.setOnClickListener {
            cleanError()
            binding.tvInput.text = binding.tvInput.text.toString() + "1"
        }
        binding.btn2.setOnClickListener {
            cleanError()
            binding.tvInput.text = binding.tvInput.text.toString() + "2"
        }
        binding.btn3.setOnClickListener {
            cleanError()
            binding.tvInput.text = binding.tvInput.text.toString() + "3"
        }
        binding.btn4.setOnClickListener {
            cleanError()
            binding.tvInput.text = binding.tvInput.text.toString() + "4"
        }
        binding.btn5.setOnClickListener {
            cleanError()
            binding.tvInput.text = binding.tvInput.text.toString() + "5"
        }
        binding.btn6.setOnClickListener {
            cleanError()
            binding.tvInput.text = binding.tvInput.text.toString() + "6"
        }
        binding.btn7.setOnClickListener {
            binding.tvInput.text = binding.tvInput.text.toString() + "7"
        }
        binding.btn8.setOnClickListener {
            cleanError()
            binding.tvInput.text = binding.tvInput.text.toString() + "8"
        }
        binding.btn9.setOnClickListener {
            binding.tvInput.text = binding.tvInput.text.toString() + "9"
        }
        binding.btn0.setOnClickListener {
            cleanError()
            binding.tvInput.text = binding.tvInput.text.toString() + "0"
        }
        binding.btnDecimal.setOnClickListener {
            if (!binding.tvInput.text.contains(".")) {
                cleanError()
                binding.tvInput.text = binding.tvInput.text.toString() + "."
            }
        }
    }

    //this function is used to take take
    private fun operationKeys() {

        //clear button
        binding.btnClear.setOnClickListener {
            num1 = null
            num2 = null
            symbol = ""
            binding.tvInput.text = ""
        }

        binding.btnAdd.setOnClickListener {
            if (emptyCheck()) {
                symbol = "+"
                setAndClean()
            }
        }
        binding.btnMinus.setOnClickListener {
            if (emptyCheck()) {
                symbol = "-"
                setAndClean()
            }
        }
        binding.btnMulti.setOnClickListener {
            if (emptyCheck()) {
                symbol = "*"
                setAndClean()
            }
        }
        binding.btnDivide.setOnClickListener {
            if (emptyCheck()) {
                symbol = "/"
                setAndClean()
            }
        }
        binding.btnPower.setOnClickListener {
            if (emptyCheck()) {
                symbol = "^"
                setAndClean()
            }
        }
        binding.btnRem.setOnClickListener {
            if (emptyCheck()) {
                symbol = "%"
                setAndClean()
            }
        }

        binding.btnSquareRoot.setOnClickListener {
            if (emptyCheck()) {
                val tv = binding.tvInput
                val x = tv.text.toString().toDouble()
                binding.tvInput.text = ""
                tv.text = sqrt(x).toString()
            }
        }
    }


    private fun result() {
        binding.btnResult.setOnClickListener {

            if (binding.tvInput.text.length > 17) {
                binding.tvInput.text = getString(R.string.value_exceeded)
            }

            if (emptyCheck() && num1 != null || num2 != null) {
                if (binding.tvInput.text == ".") {
                    binding.tvInput.text = getString(R.string.error)
                } else {

                    num2 = binding.tvInput.text.toString().toDouble()
                    calculate(num1!!, num2!!, symbol!!)
                    num1 = null
                    num2 = null
                    symbol = ""
                }
            }
        }
    }

    val calculate =
        { v1: Double, v2: Double, symbol: String ->
            if (symbol == "+") {
                setResults(v1 + v2)
            }
            if (symbol == "-") {
                setResults(v1 - v2)
            }
            if (symbol == "*") {
                setResults(v1 * v2)
            }
            if (symbol == "/") {
                setResults(v1 / v2)
            }
            if (symbol == "%") {
                setResults(v1 % v2)
            }
            if (symbol == "%") {
                setResults(v1.pow(v2))
            }
        }

    //this function is used to set the results on the user screen.
    private fun setResults(result: Double) {
        binding.tvInput.text = result.toString()
    }


    //this function is used to remove
    private fun setAndClean() {
        if (binding.tvInput.text.toString() == "." ||
            binding.tvInput.text.toString() == getString(R.string.error)
        ) {
            binding.tvInput.text = getString(R.string.error)
        } else {
            num1 = binding.tvInput.text.toString().toDouble()
            binding.tvInput.text = ""
        }
    }

    //this function is used to check textView is it empty or not.
    private fun emptyCheck(): Boolean {
        return binding.tvInput.text.isNotEmpty()
    }

    //this function clean the result screen if any unwanted text is present there.
    private fun cleanError() {
        if (binding.tvInput.text.toString() == "error"
            || binding.tvInput.text.toString() == "Value Exceeded"
            || binding.tvInput.text.toString() == "Infinity"
        ) {
            binding.tvInput.text = ""
        }
    }
}



//                     NOT IN USE

//    private fun calculate(v1: Double, v2: Double, symbol: String) {
//
//        if (symbol == "+") {
//            val sum = { a: Double, b: Double -> a + b }
//            binding.tvInput.text = sum(v1, v2).toString()
//        }
//        if (symbol == "-") {
//            val sum = { a: Double, b: Double -> a - b }
//            binding.tvInput.text = sum(v1, v2).toString()
//        }
//        if (symbol == "*") {
//            val sum = { a: Double, b: Double -> a * b }
//            binding.tvInput.text = sum(v1, v2).toString()
//        }
//        if (symbol == "/") {
//            val sum = { a: Double, b: Double -> a / b }
//            binding.tvInput.text = sum(v1, v2).toString()
//        }
//        if (symbol == "^") {
//            val sum = { a: Double, b: Double -> a.pow(b) }
//            binding.tvInput.text = sum(v1, v2).toString()
//        }
//        if (symbol == "%") {
//            val sum = { a: Double, b: Double -> a % b }
//            binding.tvInput.text = sum(v1, v2).toString()
//        }
//    }



