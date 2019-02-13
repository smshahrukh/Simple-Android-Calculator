package com.calc.smshahrukh.simplecalc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    val operations: MutableList<String> = arrayListOf()
    val operands: MutableList<String> = arrayListOf()

    private fun extractString(items: List<String>, connect: String=""): String {
        if (items.isEmpty()) return ""
        return items.reduce { acc, s -> acc + connect + s }
    }

    private fun updateUI(mainUIString: String) {
        val calcString = extractString(operations, connect = "")
        val display = findViewById<View>(R.id.display) as TextView
        display.text  = calcString
        val result = findViewById<View>(R.id.result) as TextView
        result.text = mainUIString
    }

    fun numberSmash(view: View) {
        val button = view as Button
        val numString = button.text

        operands.add(numString.toString())
        val text = extractString(operands)
        updateUI(text)
    }

    fun operatorSmash(view: View) {
        val button = view as Button
        if(operands.isEmpty()) return

        operations.add(extractString(operands))
        operands.clear()
        val buttonText = button.text.toString()
        operations.add(buttonText)
        updateUI(buttonText)
    }

    private fun clearCache() {
        operations.clear()
        operands.clear()
    }

    fun allClear(view: View) {
        clearCache()
        updateUI("")
    }

    fun equalHit(view: View) {
        operations.add(extractString(operands))
        operands.clear()

        val calculator = Calculator()
        val context = view.context
        val result = calculator.calculate(operations, context)
        updateUI("=" + result.toString())
        clearCache()
    }
}
