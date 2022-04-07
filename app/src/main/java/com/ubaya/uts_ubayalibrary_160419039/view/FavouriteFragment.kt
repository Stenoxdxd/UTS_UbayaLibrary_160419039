package com.ubaya.uts_ubayalibrary_160419039.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.uts_ubayalibrary_160419039.R
import com.ubaya.uts_ubayalibrary_160419039.model.Book
import com.ubaya.uts_ubayalibrary_160419039.model.Books
import com.ubaya.uts_ubayalibrary_160419039.viewmodel.BorrowListViewModel
import com.ubaya.uts_ubayalibrary_160419039.viewmodel.FavListViewModel
import com.ubaya.uts_ubayalibrary_160419039.viewmodel.HomeListViewModel
import kotlinx.android.synthetic.main.fragment_borrowed.*
import kotlinx.android.synthetic.main.fragment_borrowed.refreshLayout
import kotlinx.android.synthetic.main.fragment_favourite.*
import kotlinx.android.synthetic.main.fragment_home.*

class FavouriteFragment : Fragment() {
    private lateinit var viewModel: FavListViewModel
    private val favListAdapter  = FavouriteAdapter(Books("", arrayListOf(Book(1, "", "", "", "", "", "", 0))))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(FavListViewModel::class.java)
        viewModel.refresh()


        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        //recViewTerbaru.layoutManager = LinearLayoutManager(context)
        recViewFav.layoutManager = layoutManager
        recViewFav.adapter = favListAdapter

        refreshLayout.setOnRefreshListener {
            recViewFav.visibility = View.GONE
            //txtError.visibility = View.GONE
            progressBarFav.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.booksLD.observe(viewLifecycleOwner, Observer {
            favListAdapter.updateBookList(it.books)
        })

        viewModel.booksLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                txtErrorFav.visibility = View.VISIBLE
            } else {
                txtErrorFav.visibility = View.GONE
            }
        })


        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                recViewFav.visibility = View.GONE
                progressBarFav.visibility = View.VISIBLE
            } else {
                recViewFav.visibility = View.VISIBLE
                progressBarFav.visibility = View.GONE
            }
        })
    }
}