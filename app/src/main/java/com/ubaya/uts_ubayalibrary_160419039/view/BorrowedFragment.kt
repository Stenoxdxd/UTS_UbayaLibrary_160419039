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
import com.ubaya.uts_ubayalibrary_160419039.viewmodel.HomeListViewModel
import kotlinx.android.synthetic.main.fragment_borrowed.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.refreshLayout


class BorrowedFragment : Fragment() {
    private lateinit var viewModel: BorrowListViewModel
    private val BorrowListAdapter  = BorrowedListAdapter(Books("", arrayListOf(Book(0,"","","","","","", 0))))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_borrowed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(BorrowListViewModel::class.java)
        viewModel.refresh()

        btnBorrowHistory.setOnClickListener {
            val action = BorrowedFragmentDirections.actionBorrowHistoryFragment()
            Navigation.findNavController(it).navigate(action)
        }


        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        //recViewTerbaru.layoutManager = LinearLayoutManager(context)
        recViewBorrowed.layoutManager = layoutManager
        recViewBorrowed.adapter = BorrowListAdapter

        refreshLayout.setOnRefreshListener {
            recViewBorrowed.visibility = View.GONE
            //txtError.visibility = View.GONE
            progressBarBorrow.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.booksLD.observe(viewLifecycleOwner, Observer {
            BorrowListAdapter.updateBookList(it.books)
        })

        viewModel.booksLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                txtErrorBorrow.visibility = View.VISIBLE
            } else {
                txtErrorBorrow.visibility = View.GONE
            }
        })


        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                recViewBorrowed.visibility = View.GONE
                progressBarBorrow.visibility = View.VISIBLE
            } else {
                recViewBorrowed.visibility = View.VISIBLE
                progressBarBorrow.visibility = View.GONE
            }
        })
    }
}