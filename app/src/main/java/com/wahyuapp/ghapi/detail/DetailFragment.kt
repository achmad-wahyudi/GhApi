package com.wahyuapp.ghapi.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.wahyuapp.ghapi.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //accept args
        val argUsername = DetailFragmentArgs.fromBundle(requireArguments()).username
        val vmFactory = DetailViewModelFactory(argUsername)

        viewModel = ViewModelProvider(
            this, vmFactory
        ).get(DetailViewModel::class.java)

        binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }
}
