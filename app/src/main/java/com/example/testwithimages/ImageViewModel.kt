package com.example.testwithimages

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testwithimages.model.ListImage
import com.example.testwithimages.repository.RepositoryImage

class ImageViewModel(application: Application) : AndroidViewModel(application) {
    val readAllUrlImage: LiveData<ListImage>
    val repository: RepositoryImage
    val responseFlag: LiveData<Boolean>

    init {
        repository = RepositoryImage()
        repository.getListImage()
        readAllUrlImage = repository.readAllUrlImage
        responseFlag = repository.responseFlag
    }

    fun refreshData(){
        repository.getListImage()
    }


}