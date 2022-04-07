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
import kotlinx.android.synthetic.main.book_list_item.view.*

class BorrowHistoryAdapter(val borrowHistoryList: Books): RecyclerView.Adapter<BorrowHistoryAdapter.BorrowHistoryViewHolder>(){
    class BorrowHistoryViewHolder(var view: View) : RecyclerView.ViewHolder(view)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BorrowHistoryAdapter.BorrowHistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.book_list_item, parent, false)

        return BorrowHistoryAdapter.BorrowHistoryViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: BorrowHistoryAdapter.BorrowHistoryViewHolder,
        position: Int
    ) {
        holder.view.txtBookListTitle.text = borrowHistoryList.books[position].name
        holder.view.imgBookList.loadImage(borrowHistoryList.books[position].url, holder.view.progressBarBookList)
        holder.view.btnDetail.setOnClickListener{
            val action = BorrowHistoryFragmentDirections.actionDetailHistoryFragment(borrowHistoryList.books[position].id!!.toInt())
            //val action = StudentListFragmentDirections.actionStudentDetail()
            Navigation.findNavController(it).navigate(action)
        }
    }


    override fun getItemCount(): Int {
        return borrowHistoryList.books.size
    }

    fun updateBookList(newBookList: List<Book>) {
        borrowHistoryList.books.clear()
        borrowHistoryList.books.addAll(newBookList)
        notifyDataSetChanged()
    }
}