package com.example.firebase

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.FirebaseDatabase

class Signup : AppCompatActivity() {

    var user : String?=null
    var passwd : String?=null
    var fname : String?=null
    var mob : String?=null
    var eml : String?=null
    //lateinit var selectedimg : Uri
    lateinit var image : ImageView
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    lateinit var register: Button
    lateinit var tologin: Button
   lateinit var susername : TextInputEditText
    lateinit var spassword : TextInputEditText
    lateinit var fullname : TextInputEditText
    lateinit var email : TextInputEditText
    lateinit var mobileno : TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        var database = FirebaseDatabase.getInstance()
        var ref = database.getReference("user")
        image = findViewById(R.id.userimg)
        susername=findViewById(R.id.username_signup)
        spassword= findViewById(R.id.password_signup)
        email= findViewById(R.id.email)
         mobileno=findViewById(R.id.mobile)
        fullname=findViewById(R.id.fullname_signup)

        register = findViewById(R.id.register_button)
        tologin = findViewById(R.id.to_login_button)


        tologin.setOnClickListener {
            var intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }
        fun user() : Boolean{
            return when {

                user.toString().isEmpty() -> {
                    susername.error = "This field cannot be empty"
                    false
                }
                user.toString().length > 30 -> {
                    susername.error = "The number of characters entered is more than allowed"
                    false
                }
                else -> {
                    susername.error = null
                    true
                }
            }
        }

        fun pas() : Boolean{
            return when {

                passwd.toString().isEmpty() -> {
                    spassword.error = "This field cannot be empty"
                    false
                }
                passwd.toString().length < 4 && passwd.toString().length != null -> {
                    spassword.error = "Enter at least 4 characters"
                    false
                }
                else -> {
                    spassword.error = null
                    true
                }
            }
        }
        fun fname() : Boolean{
            return when {

                fname.toString().isEmpty() -> {
                    fullname.error = "This field cannot be empty"
                    false
                }

                else -> {
                    fullname.error = null
                    true
                }
            }
        }
        fun mob() : Boolean{
            return when {

                mob.toString().isEmpty() -> {
                    mobileno.error = "This field cannot be empty"
                    false
                }
                else -> {
                    mobileno.error = null
                    true
                }
            }
        }
        fun email() : Boolean{
            return when {

                eml.toString().isEmpty() -> {
                    email.error = "This field cannot be empty"
                    false
                }
                eml.toString().matches(emailPattern.toRegex()) -> {
                    email.error = null
                    true
                }
                else -> {
                    email.error = "Enter valid email address"
                    false
                }
            }
        }
       register.setOnClickListener {

           eml = email.text.toString().trim()
           mob = mobileno.text.toString().trim()
           passwd = spassword.text.toString().trim()
           user = susername.text.toString().trim()
           fname = fullname.text.toString().trim()

            if(!user() || !pas() || !fname()|| !mob() || !email())
                return@setOnClickListener
            Toast.makeText(applicationContext,"Welcome", Toast.LENGTH_SHORT).show()

           var userRole = 1
           if(user.toString().equals("sans.sk",true))
               userRole=0

           var u = User(fname.toString(),user.toString(),eml.toString(),mob.toString(),passwd.toString(),userRole)
           ref.push().setValue(u)
           var intent = Intent(this ,Login::class.java)
           startActivity(intent)
           finish()

        }
//      image.setOnClickListener {
//            val intent = Intent()
//           intent.action=Intent.ACTION_GET_CONTENT
//            intent.type="image/*"
//            startActivityForResult(intent,1)
        }

    }
//  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if(data!=null){
//            if(data.data != null){
//                selectedimg=data.data!!
//                image.setImageURI(selectedimg)
//            }
//        }
//    }


//}