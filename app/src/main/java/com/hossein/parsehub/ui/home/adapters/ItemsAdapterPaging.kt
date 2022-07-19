package com.hossein.parsehub.ui.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.hossein.parsehub.R
import com.hossein.parsehub.databinding.ItemHomePageBinding
import com.hossein.parsehub.models.ResponseItems
import com.hossein.parsehub.models.ResponseItems.Doc
import com.hossein.parsehub.paging.ItemsPagingSource
import com.hossein.utils.showInvisible
import javax.inject.Inject

class ItemsAdapterPaging @Inject constructor() :
    PagingDataAdapter<Doc, ItemsAdapterPaging.ViewHolder>(differCallBack) {

    private lateinit var binding: ItemHomePageBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemsAdapterPaging.ViewHolder {
        context = parent.context
        binding = ItemHomePageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ItemsAdapterPaging.ViewHolder, position: Int) {
        holder.setData(getItem(position)!!)
        holder.setIsRecyclable(false)
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun setData(item: Doc) {
            binding.apply {
                userName.text = item.author?.name.toString()
                userTitle.text = item.manuType.toString()
                textViewStateProduct.text = item.status.toString()
                textViewDiscription.text = item.text.toString()
                textViewCountLike.text = item.likesCount.toString()
                textViewCountComment.text = item.commentCount.toString()
                if (item.isPublished == true) {
                    imageViewChecked.showInvisible(true)
                } else {
                    imageViewChecked.showInvisible(false)
                }
                if (item.media?.size!! > 0) {
                    imageViewShowEmpty.showInvisible(false)
                    imageProduct.load(item.media.get(0)?.url.toString()) {
                        crossfade(true)
                        crossfade(600)
                    }
                } else {
                    imageViewShowEmpty.showInvisible(true)
                }
                eventManager(item)

            }
        }

        private fun eventManager(item: Doc) {
            binding.apply {
            onClick(imageProduct, "id: " + item.id)
            onClick(imageViewMenu, "Menu")
            onClick(imageViewLike, "likeCount: " + item.likesCount.toString())
            onClick(imageViewComment, "CommentCount: " + item.commentCount.toString())
            onClick(imageViewShare, "Share")
            onClick(userName, "User: " + item.author?.name.toString())
            onClick(textViewDiscription, "User: " + item.text.toString())
            onClick(textViewStateProduct, "State: " + item.status.toString())
            onClick(userTitle, "MenuType: " + item.manuType.toString())
            }
        }

        private fun showAlert(message: String) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("ParseHub")
            builder.setMessage(message)
            builder.setIcon(R.drawable.ic_baseline_wifi_protected_setup_24)
            builder.create().show()
        }

        private fun onClick(view: View, message: String) {
            view.setOnClickListener {
                showAlert(message);
            }
        }

    }

    companion object {
        val differCallBack = object : DiffUtil.ItemCallback<Doc>() {
            override fun areItemsTheSame(oldItem: Doc, newItem: Doc): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Doc, newItem: Doc): Boolean {
                return oldItem == newItem
            }

        }
    }

}