package com.example.arad_january

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roh.dogdom.data.home.MainPost
import com.roh.dogdom.databinding.ItemHomeVerticalSecondRecyclerBinding
import com.roh.dogdom.databinding.ItemHomeViewpagerBinding
import kotlinx.coroutines.withContext


class ViewPagerAdapter (var AdapterItem: MainPost) : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    private var listener : OnItmeCLickListener? = null

    interface OnItmeCLickListener {
        fun onItemClick ()
    }
    fun setOnItemClickListener(listener : OnItmeCLickListener) {
        this.listener = listener
    }

    class ViewHolder(val binding: ItemHomeVerticalSecondRecyclerBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {
//        val ImageMember = binding.

        fun bind(item: MainPost, position: Int) {
//            ImageMember.setImageResource(item.getImagesMembers()[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerAdapter.ViewHolder =
        ViewHolder(
            ItemHomeVerticalSecondRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            parent.context
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(AdapterItem, position)
    }

    override fun getItemCount(): Int {
        return AdapterItem.getImagesMembers().size
    }
}