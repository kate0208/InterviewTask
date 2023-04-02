package com.kate.interviewtask.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kate.interviewtask.databinding.SourceItemBinding
import com.kate.interviewtask.model.SourceModel

class FavAdapter(
    private val favListener: (SourceModel) -> Unit,
    private val imageListener: (SourceModel) -> Unit
) :
    ListAdapter<SourceModel, FavItemViewHolder>(FavDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavItemViewHolder {
        val itemBinding = SourceItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FavItemViewHolder(itemBinding, favListener, imageListener)
    }

    override fun onBindViewHolder(holder: FavItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}

class FavItemViewHolder(
    private val binding: SourceItemBinding,
    private val favListener: (SourceModel) -> Unit,
    private val imageListener: (SourceModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SourceModel) {
        Glide.with(binding.image)
            .load(item.url)
            .into(binding.image)

        binding.button.setOnClickListener {
            favListener(item)
        }

        binding.image.setOnClickListener {
            imageListener(item)
        }

        if (item.fav) {
            binding.button.text = "移除最愛"
        } else {
            binding.button.text = "加入最愛"
        }
    }

}

class FavDiffUtil : DiffUtil.ItemCallback<SourceModel>() {
    override fun areItemsTheSame(oldItem: SourceModel, newItem: SourceModel): Boolean {
        return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(oldItem: SourceModel, newItem: SourceModel): Boolean {
        return oldItem == newItem
    }

}