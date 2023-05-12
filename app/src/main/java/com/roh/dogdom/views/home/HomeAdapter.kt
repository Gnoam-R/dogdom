package com.roh.dogdom.views.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roh.dogdom.data.home.MainPost
import com.roh.dogdom.databinding.ItemHomeVerticalSecondRecyclerBinding

class HomeAdapter(var AdapterItem: MainPost)
    : RecyclerView.Adapter<RecyclerView.ViewHolder> () {

    private var listener : OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick (pos : Int)
    }
    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }

    inner class MultiViewHolder(private val binding: ItemHomeVerticalSecondRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivPost = binding.ivPost
        val ivProfile = binding.ivProfile

        val tvName = binding.tvName
        val tvLike = binding.tvLike
        val tvMessage = binding.tvMessage
        val tvShare = binding.tvShare

        val btFollow = binding.btFollow

        fun bind(AdapterItem: MainPost) {
            val pos = adapterPosition
            ivPost.setImageResource(AdapterItem.getImageMembers()[pos])
            ivProfile.setImageResource(AdapterItem.getProfileMembers()[pos])

            tvName.text = AdapterItem.getNameMembers()[pos]
            tvLike.text = AdapterItem.getLikeMembers()[pos]
            tvMessage.text = AdapterItem.getMessageMembers()[pos]
            tvShare.text = AdapterItem.getShareMembers()[pos]

            if(pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(pos)
                }
            }
        }
    }


    inner class SingleViewHolder(private val binding: ItemHomeVerticalSecondRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivPost = binding.ivPost
        val ivProfile = binding.ivProfile

        val tvName = binding.tvName
        val tvLike = binding.tvLike
        val tvMessage = binding.tvMessage
        val tvShare = binding.tvShare

        val btFollow = binding.btFollow

        fun bind(AdapterItem: MainPost) {
            val pos = adapterPosition
            ivPost.setImageResource(AdapterItem.getImageMembers()[pos])
            ivProfile.setImageResource(AdapterItem.getProfileMembers()[pos])

            tvName.text = AdapterItem.getNameMembers()[pos]
            tvLike.text = AdapterItem.getLikeMembers()[pos]
            tvMessage.text = AdapterItem.getMessageMembers()[pos]
            tvShare.text = AdapterItem.getShareMembers()[pos]

            if(pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(pos)
                }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val singleBinding = ItemHomeVerticalSecondRecyclerBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup, false)
        val multiBinding = ItemHomeVerticalSecondRecyclerBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup, false)
        return when(viewType) {
            0 -> SingleViewHolder(singleBinding)
            1 -> MultiViewHolder(multiBinding)
            else -> throw IllegalArgumentException("Invalid view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is SingleViewHolder -> holder.bind(AdapterItem)
            is MultiViewHolder -> holder.bind(AdapterItem)
        }
    }

    override fun getItemCount(): Int {
        return AdapterItem.getNameMembers().size
    }
}