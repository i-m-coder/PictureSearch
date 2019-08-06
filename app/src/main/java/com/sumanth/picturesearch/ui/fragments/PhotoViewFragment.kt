package com.sumanth.picturesearch.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import uk.co.senab.photoview.PhotoView;
import com.sumanth.picturesearch.R

class PhotoViewFragment :DialogFragment() {

    private lateinit var rootView: View;
    private lateinit var photoView: PhotoView;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
          rootView = inflater.inflate(R.layout.photo, container, false)
        val url = arguments?.getString("imageUrl")
        val photoView = rootView.findViewById<PhotoView>(R.id.iv_photo)
        Glide.with(this).load(url).into(photoView)
        return rootView
    }

  open fun close(){
      photoView.setImageDrawable(null)
      this@PhotoViewFragment.dismiss()
  }

}