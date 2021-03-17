package com.example.gogolookinterviewtronchen.imageR

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gogolookinterviewtronchen.GogolookApplication
import com.example.gogolookinterviewtronchen.MainActivity
import com.example.gogolookinterviewtronchen.R
import com.example.gogolookinterviewtronchen.databinding.FragmentImageRBinding
import com.example.gogolookinterviewtronchen.ext.getVmFactory
import kotlinx.android.synthetic.main.activity_main.*

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

        val adapter = ImageRAdapter()

        binding.imageRrec.adapter = adapter

        viewModel.searchString?.let { Log.d("Tron", it) }

        viewModel.searchItem.observe(viewLifecycleOwner, Observer {
            Log.d("Result", it.hits.toString())
            viewModel.searchImage.value = it.hits
        })

        viewModel.searchImage.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        switchLayoutManager(binding.imageRrec)

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun switchLayoutManager(recyclerView: RecyclerView){
        val activity = activity as MainActivity

        activity.gridLayout.setOnClickListener {
            it.visibility = View.GONE
            activity.linearLayout.visibility = View.VISIBLE
            recyclerView.layoutManager = GridLayoutManager(GogolookApplication.INSTANCE.applicationContext, 2)
        }

        activity.linearLayout.setOnClickListener {
            it.visibility = View.GONE
            activity.gridLayout.visibility = View.VISIBLE
            recyclerView.layoutManager = LinearLayoutManager(GogolookApplication.INSTANCE.applicationContext)
        }
    }
}