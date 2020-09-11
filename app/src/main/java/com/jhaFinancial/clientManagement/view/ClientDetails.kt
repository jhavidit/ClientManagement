package com.jhaFinancial.clientManagement.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.jhaFinancial.clientManagement.R
import com.jhaFinancial.clientManagement.adapter.ClientDetailsAdapter
import com.jhaFinancial.clientManagement.databinding.FragmentClientDetailsBinding
import com.jhaFinancial.clientManagement.viewModel.ClientDetailsViewModel


class ClientDetails : Fragment() {
    private lateinit var navController: NavController
    private lateinit var adapter: ClientDetailsAdapter
    private lateinit var viewModel: ClientDetailsViewModel
    private lateinit var binding: FragmentClientDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_client_details, container, false
            )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getData()

    }

    @SuppressLint("LogNotTimber")
    private fun getData() {
        viewModel = ViewModelProvider(this).get(ClientDetailsViewModel::class.java)
        adapter = ClientDetailsAdapter(requireContext())
        viewModel.getClientDetails()
        binding.rvClientDetails.adapter = adapter
        viewModel.showCLientDetails.observe(viewLifecycleOwner, Observer {
            adapter.setCountryWiseTracker(it)
            binding.totalClientCount.text = it.size.toString()
            Log.d("test", it.toString())

        })
    }


}