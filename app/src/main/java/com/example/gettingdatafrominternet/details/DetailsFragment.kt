package com.example.gettingdatafrominternet.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.gettingdatafrominternet.R
import com.example.gettingdatafrominternet.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {

    private lateinit var binding : FragmentDetailsBinding
    private lateinit var viewModel : DetailViewModel
    private lateinit var viewModelFactory: DetailViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        val argument = DetailsFragmentArgs.fromBundle(arguments!!).selectedProperty
        val application = requireNotNull(activity).application

        viewModelFactory = DetailViewModelFactory(argument, application)
        viewModel = ViewModelProvider(this,viewModelFactory).get(DetailViewModel::class.java)

        binding.lifecycleOwner = this
        binding.detailsViewModel = viewModel

        return binding.root
    }


}