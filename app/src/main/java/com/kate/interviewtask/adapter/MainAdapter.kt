package com.kate.interviewtask.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kate.interviewtask.R
import com.kate.interviewtask.databinding.SourceItemBinding
import com.kate.interviewtask.model.SourceModel

class MainAdapter(
    private val favListener: (SourceModel) -> Unit,
    private val imageListener: (SourceModel) -> Unit
) :
    PagingDataAdapter<SourceModel, ItemViewHolder>(MainDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemBinding = SourceItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ItemViewHolder(itemBinding, favListener, imageListener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item)
    }

}

class ItemViewHolder(
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
            binding.button.setImageResource(R.drawable.add_fav)
        } else {
            binding.button.setImageResource(R.drawable.remove_fav)
        }
    }

}

class MainDiffUtil : DiffUtil.ItemCallback<SourceModel>() {
    override fun areItemsTheSame(oldItem: SourceModel, newItem: SourceModel): Boolean {
        return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(oldItem: SourceModel, newItem: SourceModel): Boolean {
        return oldItem == newItem
    }

}