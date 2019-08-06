package com.sumanth.picturesearch.model

/**
 * Data class for each photo
 */
data class Photo(val id:String, val owner:String, val secret:String, val farm:String,
                 val title:String, val ispublic:Int, val isfriend:Int, val isfamily:Int,
                 val url_s:String, val height_s:String, val width_s:String)

/**
 * Data class containing the photo array
 */
data class Photos(val page: Int, val pages: Int, val perpage: Int,
                  val total: String, val photo: Array<Photo>)

/**
 * Data class for the entire success response
 */
data class PhotosResponse(val photos: Photos, val stat: String)