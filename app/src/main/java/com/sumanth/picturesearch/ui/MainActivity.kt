package com.sumanth.picturesearch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sumanth.picturesearch.R
import com.sumanth.picturesearch.ui.fragments.PhotosFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         if(savedInstanceState == null)
            this.supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, PhotosFragment()).commit()
    }
}
