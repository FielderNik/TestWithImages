package com.example.testwithimages.service

import com.example.testwithimages.model.ListImage
import retrofit2.Call
import retrofit2.http.GET

interface ImageService {

    @GET("/task-m-001/list.php")
    suspend fun getListImage() : ListImage
}