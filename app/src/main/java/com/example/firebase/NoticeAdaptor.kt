package com.example.firebase

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class NoticeAdapter(var context: Context, var arrayNotice: ArrayList<Notice>) : RecyclerView.Adapter<SampleViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return SampleViewHolder(view)
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {

        holder.title.text = arrayNotice[position].title
        holder.url.text = arrayNotice[position].url
        holder.parent.setOnClickListener {

            var intent = Intent(context, NoticeView::class.java)
            intent.putExtra("URL",arrayNotice[position].url)
            context.startActivity(intent)

        }

    }


    override fun getItemCount(): Int {
        return arrayNotice.size
    }


}