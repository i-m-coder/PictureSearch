package com.sumanth.picturesearch.network

import com.sumanth.picturesearch.model.PhotosResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureService {

    /**
     * Get photos based on the search query.
     * @return Single observable with [PhotosResponse] as type to perform network calls in required thread.
     */
    @GET("?method=flickr.photos.search&api_key=675894853ae8ec6c242fa4c077bcf4a0&extras=url_s&format=json&nojsoncallback=1")
    fun getPhotos(@Query("text") searchText:String): Single<PhotosResponse>


}