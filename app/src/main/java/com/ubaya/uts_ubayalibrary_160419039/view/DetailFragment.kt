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
import com.ubaya.uts_ubayalibrary_160419039.model.Book
import com.ubaya.uts_ubayalibrary_160419039.util.loadImage
import com.ubaya.uts_ubayalibrary_160419039.viewmodel.BookDetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {
    private lateinit var detailModel: BookDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailModel = ViewModelProvider(this).get(BookDetailViewModel::class.java)
        //val idBook = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId
        val idBook = DetailFragmentArgs.fromBundle(requireArguments()).bookId
        //val idStudent = 16055
        Log.d("showVolley", idBook.toString())

        detailModel.refresh(idBook)

        observeViewModel()
    }

    fun observeViewModel()
    {
        detailModel.booksLD.observe(viewLifecycleOwner, Observer {
            imgViewDetailBook.loadImage(it.url.toString(), progressBarDetailBook)
            txtBookTitle.setText(it.name)
            txtTanggal.setText(it.release)
            txtBookGenre.setText(it.genre)
            txtBookAuthor.setText(it.author)
            txtSummary.setText(it.summary)

        })
    }
}