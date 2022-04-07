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
import com.ubaya.uts_ubayalibrary_160419039.model.User

class ProfileViewModel(application: Application): AndroidViewModel(application) {
    val userLD = MutableLiveData<User>()
    val userLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh() {
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://nmp160419039.000webhostapp.com/ANMP/UTS/show_profile.php?id=1"
        Log.d("showVolley", url)
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                    response ->
                val result = Gson().fromJson<User>(response, User::class.java)
                userLD.value = result
                Log.d("showVolley", response.toString())
                Log.d("showVolley", url)
            },
            {
                Log.d("showvoley", it.toString())
                userLoadErrorLD.value = false
                loadingLD.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
        userLoadErrorLD.value = false
        loadingLD.value = false
    }
}