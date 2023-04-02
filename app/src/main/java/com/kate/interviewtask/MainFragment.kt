package com.kate.interviewtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.kate.interviewtask.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null

    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels { MainViewModel.Factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MainAdapter {
            viewModel.updateFav(it)
        }
        binding.tasksList.adapter = adapter
        viewModel.sourceLivedata.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        viewModel.updateSource()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}