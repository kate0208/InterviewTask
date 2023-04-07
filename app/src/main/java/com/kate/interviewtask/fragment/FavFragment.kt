package com.kate.interviewtask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.kate.interviewtask.adapter.FavAdapter
import com.kate.interviewtask.databinding.FragmentFavBinding
import com.kate.interviewtask.viewmodel.FavViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
        val adapter = FavAdapter({
            viewModel.updateFav(it)
        }, {
            val action =
                MainTabFragmentDirections.actionMainTabFragmentToInfoFragment(it.date)
            view.findNavController().navigate(action)
        })
        binding.recycler.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.sourceFlow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}