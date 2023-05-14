package com.example.arad_january

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roh.dogdom.data.home.MainPost
import com.roh.dogdom.databinding.ItemHomeViewpagerBinding


class ViewPagerAdapterTypeImages (var AdapterItem: MainPost) : RecyclerView.Adapter<ViewPagerAdapterTypeImages.ViewHolder>() {

    private var listener : OnItmeCLickListener? = null
    public lateinit var ViewPagerBinding : ItemHomeViewpagerBinding

    interface OnItmeCLickListener {
        fun onItemClick ()
    }
    fun setOnItemClickListener(listener : OnItmeCLickListener) {
        this.listener = listener
    }

    inner class ViewHolder(val binding: ItemHomeViewpagerBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {
        val ImageMember = binding.ivViewPager

        fun bind(item: MainPost, position: Int) {
            ImageMember.setImageResource(item.getImagesMembers()[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerAdapterTypeImages.ViewHolder {
        ViewPagerBinding = ItemHomeViewpagerBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(ViewPagerBinding, context = parent.context)
    }
//    =
//        ViewHolder(
//            ItemHomeViewpagerBinding.inflate(LayoutInflater.from(parent.context), parent, false),
//            parent.context
//        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(AdapterItem, position)
    }

    override fun getItemCount(): Int {
        return AdapterItem.getImagesMembers().size
    }
}