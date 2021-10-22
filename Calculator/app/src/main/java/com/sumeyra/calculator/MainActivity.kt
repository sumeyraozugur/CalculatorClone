package com.sumeyra.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder //stringi dönüştürüyor evaluate etmek için


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Number listeners

        btn0.setOnClickListener { appendCalculator(true, "0") }
        btn1.setOnClickListener { appendCalculator(true, "1") }
        btn2.setOnClickListener { appendCalculator(true, "2") }
        btn3.setOnClickListener { appendCalculator(true, "3") }
        btn4.setOnClickListener { appendCalculator(true, "4") }
        btn5.setOnClickListener { appendCalculator(true, "5") }
        btn6.setOnClickListener { appendCalculator(true, "6") }
        btn7.setOnClickListener { appendCalculator(true, "7") }
        btn8.setOnClickListener { appendCalculator(true, "8") }
        btn9.setOnClickListener { appendCalculator(true, "9") }
        btnDot.setOnClickListener { appendCalculator(true, ".") }


        //Operator Listeners
        btnPlus.setOnClickListener { appendCalculator(false, "+") }
        btnMinus.setOnClickListener { appendCalculator(false, "-") }
        btnMultiply.setOnClickListener { appendCalculator(false, "*") }
        btnDivide.setOnClickListener { appendCalculator(false, "/") }
        btnLeftB.setOnClickListener { appendCalculator(false, "(") }
        btnRightB.setOnClickListener { appendCalculator(false, ")") }
        btnMode.setOnClickListener { appendCalculator(false, "%") }


        btnClear.setOnClickListener {
            clear()
        }

        btnEqual.setOnClickListener {
            calculate()
        }


    }

    //now create methods

    private fun appendCalculator(delete: Boolean, string: String) {

        if (delete) {

            tvOutput.text = ""
            tvInput.append(string)
        } else {
            if(tvInput.text.isEmpty()){
                tvInput.append(tvOutput.text)
                tvInput.append(string)
                tvOutput.text = ""

                }
            else{
                tvInput.append(string)
                tvInput.append(tvOutput.text)
                tvOutput.text = ""
            }


        }
    }

    private fun clear() {
        tvInput.text = ""
        tvOutput.text = ""

    }

    private fun calculate() {

        try {


            val input = ExpressionBuilder(tvInput.text.toString()).build()
            val output = input.evaluate()
            val longOutput = output.toLong()

            if (output == longOutput.toDouble()){
                tvOutput.text = longOutput.toString()
                tvInput.text = ""
            }else{
                tvOutput.text = output.toString()
            }

        }catch (e:Exception){
            Toast.makeText(this@MainActivity,e.localizedMessage,Toast.LENGTH_LONG).show()
        }
    }
    }
