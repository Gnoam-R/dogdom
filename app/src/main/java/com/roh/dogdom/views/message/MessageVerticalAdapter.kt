package com.roh.dogdom.views.message

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roh.dogdom.data.message.MessageHorizontalPost
import com.roh.dogdom.data.message.MessageVerticalPost
import com.roh.dogdom.databinding.ItemMessageVerticalRecyclerBinding

class MessageVerticalAdapter(var AdapterItem: MessageVerticalPost)
    : RecyclerView.Adapter<MessageVerticalAdapter.ViewHolder> () {

    private var listener : OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick (pos : Int)
    }
    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }

    inner class ViewHolder(private val binding: ItemMessageVerticalRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivPost = binding.ivMessageItem1

        val tvTitle = binding.tvMessageTitle
        val tvDate = binding.tvMessageDate
        val tvDescription = binding.tvMessageDescription

        fun bind(AdapterItem: MessageVerticalPost) {
            val pos = adapterPosition
            ivPost.setImageResource(AdapterItem.getImageMembers()[pos])

            tvTitle.text = AdapterItem.getTitleMembers()[pos]
            tvDate.text = AdapterItem.getDateMembers()[pos]
            tvDescription.text = AdapterItem.getDescriptionMembers()[pos]

            if(pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(pos)
                }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMessageVerticalRecyclerBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(AdapterItem)
    }

    override fun getItemCount(): Int {
        return AdapterItem.getTitleMembers().size
    }
}