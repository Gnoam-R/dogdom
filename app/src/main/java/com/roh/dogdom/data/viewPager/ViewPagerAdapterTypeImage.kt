package com.roh.dogdom.data.viewPager

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roh.dogdom.data.firebase.post.PostRepository
import com.roh.dogdom.data.firebase.post.PostRepositoryImpl
import com.roh.dogdom.data.home.MainPost2
import com.roh.dogdom.databinding.ItemHomeViewpagerBinding
import java.text.SimpleDateFormat


class ViewPagerAdapterTypeImage (var AdapterItem: MainPost2, var mainPosition : Int) : RecyclerView.Adapter<ViewPagerAdapterTypeImage.ViewHolder>() {

    private val postRepository: PostRepository = PostRepositoryImpl()

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
            Log.e("ViewPagerAdapterTypeImage", "test : ${position}")

            val currentTime = System.currentTimeMillis()
            val sdf = SimpleDateFormat("yyyy-MM-dd-hh-mm-ss")
            val date = sdf.format(currentTime)

            Log.e("ViewPagerAdapterTypeImage", "test : ${mainPosition}")
            var position1 = mainPosition + 1
            var position2 = position + 1
            postRepository.downloadImage(ImageMember, "file/img${position1}_$position2.jpeg")
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