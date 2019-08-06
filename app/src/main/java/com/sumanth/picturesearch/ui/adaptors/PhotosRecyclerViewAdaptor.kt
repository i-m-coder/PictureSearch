package com.sumanth.picturesearch.ui.adaptors

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView 
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sumanth.picturesearch.R
import com.sumanth.picturesearch.model.Photo
import com.sumanth.picturesearch.ui.MainActivity
import com.sumanth.picturesearch.ui.fragments.PhotoViewFragment


class PhotosRecyclerViewAdaptor(context: Context, var photos: Array<Photo>?) :
    RecyclerView.Adapter<PhotosRecyclerViewAdaptor.PhotosViewHolder>() {

    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private val mContext: Context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return PhotosViewHolder(layoutInflater.inflate(R.layout.photo_item, parent, false))
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bindView(photos!![position])
    }

    override fun getItemCount(): Int {
        return photos?.size ?: 0
    }

    class PhotosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val nameView: TextView = itemView.findViewById(R.id.title)
        private val pic: ImageView = itemView.findViewById(R.id.thumbnail)

        fun bindView(photo: Photo?) {
            nameView.text = photo?.title
            Glide.with(pic.context).load(photo?.url_s).into(pic);
            pic.setOnClickListener {
                 showPhotoView(photo?.url_s)
            }
        }

        fun showPhotoView(url: String?) {
            val mainActivity = itemView.context as MainActivity
            val fragmentManager = mainActivity.getSupportFragmentManager()
            val fragment = PhotoViewFragment()
            val args = Bundle()
            args.putString("imageUrl", url)
            fragment.setArguments(args)
            fragment.show(fragmentManager,"photo")
        }
    }

    fun updateData(newPhotoArray: Array<Photo>?) {
        photos = newPhotoArray
        notifyDataSetChanged()
    }



}