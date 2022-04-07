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

class BookDetailViewModel(application: Application): AndroidViewModel(application) {
    val booksLD = MutableLiveData<Book>()
    val booksLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh(idBook: Int) {

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://nmp160419039.000webhostapp.com/ANMP/UTS/show_book_detail.php?id=" + idBook.toString()
        Log.d("showVolley", url)
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                    response ->
                val result = Gson().fromJson<Book>(response, Book::class.java)
                booksLD.value = result
                Log.d("showVolley", response.toString())
                Log.d("showVolley", url)
            },
            {
                Log.d("showvoley", it.toString())
                booksLoadErrorLD.value = false
                loadingLD.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
        booksLoadErrorLD.value = false
        loadingLD.value = false
    }
}