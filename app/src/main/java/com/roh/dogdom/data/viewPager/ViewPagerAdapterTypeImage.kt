package com.roh.dogdom.data.viewPager

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roh.dogdom.data.firebase.FirebaseRepository
import com.roh.dogdom.data.firebase.FirebaseRepositoryImpl
import com.roh.dogdom.data.home.MainPost
import com.roh.dogdom.data.home.MainPost2
import com.roh.dogdom.databinding.ItemHomeViewpagerBinding
import com.roh.dogdom.views.home.HomeFragment


class ViewPagerAdapterTypeImage (var AdapterItem: MainPost2) : RecyclerView.Adapter<ViewPagerAdapterTypeImage.ViewHolder>() {


    private val repositoryFB: FirebaseRepository = FirebaseRepositoryImpl()
    private val HomeFragment = HomeFragment()

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

        fun bind(item: MainPost2, position: Int) {
            repositoryFB.downloadImage(ImageMember, "0604_file/0604_image" + position + ".jpeg")
//            ImageMember.setImageResource(item.getImageMembers()[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerAdapterTypeImage.ViewHolder {
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