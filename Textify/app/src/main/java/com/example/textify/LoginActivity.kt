package com.example.textify

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        sign_in.setOnClickListener {
            performLog_in()

        }

        sign_up.setOnClickListener {
            Log.d("SignUpActivity", "trying log in")

            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun performLog_in(){


        val email_l = email_login.text.toString()
        val passwrd_l = passwrd_login.text.toString()

        if (email_l.isEmpty() || passwrd_l.isEmpty()){
            Toast.makeText(this, "Please enter Email and Password!!!", Toast.LENGTH_SHORT).show()
            return
        }

        Log.d("SignUpActivity", "Email is: " + email_l)
        Log.d("SignUpActivity", "Password is: $passwrd_l")

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email_l, passwrd_l).addOnCompleteListener {
            if (!it.isSuccessful){
                return@addOnCompleteListener
            }
            Toast.makeText(this, "Signed In Successfully😁", Toast.LENGTH_SHORT).show()
            Log.d("Main", "Successfully Logged In user with Uid: ${it.result?.user?.uid}")
//            val intent = Intent(this, MessagePageActivity::class.java)
//            startActivity(intent)

        }.addOnFailureListener {
            Log.d("Main", "Failed to Log in: ${it.message}")
            Toast.makeText(this, "Failed to Login  User: ${it.message}", Toast.LENGTH_SHORT).show()
        }
        val intent = Intent(this, MessagePageActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)

    }
}