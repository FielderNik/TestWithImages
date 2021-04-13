package com.example.testwithimages

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageViewModel = ViewModelProvider(this).get(ImageViewModel::class.java)
        val adapterImageList = AdapterImageList(this)

        recyclerView = findViewById(R.id.rvImages)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)

        val isTablet = resources.getBoolean(R.bool.isTablet)
        if (isTablet){
            recyclerView.layoutManager = GridLayoutManager(this, 3)
            Log.d("milk", "isTablet: true")
        } else {
            recyclerView.layoutManager = GridLayoutManager(this, 2)
            Log.d("milk", "isTablet: false")
        }

        recyclerView.adapter = adapterImageList

        imageViewModel.readAllUrlImage.observe(this, Observer {
            Log.d("milk", "listImage: $it")
            adapterImageList.refreshImages(it)
        })

        imageViewModel.responseFlag.observe(this, Observer {
            if (it == false){
                Toast.makeText(this, "FAIL", Toast.LENGTH_SHORT).show()
            }
        })

        swipeRefreshLayout.setOnRefreshListener {
            imageViewModel.refreshData()
            Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT).show()
            swipeRefreshLayout.isRefreshing = false
        }
    }
}