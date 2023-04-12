package com.example.picsumview.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.example.picsumview.data.network.PicsumModel
import com.example.picsumview.databinding.ItemPicsumBinding

class PicsumAdapter : RecyclerView.Adapter<PicsumAdapter.PicsumViewHolder>() {

    var pictures: List<PicsumModel> = emptyList()

    inner class PicsumViewHolder(val binding: ItemPicsumBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicsumViewHolder {
        val binding = ItemPicsumBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PicsumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PicsumViewHolder, position: Int) {
        val picture = pictures[position]
        Log.i("Adapter", picture.toString())
        with (holder.binding) {
            Glide.with(imgPicsum.context)
                .load(picture.link)
                .downsample(DownsampleStrategy.AT_MOST)
                .centerCrop()
                .into(imgPicsum)
            txtAuthor.text = picture.author
        }
    }


    override fun getItemCount(): Int = pictures.size

}
