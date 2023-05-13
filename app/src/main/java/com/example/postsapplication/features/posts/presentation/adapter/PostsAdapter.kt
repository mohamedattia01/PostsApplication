package com.example.postsapplication.features.posts.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.postsapplication.core.constans.AppConstants.Companion.IMAGE_URL_SUFFIX
import com.example.postsapplication.core.custom_views.blur.RenderScriptBlur
import com.example.postsapplication.core.extensions.loadImage
import com.example.postsapplication.databinding.PostsItemLayoutBinding
import com.example.postsapplication.features.posts.data.remote.models.Result

class PostsAdapter(lifecycleOwner: LifecycleOwner) :
    PagingDataAdapter<Result, PostsAdapter.PostsViewHolder>(differCallback) {
    var onViewClickListener: OnViewClickListener
    private lateinit var binding: PostsItemLayoutBinding

    init {
        onViewClickListener = lifecycleOwner as OnViewClickListener
    }

    inner class PostsViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Result) {
            binding.apply {
                postCardView.setOnClickListener {
                    onViewClickListener.onClick(item)
                }
                postImageView.loadImage(
                    IMAGE_URL_SUFFIX.plus(item.poster_path),
                )
                postNameTv.text = item.original_title
                postTypeTv.text = item.overview
                postConstraintLayout.let {
                    blurView.setupWith(it).setBlurAlgorithm(RenderScriptBlur(itemView.context))
                        ?.setBlurRadius(10f)?.setBlurAutoUpdate(true)?.setBlurEnabled(true)
                        ?.setHasFixedTransformationMatrix(true)
                }
            }
        }
    }

    interface OnViewClickListener {
        fun onClick(item: Result)
    }

    companion object {
        val differCallback = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.onBind(getItem(position)!!)
        holder.setIsRecyclable(false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = PostsItemLayoutBinding.inflate(inflater, parent, false)
        return PostsViewHolder()
    }
}