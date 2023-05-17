package com.example.firebase

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class SampleViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {
    var url = itemView.findViewById<TextView>(R.id.url)
    var title = itemView.findViewById<TextView>(R.id.tx_title)

    var parent = itemView.findViewById<ConstraintLayout>(R.id.parent_layout)
}