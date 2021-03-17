package com.example.gogolookinterviewtronchen.imageR

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.gogolookinterviewtronchen.R
import com.example.gogolookinterviewtronchen.databinding.FragmentImageRBinding
import com.example.gogolookinterviewtronchen.ext.getVmFactory

class ImageRFragment : Fragment() {

    private val viewModel by viewModels<ImageRViewModel> { getVmFactory(
        ImageRFragmentArgs.fromBundle(
            requireArguments()
        ).searchProperties)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentImageRBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val adapter = ImageRAdapter(ImageRAdapter.ImageROnItemClickListener{

        })

        binding.imageRrec.adapter = adapter


        viewModel.searchString?.let { Log.d("Tron", it) }

        viewModel.searchItem.observe(viewLifecycleOwner, Observer {
            Log.d("Result", it.hits.toString())

            viewModel.searchImage.value = it.hits

            if (it.hits?.get(0) != null){
                Log.d("Result",  it.hits?.get(0)!!.pageURL.toString())
            }
        })

        viewModel.searchImage.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })


        // Inflate the layout for this fragment
        return binding.root
    }
}