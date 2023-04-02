package com.kate.interviewtask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.kate.interviewtask.R
import com.kate.interviewtask.databinding.FragmentMainTabBinding
import com.kate.interviewtask.pager.MainTabAdapter
import com.kate.interviewtask.viewmodel.MainTabViewModel

class MainTabFragment : Fragment() {
    private var _binding: FragmentMainTabBinding? = null

    private val binding get() = _binding!!

    private val viewModel: MainTabViewModel by viewModels { MainTabViewModel.Factory }

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

        viewModel.allCountLivedata.observe(viewLifecycleOwner, Observer {
            binding.tabLayout.getTabAt(0)?.text = getString(R.string.main_tab, it.toString())
        })

        viewModel.allFavCountLivedata.observe(viewLifecycleOwner, Observer {
            binding.tabLayout.getTabAt(1)?.text = getString(R.string.fav_tab, it.toString())
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}