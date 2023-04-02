package com.kate.interviewtask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.kate.interviewtask.databinding.FragmentImageViewerBinding
import com.kate.interviewtask.viewmodel.ImageViewerViewModel

class ImageViewerFragment : Fragment() {
    private var _binding: FragmentImageViewerBinding? = null
    private val viewModel: ImageViewerViewModel by viewModels { ImageViewerViewModel.Factory }
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageViewerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.source.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Glide.with(this)
                    .load(it.hdurl)
                    .into(binding.image)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}