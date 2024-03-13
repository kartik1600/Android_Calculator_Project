package com.example.calculator

import android.widget.TextView
import androidx.lifecycle.ViewModel
import kotlin.math.pow

class MainVM : ViewModel() {
     private var num1: Double? = null
     private var num2: Double? = null
     var symbol: String? = null

     fun emptyCheck(textView: TextView): Boolean {
        return textView.text.isNotEmpty()
    }


     fun result(textView: TextView) {

        if (textView.text.length > 17) {
            textView.text = "Value Exceeded"
        }

        if (emptyCheck(textView) && num1 != null || num2 != null) {
            if (textView.text == ".") {
                textView.text = "error"
            } else {

                num2 = textView.text.toString().toDouble()
                calculate(num1!!, num2!!, symbol!!, textView)
                num1 = null
                num2 = null
                symbol = ""

            }
        }

    }

    val calculate =
        { v1: Double, v2: Double, symbol: String, textView: TextView ->
            if (symbol == "+") {
                setResults(v1 + v2, textView)
            }
            if (symbol == "-") {
                setResults(v1 - v2, textView)
            }
            if (symbol == "*") {
                setResults(v1 * v2, textView)
            }
            if (symbol == "/") {
                setResults(v1 / v2, textView)
            }
            if (symbol == "%") {
                setResults(v1 % v2, textView)
            }
            if (symbol == "%") {
                setResults(v1.pow(v2), textView)
            }
        }

     fun setResults(result: Double, textView: TextView) {
        textView.text = result.toString()
    }

}