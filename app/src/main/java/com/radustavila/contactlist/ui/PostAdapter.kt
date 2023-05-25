package com.radustavila.contactlist.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.radustavila.contactlist.R
import com.radustavila.contactlist.databinding.ContactItemBinding
import com.radustavila.contactlist.databinding.PostItemBinding
import com.radustavila.contactlist.model.Contact
import com.radustavila.contactlist.model.Post
import com.radustavila.contactlist.util.URL_RANDOM_PICS

class PostAdapter : RecyclerView.Adapter<PostAdapter.ContactViewHolder>() {

    var postList = emptyList<Post>()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        = ContactViewHolder(PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), parent.context)

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount() = postList.size

    inner class ContactViewHolder(private val binding: PostItemBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.post = post
        }
    }
}