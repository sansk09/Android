package com.example.firebase

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.database.FirebaseDatabase

class NoticeFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        var myview = inflater.inflate(R.layout.fragment_notice2, container, false)
        var database =FirebaseDatabase.getInstance()
        var ref = database.getReference("notice")
        myview.findViewById<Button>(R.id.bt_create).setOnClickListener {
            var noticeString = myview.findViewById<EditText>(R.id.notice_name).text.toString()
            var noticeUrl = myview.findViewById<EditText>(R.id.notice_url).text.toString()
            var notice = Notice(noticeString,noticeUrl)
            ref.push().setValue(notice)
            activity?.let{
                val intent = Intent (it, Dashboard::class.java)
                it.startActivity(intent)
            }


        }
        return myview
    }

}