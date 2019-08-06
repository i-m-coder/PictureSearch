package com.sumanth.picturesearch.viewmodel
import android.text.Editable
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sumanth.picturesearch.model.Photos
import com.sumanth.picturesearch.model.PhotosResponse
import com.sumanth.picturesearch.network.PictureService
import com.sumanth.picturesearch.network.RetrofitBuilder
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

private const val MIN_SEARCH_CHARACTERS = 3

/**
 * ViewModel to handle get photos service call based on search query and update
 * list with results.
 */
class PhotosViewModel : ViewModel(), Observable, SearchView.OnQueryTextListener{


    val searchText = MutableLiveData<String>()
    val photosLiveData = MutableLiveData<Photos>()

    init {
        /**
         * Add text change listener to Observable Field so that when ever user
         * enters or deletes a character then this callback is notified.
         * If character count is more than [MIN_SEARCH_CHARACTERS] then will perform
         * a service call to get photos.
         */
        /*val searchMediator = MutableLiveData<String>().apply {

        }*/

        /*searchText.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                println("Search query changed: $searchText")
                if (searchText.get() != null
                    && (searchText.get() as String).length > MIN_SEARCH_CHARACTERS) {
                    loadPhotoResults(searchText.get() as String)
                }
            }
        })*/
    }



    /**
     * Search photos based on provided search query and update [photosLiveData].
     */
    fun loadPhotoResults(searchQuery: String) {

//        if (searchQuery.length > MIN_SEARCH_CHARACTERS) {
        val flickerService = RetrofitBuilder.retrofit.create(PictureService::class.java)
        flickerService.getPhotos(searchQuery)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<PhotosResponse> {

                override fun onSuccess(photosResponse: PhotosResponse) {
                    println("onSuccess")
                    // update LiveData with latest response values.
                    photosLiveData.value = photosResponse.photos
                }

                override fun onSubscribe(d: Disposable) {
                    println("onSubscribe")
                }

                override fun onError(e: Throwable) {
                    println("OnError: ${e.toString()}")
                }
            })
//        } else {
//            println("Search query is too short to try. Please enter minimum 3 characters to search")
//            photosLiveData.value = null
//        }
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query!!.length>0){
            searchText.value = query;
            loadPhotoResults(query)
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }


}