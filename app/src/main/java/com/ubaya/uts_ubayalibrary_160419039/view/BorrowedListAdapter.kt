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

class BorrowedListAdapter(val borrowList: Books):RecyclerView.Adapter<BorrowedListAdapter.BorrowedViewHolder>(){
    class BorrowedViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BorrowedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.book_list_item, parent, false)

        return BorrowedViewHolder(view)

    }

    override fun onBindViewHolder(holder: BorrowedViewHolder, position: Int) {
        holder.view.txtBookListTitle.text = borrowList.books[position].name
        holder.view.imgBookList.loadImage(borrowList.books[position].url, holder.view.progressBarBookList)
        holder.view.btnDetail.setOnClickListener{
            val action = BorrowedFragmentDirections.actionBorrowDetail(borrowList.books[position].id!!.toInt())
            //val action = StudentListFragmentDirections.actionStudentDetail()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return borrowList.books.size
    }

    fun updateBookList(newBookList: List<Book>) {
        borrowList.books.clear()
        borrowList.books.addAll(newBookList)
        notifyDataSetChanged()
    }
}
