package com.ubaya.uts_ubayalibrary_160419039.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ubaya.uts_ubayalibrary_160419039.R
import com.ubaya.uts_ubayalibrary_160419039.util.loadImage
import com.ubaya.uts_ubayalibrary_160419039.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.fragment_profile.*

class EditProfileFragment : Fragment() {
    private lateinit var userModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        //val idBook = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId

        userModel.refresh()

        observeViewModel()
    }

    fun observeViewModel()
    {
        userModel.userLD.observe(viewLifecycleOwner, Observer {
            txtInputEditName.setText(it.name)
            txtInputEditPP.setText(it.url)
            txtInputEditBG.setText(it.back_url)
        })
    }
}