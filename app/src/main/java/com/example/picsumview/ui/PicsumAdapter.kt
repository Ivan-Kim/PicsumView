package com.example.picsumview.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.picsumview.data.PicsumModel
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
        with (holder.binding) {
            imgPicsum.load(picture.url)
            txtAuthor.text = picture.author
        }
    }


    override fun getItemCount(): Int = pictures.size

}
