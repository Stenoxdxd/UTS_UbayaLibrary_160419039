package com.ubaya.uts_ubayalibrary_160419039.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ubaya.uts_ubayalibrary_160419039.R
import com.ubaya.uts_ubayalibrary_160419039.util.loadImage
import com.ubaya.uts_ubayalibrary_160419039.viewmodel.BookDetailViewModel
import com.ubaya.uts_ubayalibrary_160419039.viewmodel.BorrowDetailViewModel
import kotlinx.android.synthetic.main.fragment_borrow_detail.*
import kotlinx.android.synthetic.main.fragment_detail.*

class BorrowDetailFragment : Fragment() {
    private lateinit var detailModel: BorrowDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_borrow_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailModel = ViewModelProvider(this).get(BorrowDetailViewModel::class.java)
        //val idBook = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId
        val idBorrow = BorrowDetailFragmentArgs.fromBundle(requireArguments()).borrowId
        //val idStudent = 16055
        Log.d("showVolley", idBorrow.toString())

        detailModel.refresh(idBorrow)

        observeViewModel()
    }

    fun observeViewModel()
    {
        detailModel.borrowsLD.observe(viewLifecycleOwner, Observer {
            imgBorrowDetailBook.loadImage(it.url.toString(), progressBarBorrowDetail)
            txtBorrowDetailTitle.setText(it.name)
            txtTglPinjam.setText(it.pinjam)
            txtTglKembali.setText(it.kembali)

            if(it.dikembalikan == 1)
            {
                btnReturn.isEnabled = false
                btnReturn.setVisibility(View.GONE)
            }
        })
    }
}