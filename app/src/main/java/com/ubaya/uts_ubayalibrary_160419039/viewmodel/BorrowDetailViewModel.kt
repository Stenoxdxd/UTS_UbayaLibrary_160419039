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
import com.ubaya.uts_ubayalibrary_160419039.model.Book
import com.ubaya.uts_ubayalibrary_160419039.model.Borrow

class BorrowDetailViewModel(application: Application): AndroidViewModel(application) {
    val borrowsLD = MutableLiveData<Borrow>()
    val borrowsLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh(idBorrow: Int) {

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://nmp160419039.000webhostapp.com/ANMP/UTS/show_borrow_detail.php?id=" + idBorrow.toString()
        Log.d("showVolley", url)
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                    response ->
                val result = Gson().fromJson<Borrow>(response, Borrow::class.java)
                borrowsLD.value = result
                Log.d("showVolley", response.toString())
                Log.d("showVolley", url)
            },
            {
                Log.d("showvoley", it.toString())
                borrowsLoadErrorLD.value = false
                loadingLD.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
        borrowsLoadErrorLD.value = false
        loadingLD.value = false
    }
}