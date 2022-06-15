package com.hossein.parsehub.ui.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.hossein.parsehub.R
import com.hossein.parsehub.databinding.ItemHomePageBinding
import com.hossein.parsehub.models.ResponseItems
import com.hossein.utils.showInvisible
import javax.inject.Inject

class ItemsAdapter @Inject constructor() :
    RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    private lateinit var binding: ItemHomePageBinding

    private var itemsList = emptyList<ResponseItems.Doc>()

    private lateinit var context: Context


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemsAdapter.ViewHolder {
        context = parent.context
        binding =
            ItemHomePageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ItemsAdapter.ViewHolder, position: Int) {
        holder.bindItems(itemsList[position])
        //avoid from douplicate item
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int = itemsList.size
    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        fun bindItems(item: ResponseItems.Doc) {
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

        private fun eventManager(item: ResponseItems.Doc) {
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


    fun setData(data: List<ResponseItems.Doc>) {
        val itemsDiffUtil = itemsDiffUtils(itemsList, data)
        val diffUtils = DiffUtil.calculateDiff(itemsDiffUtil)
        itemsList = data
        diffUtils.dispatchUpdatesTo(this)
    }

    class itemsDiffUtils(
        private val oldItem: List<ResponseItems.Doc>,
        private val newItem: List<ResponseItems.Doc>
    ) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldItem.size
        }

        override fun getNewListSize(): Int {
            return newItem.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }

        override fun areContentsTheSame(
            oldItemPosition: Int,
            newItemPosition: Int
        ): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }


    }

}