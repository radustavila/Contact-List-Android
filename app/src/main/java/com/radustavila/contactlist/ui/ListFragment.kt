package com.radustavila.contactlist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.radustavila.contactlist.R
import com.radustavila.contactlist.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private val listViewModel: ListViewModel by activityViewModels()

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ContactAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        adapter = ContactAdapter {

        }

        binding.recyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listViewModel.contacts.observe(viewLifecycleOwner) {
            adapter.contactList = it
        }
    }

}