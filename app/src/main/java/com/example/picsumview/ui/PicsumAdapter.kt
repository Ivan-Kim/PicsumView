package com.example.picsumview.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.example.picsumview.R
import com.example.picsumview.data.network.PicsumModel
import com.example.picsumview.databinding.ItemPicsumBinding

class PicsumAdapter :
    PagingDataAdapter<PicsumModel, PicsumAdapter.PicsumViewHolder>(PicsumComparator) {

    companion object {
        object PicsumComparator : DiffUtil.ItemCallback<PicsumModel>() {
            override fun areItemsTheSame(oldItem: PicsumModel, newItem: PicsumModel): Boolean {
                return oldItem.link == newItem.link
            }

            override fun areContentsTheSame(oldItem: PicsumModel, newItem: PicsumModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class PicsumViewHolder(val binding: ItemPicsumBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicsumViewHolder {
        val binding = ItemPicsumBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PicsumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PicsumViewHolder, position: Int) {
        getItem(position)?.let { picture ->
            with(holder.binding) {
                Glide.with(imgPicsum.context)
                    .load(picture.link)
                    .downsample(DownsampleStrategy.AT_MOST)
                    .placeholder(R.drawable.ic_placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .centerCrop()
                    .into(imgPicsum)
                txtAuthor.text = picture.author
                txtSource.setOnClickListener {
                    val viewIntent = Intent(Intent.ACTION_VIEW, Uri.parse(picture.url))
                    it.context.startActivity(viewIntent)
                }
            }
        }
    }

}
