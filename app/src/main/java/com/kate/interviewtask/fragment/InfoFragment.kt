package com.kate.interviewtask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.kate.interviewtask.databinding.FragmentInfoBinding
import com.kate.interviewtask.viewmodel.InfoViewModel

class InfoFragment : Fragment() {
    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: InfoViewModel by viewModels { InfoViewModel.Factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.source.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Glide.with(this)
                    .load(it.hdurl)
                    .into(binding.image)
                binding.title.text = it.title
                binding.description.text = it.description
                binding.date.text = it.date
                binding.copyright.text = it.copyright
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

