package com.ubaya.uts_ubayalibrary_160419039.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.uts_ubayalibrary_160419039.R
import com.ubaya.uts_ubayalibrary_160419039.model.Book
import com.ubaya.uts_ubayalibrary_160419039.model.Books
import com.ubaya.uts_ubayalibrary_160419039.util.loadImage
import kotlinx.android.synthetic.main.book_list_home_item.view.*
import kotlinx.android.synthetic.main.book_list_item.view.*

class HomeAdapter(val bookList: Books):RecyclerView.Adapter<HomeAdapter.HomeViewHolder>(){
    class HomeViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.book_list_item, parent, false)

        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.view.txtBookListTitle.text = bookList.books[position].name
        holder.view.imgBookList.loadImage(bookList.books[position].url, holder.view.progressBarBookList)
        holder.view.btnDetail.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeDetail(bookList.books[position].id!!.toInt())
            //val action = StudentListFragmentDirections.actionStudentDetail()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return bookList.books.size
    }

    fun updateBookList(newBookList: List<Book>) {
        bookList.books.clear()
        bookList.books.addAll(newBookList)
        notifyDataSetChanged()
    }
}