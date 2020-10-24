package com.example.tvshop

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshop.models.TV
import com.squareup.picasso.Picasso
import java.net.URL

class TVAdapter(var tvList: ArrayList<TV>, val context: Context) : RecyclerView.Adapter<TVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.tv_list_row, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(tvList[position], context)
    }

    override fun getItemCount(): Int {
        Log.v("hola caraoola", "kk")
        Log.v("hola caracola", tvList.size.toString())
        return tvList.size;
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(b: TV, context: Context){
            val url = "http://192.168.0.177:8080/img/tv-"
            val txt_brand: TextView = itemView.findViewById(R.id.textViewBrand)
            val txt_model: TextView = itemView.findViewById(R.id.textViewModel)
            val txt_price: TextView = itemView.findViewById(R.id.textViewPrice)
            val img: ImageView = itemView.findViewById(R.id.imageViewTV)

            txt_brand.text = b.brand
            txt_model.text = b.model
            txt_price.text = b.price

            val imageUrl = url + b.id.toString() + ".jpg"
            Picasso.with(context).load(imageUrl).into(img);
        }
    }
}