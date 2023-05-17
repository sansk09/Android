package com.example.firebase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ViewNotice : Fragment() {
    lateinit var recyclerview : RecyclerView
    var arrayNotice = ArrayList<Notice>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var myview= inflater.inflate(R.layout.fragment_view_notice, container, false)
        recyclerview = myview.findViewById(R.id.recyclerview_notice)
        recyclerview.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        var database = FirebaseDatabase.getInstance()
        var noticeDatabaseReference = database.getReference("notice")


        noticeDatabaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (child in snapshot.children){

                    var noticeValue = child.value as HashMap<*,*>

                    var title = noticeValue["title"]
                    var url = noticeValue["url"] as String

                    var notice = Notice(title.toString(), url = url)
                    arrayNotice.add(notice)
                }

                recyclerview.adapter = NoticeAdapter(context!!,arrayNotice)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })



        return myview
    }
}


