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

class FavouriteAdapter(val favList: Books): RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>() {
    class FavouriteViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.book_list_item, parent, false)

        return FavouriteAdapter.FavouriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.view.txtBookListTitle.text = favList.books[position].name
        holder.view.imgBookList.loadImage(favList.books[position].url, holder.view.progressBarBookList)
        holder.view.btnDetail.setOnClickListener{
            val action = FavouriteFragmentDirections.actionFavDetailFragment(favList.books[position].id!!.toInt())
            //val action = StudentListFragmentDirections.actionStudentDetail()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return favList.books.size
    }

    fun updateBookList(newBookList: List<Book>) {
        favList.books.clear()
        favList.books.addAll(newBookList)
        notifyDataSetChanged()
    }
}