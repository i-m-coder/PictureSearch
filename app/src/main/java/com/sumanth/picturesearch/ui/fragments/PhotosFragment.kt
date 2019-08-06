package com.sumanth.picturesearch.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sumanth.picturesearch.R
import com.sumanth.picturesearch.databinding.FragmentPhotosBinding
import com.sumanth.picturesearch.ui.adaptors.PhotosRecyclerViewAdaptor
import com.sumanth.picturesearch.viewmodel.PhotosViewModel

class PhotosFragment:Fragment() {

    private lateinit var viewBinding: FragmentPhotosBinding
    private lateinit var photosViewModel: PhotosViewModel
    private lateinit var adaptor: PhotosRecyclerViewAdaptor
    private lateinit var photoList: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val context = context!!
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_photos, container, false)

        photosViewModel = ViewModelProviders.of(this).get(PhotosViewModel::class.java)
        photoList = viewBinding.pictureList
        adaptor = PhotosRecyclerViewAdaptor(context, null)
        photoList.adapter = adaptor
        photoList.layoutManager = GridLayoutManager(context, getColumnCount(context))

        photosViewModel.photosLiveData.observe(this, Observer { photos ->
            println("Update photos list: ${photos?.photo?.size}")
            viewBinding.pictureSearch.hideKeyboard();
            adaptor.updateData(photos?.photo)
        })
        viewBinding.pictureSearch.setOnQueryTextListener(photosViewModel)
        viewBinding.pictureSearch.setQuery(photosViewModel.searchText?.value,true)


        viewBinding.lifecycleOwner = this
        retainInstance = true
        return viewBinding.root
    }

    private fun getColumnCount(context: Context): Int {
        return context.resources.configuration.screenWidthDp / 100
    }


    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}