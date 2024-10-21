package com.roh.dogdom.views.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.roh.dogdom.data.viewPager.ViewPagerAdapterTypeImage
import com.roh.dogdom.data.viewPager.ViewPagerAdapterTypeImages
import com.roh.dogdom.data.viewPager.ViewPagerAdapterTypeVideo
import com.google.firebase.storage.FirebaseStorage
import com.roh.dogdom.data.firebase.comment.CommentRepository
import com.roh.dogdom.data.firebase.comment.CommentRepositoryImpl
import com.roh.dogdom.data.firebase.post.PostRepository
import com.roh.dogdom.data.firebase.post.PostRepositoryImpl
import com.roh.dogdom.data.home.ItemType
import com.roh.dogdom.data.home.MainPost2
import com.roh.dogdom.databinding.ItemHomeVerticalSecondRecyclerBinding

class HomeAdapter(var AdapterItem: MainPost2)
    : RecyclerView.Adapter<RecyclerView.ViewHolder> () {

    private var listener : OnItemClickListener? = null
    private lateinit var viewPager: ViewPager2
    private val commentRepository: CommentRepository = CommentRepositoryImpl()

    interface OnItemClickListener {
        fun onItemClick (pos : Int)
    }
    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }

    override fun getItemViewType(position: Int): Int {
        return when (AdapterItem.getItemViewType()[position]) {
            ItemType.IMAGE -> 0
            ItemType.IMAGES -> 1
            ItemType.VIDEO ->2
            ItemType.TEXT -> 3
            else -> 1
        }
    }

    inner class ImageViewHolder(private val binding: ItemHomeVerticalSecondRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivProfile = binding.ivProfile
        val tvName = binding.tvName
        val tvLike = binding.tvLike
        val tvMessage = binding.tvMessage
        val tvShare = binding.tvShare
        val idViewPager2 = binding.idViewPager2

        val storageReference = FirebaseStorage.getInstance()

        fun bind(AdapterItem: MainPost2) {
            val pos = adapterPosition
            var pagerAdapter = ViewPagerAdapterTypeImage(AdapterItem, pos)
            Log.e("ImageViewHolder", "ImageViewHolder: $pos" )
            idViewPager2.adapter = pagerAdapter
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

    inner class ImagesViewHolder(private val binding: ItemHomeVerticalSecondRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivProfile = binding.ivProfile
        val tvName = binding.tvName
        val tvLike = binding.tvLike
        val tvMessage = binding.tvMessage
        val tvShare = binding.tvShare
        val idViewPager2 = binding.idViewPager2

        var pagerAdapter = ViewPagerAdapterTypeImages(AdapterItem)
        val dotsIndicator = binding.dotsIndicator

        fun bind(AdapterItem: MainPost2) {
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

    inner class VideoViewHolder(private val binding: ItemHomeVerticalSecondRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivProfile = binding.ivProfile
        val tvName = binding.tvName
        val tvLike = binding.tvLike
        val tvMessage = binding.tvMessage
        val tvShare = binding.tvShare
        val idViewPager2 = binding.idViewPager2

        var pagerAdapter = ViewPagerAdapterTypeVideo(AdapterItem)

        fun bind(AdapterItem: MainPost2) {
            Log.e("VideoViewHolder", "VideoViewHolder: " )


            val pos = adapterPosition
            idViewPager2.adapter = pagerAdapter

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
        val multiBinding  = ItemHomeVerticalSecondRecyclerBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup, false)
        val videoBinding  = ItemHomeVerticalSecondRecyclerBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup, false)

        return when(viewType) {
            0 -> ImageViewHolder(singleBinding)
            1 -> ImageViewHolder(singleBinding)
            2 -> ImageViewHolder(singleBinding)
            else -> throw IllegalArgumentException("Invalid view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ImageViewHolder -> holder.bind(AdapterItem)
            is ImagesViewHolder -> holder.bind(AdapterItem)
            is VideoViewHolder -> holder.bind(AdapterItem)
        }
    }

    override fun getItemCount(): Int {
        return AdapterItem.getNameMembers().size
    }
}