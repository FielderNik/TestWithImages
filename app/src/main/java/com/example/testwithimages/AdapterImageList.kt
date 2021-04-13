package com.example.testwithimages

import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.view.*
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testwithimages.model.ListImage

class AdapterImageList(val context: Context): RecyclerView.Adapter<AdapterImageList.ViewHolder>() {
    val inflater = LayoutInflater.from(context)
    var listImage = ListImage()




    fun getWidthDisplay(context: Context): Int{
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display: Display = windowManager.defaultDisplay
        val size: Point = Point()
        display.getRealSize(size)
        val displayWidth = size.x
        return displayWidth
    }



    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var image: ImageView


        init {
            image = view.findViewById(R.id.ivItem)


        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterImageList.ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_linear_view, parent, false))
    }

    override fun onBindViewHolder(holder: AdapterImageList.ViewHolder, position: Int) {
        val imageUrl = listImage[position]
        val widthSize = getWidthDisplay(context)


        Glide
            .with(holder.itemView)
            .load(imageUrl)
            .override(widthSize/2-4)
            .error(R.drawable.ic_baseline_image_not_supported_24)
            .placeholder(R.drawable.ic_baseline_image_not_supported_24)
            .centerCrop()
            .into(holder.image)

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, FullSizeImageActivity::class.java)
            intent.putExtra("imageUrl", listImage[position])
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listImage.size


    fun refreshImages(images: ListImage){
        this.listImage = images
        notifyDataSetChanged()
    }
}