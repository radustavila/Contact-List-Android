package com.radustavila.contactlist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radustavila.contactlist.data.ContactRepository
import com.radustavila.contactlist.model.Contact
import com.radustavila.contactlist.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val contactRepository: ContactRepository
) : ViewModel() {

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading
    private val _exception = MutableLiveData<Exception>().apply { value = null }
    val exception: LiveData<Exception> = _exception

    private val _contacts = MutableLiveData<List<Contact>>()
    val contacts: LiveData<List<Contact>> get() = _contacts

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> get() = _posts

    init {
        getContactList()
    }

    private fun getContactList() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.postValue(true)
            _exception.postValue(null)
            try {
                _contacts.postValue(contactRepository.getContactList())
                _loading.postValue(false)
            } catch (e: Exception) {
                _loading.postValue(false)
                _exception.postValue(e)
            }
        }
    }

    fun getPostList(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _posts.postValue(contactRepository.getContactPostList(id))
        }
    }
}