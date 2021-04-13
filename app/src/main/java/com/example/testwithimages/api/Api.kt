package com.example.testwithimages.api

import com.example.testwithimages.service.ImageService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {


        val retrofit = Retrofit.Builder()
            .baseUrl("http://dev-tasks.alef.im")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitService: ImageService = retrofit.create(ImageService::class.java)




}