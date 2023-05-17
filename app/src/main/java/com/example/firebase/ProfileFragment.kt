package com.example.firebase

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {
lateinit var image : ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var myview = inflater.inflate(R.layout.fragment_profile,container,false)

        var sharedPreferences = context?.getSharedPreferences("Data",Context.MODE_PRIVATE)
        var fname = sharedPreferences?.getString("fname","")
        var pass = sharedPreferences?.getString("pass","")
        var mail = sharedPreferences?.getString("umail","")
        var uname = sharedPreferences?.getString("uname","")
        var mobile = sharedPreferences?.getString("mob","")
        image = myview.findViewById<ImageView>(R.id.user_img)
        myview.findViewById<TextView>(R.id.profile_fullname).setText(fname)
        myview.findViewById<TextView>(R.id.profile_username).setText(uname)
        myview.findViewById<TextView>(R.id.profile_eml).setText(mail)
        myview.findViewById<TextView>(R.id.profile_mobileno).setText(mobile)
        myview.findViewById<TextView>(R.id.profile_password).setText(pass)



        return myview
    }

}