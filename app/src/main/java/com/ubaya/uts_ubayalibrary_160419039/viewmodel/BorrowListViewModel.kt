package com.ubaya.uts_ubayalibrary_160419039.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.uts_ubayalibrary_160419039.model.Book
import com.ubaya.uts_ubayalibrary_160419039.model.Books
import com.ubaya.uts_ubayalibrary_160419039.model.BorrowDetail

class BorrowListViewModel(application: Application): AndroidViewModel(application) {
    val booksLD = MutableLiveData<Books>()
    val booksLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh() {
        booksLoadErrorLD.value = false
        loadingLD.value = true
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://nmp160419039.000webhostapp.com/ANMP/UTS/show_borrows.php?id=1"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<Books>() { }.type
                val result = Gson().fromJson<Books>(it, sType)
                booksLD.value = result
                loadingLD.value = false
                //Log.d("showvoley", it)
                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                booksLoadErrorLD.value = true
                loadingLD.value = false
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}