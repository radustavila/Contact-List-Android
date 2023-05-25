package com.radustavila.contactlist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.radustavila.contactlist.R
import com.radustavila.contactlist.databinding.FragmentDetailBinding
import com.radustavila.contactlist.model.Contact
import com.radustavila.contactlist.util.URL_RANDOM_PICS

class DetailFragment : Fragment() {

    private val viewModel: ContactViewModel by activityViewModels()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { PostAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter

        setupObservers()

        arguments?.let { it ->
            @Suppress("DEPRECATION") val contact = it.getParcelable<Contact>("contact")!!

            viewModel.getPostList(contact.id)

            with(binding) {
                name.text = contact.name
                email.text = contact.email

                // TODO - would refactor this duplicated line of code (but no time)
                val id = contact.id.toLongOrNull()
                if (id != null && id % 2 == 0L) {
                    val contactInitials = contact.name.split(' ')
                    var short = ""
                    contactInitials.forEach { i ->
                        short += i[0]
                    }
                    initials.text = short
                    initials.visibility = View.VISIBLE

                    Glide.with(this@DetailFragment)
                        .load(R.drawable.grey_background)
                        .circleCrop()
                        .into(thumbnail)
                } else {
                    initials.visibility = View.INVISIBLE
                    Glide.with(this@DetailFragment)
                        .load(URL_RANDOM_PICS)
                        .error(R.drawable.grey_background)
                        .circleCrop()
                        .into(thumbnail)
                }
            }
        }
    }

    private fun setupObservers() {
        viewModel.posts.observe(viewLifecycleOwner) {
            adapter.postList = it
        }
    }
}