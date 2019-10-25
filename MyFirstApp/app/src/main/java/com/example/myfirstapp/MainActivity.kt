package com.example.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import java.nio.file.attribute.AclFileAttributeView
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toast(view: View){
        val mytoast = Toast.makeText(this, "Uyi Toast", Toast.LENGTH_SHORT)
        mytoast.show()
    }

    fun countno(view: View){
        val showCountTextView = findViewById<TextView>(R.id.textView)

        val countString = showCountTextView.text.toString()

        var count: Int = Integer.parseInt(countString)
//        for (i in 1..10){
            if (1 <= count)
                count = count * 2
//        }
//        count

        showCountTextView.text = count.toString()
    }

    fun divideno(view: View){
        val showCountTextView = findViewById<TextView>(R.id.textView)

        val countString = showCountTextView.text.toString()

        var count: Int = Integer.parseInt(countString)
//        for (i in 1..10){
        if (1 <= count)
            count = count / 2
//        }
//        count

        showCountTextView.text = count.toString()
    }


    fun calc(view: View){
        val textCollector = findViewById<TextInputEditText>(R.id.input)
        val textChange = findViewById<TextView>(R.id.textView)

        textChange.text = textCollector.text

    }
}
