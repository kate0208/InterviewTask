package com.kate.interviewtask.fragment

import android.app.DownloadManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.davemorrissey.labs.subscaleview.ImageSource
import com.kate.interviewtask.databinding.FragmentImageViewerBinding
import com.kate.interviewtask.viewmodel.ImageViewerViewModel
import java.io.File


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
        binding.download.setOnClickListener {
            val hdurl = viewModel.source.value?.hdurl ?: return@setOnClickListener
            downloadTask(hdurl)
        }
    }

    private fun downloadTask(url: String): Boolean {
        if (!url.startsWith("http")) {
            return false
        }
        val name = url.substring(url.lastIndexOf('/') + 1, url.length)
        try {
            val file = File(Environment.getExternalStorageDirectory(), "Download")
            if (!file.exists()) {
                file.mkdirs()
            }
            val result = File(file.absolutePath + File.separator + name)
            val downloadManager = getSystemService(requireContext(), DownloadManager::class.java)
            val request = DownloadManager.Request(Uri.parse(url))
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
            request.setDestinationUri(Uri.fromFile(result))
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            downloadManager?.enqueue(request)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}