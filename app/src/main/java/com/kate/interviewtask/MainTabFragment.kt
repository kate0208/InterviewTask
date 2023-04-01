package com.kate.interviewtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kate.interviewtask.databinding.FragmentMainTabBinding

class MainTabFragment : Fragment() {
    private var _binding: FragmentMainTabBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewpager.adapter = MainTabAdapter(childFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewpager)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}