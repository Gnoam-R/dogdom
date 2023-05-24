package com.roh.dogdom.views.message.messages

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.arad_january.ViewPagerAdapterTypeImage
import com.example.arad_january.ViewPagerAdapterTypeImages
import com.example.arad_january.ViewPagerAdapterTypeVideo
import com.roh.dogdom.R
import com.roh.dogdom.data.chatgpt.ChatGptDataSource
import com.roh.dogdom.data.chatgpt.ChatGptInfo
import com.roh.dogdom.databinding.ItemMessageChatGptRecycler2Binding
import com.roh.dogdom.databinding.ItemMessageChatGptRecyclerBinding
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class MessageChatGptAdapter(private var context : Context, var AdapterItem: MutableList<ChatGptDataSource>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder> () {

    private var listener : OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick (pos : Int)
    }
    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }

    override fun getItemViewType(position: Int): Int {

        return AdapterItem[position].viewType

//        return when (AdapterItem.setData()) {
//            ItemType.IMAGE -> 0
//            ItemType.IMAGES -> 1
//            ItemType.VIDEO ->2
//            ItemType.TEXT -> 3
//            else -> 1
//        }
    }

    inner class ChatLeftViewHolder(private val binding: ItemMessageChatGptRecycler2Binding) : RecyclerView.ViewHolder(binding.root) {
        val ivProfile = binding.appCompatImageView3
        val tvName = binding.tvName
        val tvLike = binding.tvLike
        val tvMessage = binding.tvMessage
        val tvShare = binding.tvShare
        val idViewPager2 = binding.idViewPager2

        var pagerAdapter = ViewPagerAdapterTypeImage(AdapterItem)

        fun bind(AdapterItem: MainPost) {
            Log.e("ImageViewHolder", "ImageViewHolder: " )

            idViewPager2.adapter = pagerAdapter

            val pos = adapterPosition
//            ivPost.setImageResource(AdapterItem.getImageMembers()[pos])
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

    inner class ChatRightViewHolder(private val binding: ItemMessageChatGptRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivProfile = binding.ivProfile
        val tvName = binding.tvName
        val tvLike = binding.tvLike
        val tvMessage = binding.tvMessage
        val tvShare = binding.tvShare
        val idViewPager2 = binding.idViewPager2

        var pagerAdapter = ViewPagerAdapterTypeImages(AdapterItem)
        val dotsIndicator = binding.dotsIndicator

        fun bind(AdapterItem: MainPost) {
            Log.e("ImagesViewHolder", "ImagesViewHolder: " )
            val pos = adapterPosition
            idViewPager2.adapter = pagerAdapter
            dotsIndicator.attachTo(idViewPager2)

            ivProfile.setImageResource(AdapterItem.getProfileMembers()[pos])
            tvName.text = AdapterItem.getNameMembers()[pos]
            tvLike.text = AdapterItem.getLikeMembers()[pos]
            tvMessage.text = AdapterItem.getMessageMembers()[pos]
            tvShare.text = AdapterItem.getShareMembers()[pos]

//            idViewPager.adapter = ViewPagerAdapter(AdapterItem)

            if(pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(pos)
                }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val ChatLeftBinding =   ItemMessageChatGptRecycler2Binding.inflate(LayoutInflater.from(viewGroup.context),viewGroup, false)
        val ChatRightBinding  = ItemMessageChatGptRecyclerBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup, false)

        return when(viewType) {
            0 -> ChatLeftViewHolder(ChatLeftBinding)
            1 -> ChatRightViewHolder(ChatRightBinding)
            else -> throw RuntimeException("Invalid view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ChatLeftViewHolder -> holder.bind(AdapterItem)
            is ChatRightViewHolder -> holder.bind(AdapterItem)
        }
    }

    override fun getItemCount(): Int {
        return AdapterItem.getNameMembers().size
    }

//    fun addItem(check : Check) {
//        lst.add(check)
//    }

}