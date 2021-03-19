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
import com.example.gogolookinterviewtronchen.databinding.FragmentSearchBinding
import com.example.gogolookinterviewtronchen.ext.getVmFactory
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

        openSoftKeyboard(GogolookApplication.INSTANCE , binding.editTextSearch)

        binding.editTextSearch.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    sendMessage(viewModel.inputString.value!!)
                    saveToHistory(viewModel.inputString.value!!)
                    hideSoftKeyboard(GogolookApplication.INSTANCE , binding.editTextSearch)
                    true
                }
                else -> false
            }
        }

        viewModel.inputString.observe(viewLifecycleOwner, Observer {
//            Log.d("Tron",it)
//            UserManager.history = it
        })

        UserManager.history?.let { Log.d("UserManager", it) }

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

    private fun saveToHistory(inputString: String){
//
//        val history = mutableListOf<String>()
//
//        if (UserManager.history == null){
//            UserManager.history = inputString
//        }else{
//            history.add(inputString)
//            history.add(UserManager.history!!)
//            UserManager.history = history.toString().filter { it != '[' && it != ']' && it != ',' }.split("").toList().toString()
//        }
    }





}