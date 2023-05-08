package com.roh.dogdom.views.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roh.dogdom.data.main.MainPost
import com.roh.dogdom.databinding.ItemMasterMainVerticalSecondRecyclerBinding

class HomeAdapter(var AdapterItem: MainPost)
    : RecyclerView.Adapter<HomeAdapter.ViewHolder> () {

    private var listener : OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick (pos : Int)
    }
    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }

    inner class ViewHolder(private val binding: ItemMasterMainVerticalSecondRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
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

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMasterMainVerticalSecondRecyclerBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(AdapterItem)
    }

    override fun getItemCount(): Int {
        return AdapterItem.getNameMembers().size
    }
}