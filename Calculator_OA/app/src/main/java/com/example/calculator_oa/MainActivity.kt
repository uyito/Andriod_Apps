package com.example.calculator_oa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Numbers
        Zero.setOnClickListener{appendOnExpress("0", true)}
        One.setOnClickListener{appendOnExpress("1", true)}
        Two.setOnClickListener{appendOnExpress("2", true)}
        Three.setOnClickListener{appendOnExpress("3", true)}
        Four.setOnClickListener{appendOnExpress("4", true)}
        Five.setOnClickListener{appendOnExpress("5", true)}
        Six.setOnClickListener{appendOnExpress("6", true)}
        Seven.setOnClickListener{appendOnExpress("7", true)}
        Eight.setOnClickListener{appendOnExpress("8", true)}
        Nine.setOnClickListener{appendOnExpress("9", true)}
        Dot.setOnClickListener{appendOnExpress(".", true)}


//      Operation
        Plus.setOnClickListener{appendOnExpress("+", true)}
        Times.setOnClickListener{appendOnExpress("*", true)}
        Minus.setOnClickListener{appendOnExpress("-", true)}
        Divide.setOnClickListener{appendOnExpress("/", true)}
        OBrac.setOnClickListener{appendOnExpress("(", true)}
        CBrac.setOnClickListener{appendOnExpress(")", true)}


        Clear.setOnClickListener{
            Expression.text = ""
            CalResult.text = ""
        }

        Back.setOnClickListener {
            val string = Expression.text.toString()
            if (string.isNotEmpty()){
                Expression.text = string.substring(0,string.length-1)

            }
            CalResult.text = ""
        }

        Equal_to.setOnClickListener {
            try {
                val enterInput = ExpressionBuilder(Expression.text.toString()).build()
                val result = enterInput.evaluate()
                val mainResult = result.toLong()

                if(result == mainResult.toDouble()) {
                    CalResult.text = mainResult.toString()
                }else{
                    CalResult.text = result.toString()
                }

            }catch (e:Exception){
                Log.d("Exception", "Message : " + e.message)
            }
        }


    }


    fun appendOnExpress(string: String, CanClear: Boolean){

        if (CalResult.text.isNotEmpty()){
            Expression.text = ""
            Expression.append(CalResult.text)
        }

        if (CanClear){
            CalResult.text = ""
            Expression.append(string)

        }else{
            Expression.append(CalResult.text)
            Expression.append(string)
            CalResult.text = ""
        }

    }
}
