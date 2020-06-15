package com.example.gettingdatafrominternet.overview

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gettingdatafrominternet.R
import com.example.gettingdatafrominternet.databinding.FragmentOverViewBinding
import com.example.gettingdatafrominternet.network.MarsApiFilter


class OverViewFragment : Fragment() {
    private lateinit var binding : FragmentOverViewBinding
    private lateinit var viewModel : OverViewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_over_view, container, false)
        viewModel = ViewModelProvider(this).get(OverViewViewModel::class.java)
        binding.lifecycleOwner = this
        binding.overViewModel = viewModel

        binding.photosgrid.adapter = PhotoGridAdapter(PhotoGridAdapter.OnclickListner{
            viewModel.displayPropertyDetails(it)
        })

        viewModel.navigation.observe(viewLifecycleOwner, Observer {
            if(it != null){
                findNavController().navigate(OverViewFragmentDirections.actionToDetailsFragment(it))
                viewModel.displayPropertyDetailCompleted()
            }
        })
        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.updateFilter(when(item.itemId){
            R.id.buy -> MarsApiFilter.SHOW_BUY
            R.id.rent -> MarsApiFilter.SHOW_RENT
            else -> MarsApiFilter.SHOW_ALL
        })

        return true
    }


}