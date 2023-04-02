package com.kate.interviewtask.fragment

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.davemorrissey.labs.subscaleview.ImageSource
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
                (activity as AppCompatActivity).supportActionBar?.title = it.title

                Glide.with(this)
                    .asBitmap()
                    .load(it.hdurl)
                    .into(object : CustomTarget<Bitmap>() {
                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                            binding.image.setImage(ImageSource.bitmap(resource))
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {

                        }
                    })

            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}