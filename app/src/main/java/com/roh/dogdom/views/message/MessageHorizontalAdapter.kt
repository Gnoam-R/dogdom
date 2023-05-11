package com.roh.dogdom.views.message

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roh.dogdom.data.message.MessageHorizontalPost
import com.roh.dogdom.databinding.ItemMessageHorizontalRecyclerBinding

class MessageHorizontalAdapter(var AdapterItem: MessageHorizontalPost)
    : RecyclerView.Adapter<MessageHorizontalAdapter.ViewHolder> () {

    private var listener : OnItemClickListener? = null
    interface OnItemClickListener {
        fun onItemClick (pos : Int)
    }
    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }

    inner class ViewHolder(private val binding: ItemMessageHorizontalRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivPost = binding.btRanking

        fun bind(AdapterItem: MessageHorizontalPost) {
            val pos = adapterPosition
            ivPost.setImageResource(AdapterItem.getImageMembers()[pos])

            if(pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(pos)
                }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMessageHorizontalRecyclerBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(AdapterItem)
    }

    override fun getItemCount(): Int {
        return AdapterItem.getImageMembers().size
    }
}