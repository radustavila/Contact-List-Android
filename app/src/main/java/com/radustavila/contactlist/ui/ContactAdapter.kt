package com.radustavila.contactlist.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.radustavila.contactlist.R
import com.radustavila.contactlist.databinding.ContactItemBinding
import com.radustavila.contactlist.model.Contact
import com.radustavila.contactlist.util.URL_RANDOM_PICS

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    var contactList = emptyList<Contact>()
    set(value) {
        field = value
        notifyItemRangeChanged(0, value.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        = ContactViewHolder(ContactItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), parent.context)

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contactList[position])
    }

    override fun getItemCount() = contactList.size

    inner class ContactViewHolder(private val binding: ContactItemBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {

        fun bind(contact: Contact) {
            with(binding) {
                this.contact = contact

                val id = contact.id.toLongOrNull()
                if (id != null && id % 2 == 0L) {
                    val contactInitials = contact.name.split(' ')
                    var short = ""
                    contactInitials.forEach {
                        short += it[0]
                    }
                    initials.text = short
                    initials.visibility = View.VISIBLE

                    Glide.with(context)
                        .load(R.drawable.grey_background)
                        .circleCrop()
                        .into(thumbnail)
                } else {
                    initials.visibility = View.INVISIBLE
                    Glide.with(context)
                        .load(URL_RANDOM_PICS)
                        .error(R.drawable.grey_background)
                        .circleCrop()
                        .into(thumbnail)
                }

                root.setOnClickListener {
                    binding.root.findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(contact))
                }
            }
        }
    }
}