package com.example.testwithimages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target

class FullSizeImageActivity : AppCompatActivity() {
    lateinit var ivFullSize: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_size_image)

        ivFullSize = findViewById(R.id.ivFullSize)

        val imageUrl = intent.extras?.get("imageUrl")

        Glide
            .with(this)
            .load(imageUrl)
            .error(R.drawable.ic_baseline_image_not_supported_24)
            .placeholder(R.drawable.ic_baseline_image_not_supported_24)
            .override(Target.SIZE_ORIGINAL)
            .centerCrop()
            .into(ivFullSize)
    }
}