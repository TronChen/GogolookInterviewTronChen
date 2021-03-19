package com.example.gogolookinterviewtronchen.imageResult

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
import app.appworks.school.stylish.network.LoadApiStatus
import com.example.gogolookinterviewtronchen.GogolookApplication
import com.example.gogolookinterviewtronchen.MainActivity
import com.example.gogolookinterviewtronchen.databinding.FragmentImageResultBinding
import com.example.gogolookinterviewtronchen.ext.getVmFactory
import kotlinx.android.synthetic.main.activity_main.*

class ImageResultFragment : Fragment() {

    private val viewModel by viewModels<ImageResultViewModel> { getVmFactory(
        ImageResultFragmentArgs.fromBundle(
            requireArguments()
        ).searchProperties)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentImageResultBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val adapter = ImageResultAdapter()

        binding.imageResultRec.adapter = adapter

        viewModel.searchString?.let { Log.d("Tron", it) }

        viewModel.searchItem.observe(viewLifecycleOwner, Observer {
            Log.d("Result", it.hits.toString())
            viewModel.searchImage.value = it.hits
        })

        viewModel.pagingDataSearchImage.observe(viewLifecycleOwner, Observer {
            (binding.imageResultRec.adapter as ImageResultAdapter).submitList(it)
        })

        switchLayoutManager(binding.imageResultRec)

        binding.layoutSwipeRefreshImageResult.setOnRefreshListener {
            viewModel.refresh()
        }

        viewModel.status.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it != LoadApiStatus.LOADING)
                    binding.layoutSwipeRefreshImageResult.isRefreshing = false
            }
        })


        // Inflate the layout for this fragment
        return binding.root
    }

    private fun switchLayoutManager(recyclerView: RecyclerView){
        val activity = activity as MainActivity

        activity.textSearch.text = viewModel.searchString

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