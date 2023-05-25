package com.radustavila.contactlist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.radustavila.contactlist.R
import com.radustavila.contactlist.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private val listViewModel: ContactViewModel by activityViewModels()

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { ContactAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter

        setupObservers()
    }

    private fun setupObservers() {
        listViewModel.contacts.observe(viewLifecycleOwner) {
            adapter.contactList = it
        }

        listViewModel.loading.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        listViewModel.exception.observe(viewLifecycleOwner) {
            it?.let {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.exception, it.message),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

}