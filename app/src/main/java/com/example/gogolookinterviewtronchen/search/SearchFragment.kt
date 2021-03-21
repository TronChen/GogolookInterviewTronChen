package com.example.gogolookinterviewtronchen.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.gogolookinterviewtronchen.GogolookApplication
import com.example.gogolookinterviewtronchen.R
import com.example.gogolookinterviewtronchen.data.History
import com.example.gogolookinterviewtronchen.databinding.FragmentSearchBinding
import com.example.gogolookinterviewtronchen.ext.getVmFactory
import java.util.*
import java.util.stream.Collectors.toList


class SearchFragment : Fragment() {

    private val viewModel by viewModels<SearchViewModel> { getVmFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val adapter = SearchAdapter(SearchAdapter.HistoryOnItemClickListener{
            it.inputString?.let { it1 -> sendMessage(it1) }
            viewModel.updateHistory(it)
            hideSoftKeyboard(GogolookApplication.INSTANCE , binding.editTextSearch)
        })

        binding.historyRec.adapter = adapter

        openSoftKeyboard(GogolookApplication.INSTANCE , binding.editTextSearch)

        binding.editTextSearch.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    if (viewModel.inputString.value == null){
                        sendMessage("")
                        viewModel.addHistory(History(inputString = ""))
                    }else{
                        sendMessage(viewModel.inputString.value!!)
                        viewModel.addHistory(History(inputString = viewModel.inputString.value!!))
                    }
                    hideSoftKeyboard(GogolookApplication.INSTANCE , binding.editTextSearch)
                    true
                }
                else -> false
            }
        }

        binding.textClear.setOnClickListener {
            viewModel.clearHistory()
        }

        viewModel.histories.observe(viewLifecycleOwner, Observer { it ->
            if(it.isNullOrEmpty()) {
                binding.textClear.visibility = View.GONE
            }else{
                binding.textClear.visibility = View.VISIBLE
            }

            it.forEach{
                Log.d("history", it.inputString.toString())
                Log.d("history", it.date.toString())
            }

            adapter.submitList(it)
        })

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onPause() {
        super.onPause()
        viewModel.navigateToImageResult()
    }

    private fun openSoftKeyboard(context: Context, view: View) {
        view.requestFocus()
        // open the soft keyboard
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    private fun hideSoftKeyboard(context: Context, view: View){
        view.requestFocus()
        // hide the soft keyboard
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    private fun sendMessage(inputString: String){
        findNavController().navigate(SearchFragmentDirections.actionGlobalImageRFragment(inputString))
    }





}