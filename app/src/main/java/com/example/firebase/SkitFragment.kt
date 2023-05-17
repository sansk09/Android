package com.example.firebase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import java.util.ArrayList


class SkitFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var myview = inflater.inflate(R.layout.fragment_skit, container, false)
        val image = ArrayList<SlideModel>()
        image.add(SlideModel(R.drawable.sk1))
        image.add(SlideModel(R.drawable.sk2))
        image.add(SlideModel(R.drawable.sk3))
        image.add(SlideModel(R.drawable.sk4))
        image.add(SlideModel(R.drawable.sk6))
        image.add(SlideModel(R.drawable.sk7))
        image.add(SlideModel(R.drawable.sk8))
        image.add(SlideModel(R.drawable.sk9))
        image.add(SlideModel(R.drawable.sk10))
        image.add(SlideModel(R.drawable.sk11))
        image.add(SlideModel(R.drawable.sk12))
        val imageSlider = myview.findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(image)
        return myview
    }


}
