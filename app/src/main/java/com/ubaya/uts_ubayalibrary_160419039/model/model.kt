package com.ubaya.uts_ubayalibrary_160419039.model

import com.google.gson.annotations.SerializedName

data class Books(
    val status:String?,
    val books:ArrayList<Book>
)

data class BookDetail(
    val status:String?,
    val book:Book
)

data class Book(
    val id:Int?,
    val name:String?,
    @SerializedName("release_date")
    val release:String?,
    val summary:String?,
    val author:String?,
    val genre:String?,
    val url:String?,
    val borrowed:Int?
)

data class BorrowDetail(
    val status:String?,
    val borrow:Borrow
)

data class Borrow(
    val id:Int?,
    val users_id:Int?,
    val books_id:Int?,
    @SerializedName("borrow_date")
    val pinjam:String?,
    @SerializedName("return_date")
    val kembali:String?,
    @SerializedName("returned")
    val dikembalikan:Int?,
    val name:String?,
    @SerializedName("release_date")
    val release:String?,
    val summary:String?,
    val author:String?,
    val genre:String?,
    val url:String??
)

data class UserDetail(
    val status:String?,
    val user: User
)

data class User(
    val id:Int?,
    val name:String?,
    val password:String?,
    val url:String?,
    val back_url:String?
)
