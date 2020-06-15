package com.example.gettingdatafrominternet.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gettingdatafrominternet.databinding.GridImageItemBinding
import com.example.gettingdatafrominternet.network.MarsProperty

class PhotoGridAdapter(private val onClicklistener : OnclickListner) :
    ListAdapter<MarsProperty, PhotoGridAdapter.MarsPropertyViewHolder>(DiffCallback) {


    class MarsPropertyViewHolder(private var binding: GridImageItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(marsProperty: MarsProperty) {
            binding.property = marsProperty
            binding.executePendingBindings()
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<MarsProperty>() {
        override fun areItemsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem.id == newItem.id
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPropertyViewHolder {

        return MarsPropertyViewHolder(GridImageItemBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: MarsPropertyViewHolder, position: Int) {
        val marsProperty = getItem(position)
        holder.itemView.setOnClickListener{
            onClicklistener.onClick(marsProperty)
        }
        holder.bind(marsProperty)
    }

    class OnclickListner(val clickListner : (marsProperty : MarsProperty) -> Unit){
        fun onClick(marsProperty : MarsProperty) = clickListner(marsProperty)
    }
}