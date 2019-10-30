package com.example.textify

import android.app.Activity
import android.content.Intent
import android.content.Intent.ACTION_CHOOSER
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Images.Media.getBitmap
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

@Suppress("PLUGIN_WARNING", "DEPRECATION")
class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        register.setOnClickListener {
            performRegister()

        }

        have_account.setOnClickListener {
            Log.d("SignUpActivity", "trying log in")

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        profile_img.setOnClickListener{
            Log.d("SignUpActivity", "try to show photo")
//            val intent = Intent(Intent.ACTION_GET_CONTENT)
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "images/*"
//            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 0)
            startActivityForResult(Intent.createChooser(intent, "Select Photo"), 0)

        }



    }

    var selectedPhotoLocation: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            Log.d("SignUpActivity", "Photo was Picked")

            selectedPhotoLocation = data.data

            val bitmap = getBitmap(contentResolver, selectedPhotoLocation)


//            image_view.setImageBitmap(bitmap)
//
//            profile_img.alpha = 0f
            val bitmapDrawable = BitmapDrawable(bitmap)
            profile_img.setBackgroundDrawable(bitmapDrawable)
        }
    }

    private fun performRegister(){
        val email = email_register.text.toString()
        val usrname = usrname_register.text.toString()
        val passwrd = password_register.text.toString()

        if (email.isEmpty() || passwrd.isEmpty() || usrname.isEmpty()){
            Toast.makeText(this, "Please fill all fields!!!", Toast.LENGTH_SHORT).show()
            return
        }

        Log.d("SignUpActivity", "Email is: " + email)
        Log.d("SignUpActivity", "Password is: $passwrd")
        Log.d("SignUpActivity", "Username is: $usrname")


        //Firebase Authentication
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, passwrd).addOnCompleteListener {
            if (!it.isSuccessful){
                return@addOnCompleteListener
            }
            Toast.makeText(this, "Account Created Successfully😁", Toast.LENGTH_SHORT).show()
            Log.d("SignUpActivity", "Successfully Created user with Uid: ${it.result?.user?.uid}")

            uploadProfile_Img_to_Firebase()

        }
            .addOnFailureListener {
                Log.d("SignUpActivity", "Failed to create User: ${it.message}")
                Toast.makeText(this, "Failed to create User: ${it.message}", Toast.LENGTH_SHORT).show()

            }
//        val intent = Intent(this, MessagePageActivity::class.java)
//            startActivity(intent)
    }

    private fun uploadProfile_Img_to_Firebase(){

        if (selectedPhotoLocation == null){
            return
        }
        val fname = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/Image/$fname")

        ref.putFile(selectedPhotoLocation!!)
            .addOnSuccessListener {
                Log.d("SignUpActivity", "Success Uploaded : ${it.metadata?.path}")
                
                ref.downloadUrl.addOnSuccessListener { 
                    it.toString()
                    Log.d("SignUp", "File Location: $it")

                    saveUserToFirebase_DB(it.toString())
                }
            }
            .addOnFailureListener {

            }
    }

    private fun saveUserToFirebase_DB(profileImageUrl: String){
        val uid = FirebaseAuth.getInstance().uid ?:""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        var user = User(usrname_register.text.toString(), profileImageUrl,uid)
        ref.setValue(user)
            .addOnSuccessListener {
                Log.d("SignupActivity", "saved user to Firebase DB")

            val intent = Intent(this, MessagePageActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)


            }
            .addOnFailureListener {
                Log.d("SignupActivity", "Failed to set value to DB: ${it.message}")
            }
    }
}

class User(val Username: String, val profileImageUrl: String, val uid: String)