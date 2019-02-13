package com.calc.smshahrukh.simplecalc

import android.content.Context

class Calculator {

    fun calculate(calculations: List<String>, context: Context): Int {
        var operator = ""
        var operand = 0

        calculations.forEach { token ->
            when {
                token.equals(context.getString(R.string.add))
                        || token.equals(context.getString(R.string.subtract))
                        || token.equals(context.getString(R.string.multiply))
                        || token.equals(context.getString(R.string.divide)) -> operator = token

                operator.equals(context.getString(R.string.subtract)) -> operand -= token.toInt()
                operator.equals(context.getString(R.string.divide)) -> operand /= token.toInt()
                operator.equals(context.getString(R.string.multiply)) -> operand *= token.toInt()

                else -> operand += token.toInt()
            }
        }

        return operand
    }

}