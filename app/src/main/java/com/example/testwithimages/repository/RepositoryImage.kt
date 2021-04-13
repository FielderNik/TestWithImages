package com.example.testwithimages.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testwithimages.api.Api
import com.example.testwithimages.model.ListImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


class RepositoryImage() {
    val api = Api()
    val readAllUrlImage = MutableLiveData<ListImage>()
    val responseFlag = MutableLiveData<Boolean>()

    fun getListImage(){
        CoroutineScope(Dispatchers.IO).launch {
            var listImage: ListImage? = null
            try {
                listImage = api.retrofitService.getListImage()

                withContext(Dispatchers.Main){
                    responseFlag.value = true
                }

            } catch (e: Exception){
                withContext(Dispatchers.Main){
                    responseFlag.value = false
                }
            }


            withContext(Dispatchers.Main){
                listImage?.let {
                    readAllUrlImage.value = it
                }

            }
        }
    }

}