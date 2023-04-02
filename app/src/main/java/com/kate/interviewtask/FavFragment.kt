package com.kate.interviewtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.kate.interviewtask.databinding.FragmentFavBinding

class FavFragment : Fragment() {
    private var _binding: FragmentFavBinding? = null
    private val viewModel: FavViewModel by viewModels { FavViewModel.Factory }
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = FavAdapter {
            viewModel.updateFav(it)
        }
        binding.recycler.adapter = adapter
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