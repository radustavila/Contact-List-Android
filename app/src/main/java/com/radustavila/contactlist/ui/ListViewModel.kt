package com.radustavila.contactlist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radustavila.contactlist.data.ContactRepository
import com.radustavila.contactlist.model.Contact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val contactRepository: ContactRepository
) : ViewModel() {

    private val _contacts = MutableLiveData<List<Contact>>()
    val contacts: LiveData<List<Contact>> get() = _contacts

    init {
        getContactList()
    }

    private fun getContactList() {
        viewModelScope.launch {
            _contacts.postValue(contactRepository.getContactList())
        }
    }

}