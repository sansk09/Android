package com.example.firebase

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Login : AppCompatActivity() {
    var paswd : String?=null
    var user : String?=null
    lateinit var forgotbutton : Button
    lateinit var loginbutton : Button
    lateinit var signupbutton : Button
    lateinit var username : TextInputEditText
    lateinit var password : TextInputEditText
    lateinit var shared : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        loginbutton=findViewById(R.id.login_button)
        signupbutton=findViewById(R.id.signup_button)
        username= findViewById(R.id.username_login)
        password= findViewById(R.id.password_login)
       var database = FirebaseDatabase.getInstance()
       var ref = database.getReference("user")
        shared = getSharedPreferences("Data",Context.MODE_PRIVATE)
        var isloign = shared.getBoolean("ISLOGIN",false)

        if (isloign){
            var intent = Intent(this@Login,Dashboard::class.java)
            startActivity(intent)
            finish()
        }else{
            fun f_user() : Boolean{

                return when {

                    user.toString().isEmpty() -> {
                        username.error = "This field cannot be empty"
                        false
                    }
                    user.toString().length > 30 -> {
                        username.error = "The number of characters entered is more than allowed"
                        false
                    }
                    else -> {
                        username.error = null
                        true
                    }
                }
            }

            fun pas() : Boolean{

                return when {

                    paswd.toString().isEmpty() -> {
                        password.error = "This field cannot be empty"
                        false
                    }
                    paswd.toString().length < 4 && paswd.toString().length != null -> {
                        password.error = "Enter at least 4 characters"
                        false
                    }
                    else -> {
                        password.error = null
                        true
                    }
                }

            }

            signupbutton.setOnClickListener {
                var intent = Intent(this , Signup::class.java)
                startActivity(intent)
            }
            loginbutton.setOnClickListener {
                user = username.text.toString().trim()
                paswd = password.text.toString().trim()
                if(!f_user() || !pas())
                    return@setOnClickListener

                ref.orderByChild("uname").equalTo(user).addListenerForSingleValueEvent(object :
                    ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if(snapshot.exists()){
                            for(child in snapshot.children){
                                var userDetails = child.value as HashMap<*,*>
                                var pass = userDetails["pass"]
                                var eml = userDetails["eml"]
                                var fname = userDetails["fname"]
                                var mobno = userDetails["mobno"]
                                var uname = userDetails["uname"]
                                var uRole = userDetails["userRole"]
                                //var image = userDetails["img"]

                                if(pass==paswd){
                                    val editor: SharedPreferences.Editor = shared.edit()
                                    editor.putBoolean("ISLOGIN",true)
                                    editor.putString("uname",uname.toString())
                                    editor.putString("fname",fname.toString())
                                    editor.putString("pass",pass.toString())
                                    editor.putString("uRole",uRole.toString())
                                    editor.putString("umail",eml.toString())
                                    editor.putString("mob",mobno.toString())
                                    editor.commit()
                                    Toast.makeText(this@Login,"Login sucessfull" , Toast.LENGTH_SHORT).show()

                                    var intent = Intent(this@Login,Dashboard::class.java)
                                    if (uRole==0L){
                                        intent.putExtra("isadmin",true)
                                    }else intent.putExtra("isadmin",false)
                                    startActivity(intent)
                                    finish()

                                }
                                else{
                                    Toast.makeText(this@Login,"Invailid Password" , Toast.LENGTH_SHORT).show()
                                    return
                                }
                            }
                        }
                        else{
                            Toast.makeText(this@Login,"No Account Found" , Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }
                }

                )


            }
        }


    }


    }
