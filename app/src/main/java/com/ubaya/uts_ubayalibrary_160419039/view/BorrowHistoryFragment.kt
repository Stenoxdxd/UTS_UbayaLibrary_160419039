package com.ubaya.uts_ubayalibrary_160419039.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.uts_ubayalibrary_160419039.R
import com.ubaya.uts_ubayalibrary_160419039.model.Book
import com.ubaya.uts_ubayalibrary_160419039.model.Books
import com.ubaya.uts_ubayalibrary_160419039.viewmodel.BorrowHistoryViewModel
import com.ubaya.uts_ubayalibrary_160419039.viewmodel.BorrowListViewModel
import kotlinx.android.synthetic.main.fragment_borrow_history.*
import kotlinx.android.synthetic.main.fragment_borrowed.*
import kotlinx.android.synthetic.main.fragment_borrowed.refreshLayout
import kotlinx.android.synthetic.main.fragment_home.*

class BorrowHistoryFragment : Fragment() {
    private lateinit var viewModel: BorrowHistoryViewModel
    private val BorrowListAdapter  = BorrowHistoryAdapter(Books("", arrayListOf(Book(0,"","","","","","", 0))))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_borrow_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(BorrowHistoryViewModel::class.java)
        viewModel.refresh()

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        //recViewTerbaru.layoutManager = LinearLayoutManager(context)
        recViewBorrowHistory.layoutManager = layoutManager
        recViewBorrowHistory.adapter = BorrowListAdapter

        refreshLayout.setOnRefreshListener {
            recViewBorrowHistory.visibility = View.GONE
            //txtError.visibility = View.GONE
            progressBarBorrowHistory.visibility = View.VISIBLE
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
                txtErrorBorrowHistory.visibility = View.VISIBLE
            } else {
                txtErrorBorrowHistory.visibility = View.GONE
            }
        })


        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                recViewBorrowHistory.visibility = View.GONE
                progressBarBorrowHistory.visibility = View.VISIBLE
            } else {
                recViewBorrowHistory.visibility = View.VISIBLE
                progressBarBorrowHistory.visibility = View.GONE
            }
        })
    }
}