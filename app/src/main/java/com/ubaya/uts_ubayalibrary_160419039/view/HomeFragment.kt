package com.ubaya.uts_ubayalibrary_160419039.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.uts_ubayalibrary_160419039.viewmodel.HomeListViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import android.R
import android.util.Log

import androidx.recyclerview.widget.RecyclerView
import com.ubaya.uts_ubayalibrary_160419039.model.Book
import com.ubaya.uts_ubayalibrary_160419039.model.Books
import kotlinx.android.synthetic.main.fragment_home.refreshLayout
import kotlinx.android.synthetic.main.fragment_search.*


class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeListViewModel
    private val HomeListAdapter  = HomeAdapter(Books("", arrayListOf(Book(0,"","","","","","", 0))))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.ubaya.uts_ubayalibrary_160419039.R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(HomeListViewModel::class.java)
        viewModel.refresh()

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        //recViewTerbaru.layoutManager = LinearLayoutManager(context)
        recViewTerbaru.layoutManager = layoutManager
        recViewTerbaru.adapter = HomeListAdapter

        refreshLayout.setOnRefreshListener {
            recViewTerbaru.visibility = View.GONE
            //txtError.visibility = View.GONE
            progressBarTerbaru.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.booksLD.observe(viewLifecycleOwner, Observer {
            HomeListAdapter.updateBookList(it.books)
        })
        viewModel.booksLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                txtErrorHome.visibility = View.VISIBLE
            } else {
                txtErrorHome.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                recViewTerbaru.visibility = View.GONE
                progressBarTerbaru.visibility = View.VISIBLE
            } else {
                recViewTerbaru.visibility = View.VISIBLE
                progressBarTerbaru.visibility = View.GONE
            }
        })
    }
}