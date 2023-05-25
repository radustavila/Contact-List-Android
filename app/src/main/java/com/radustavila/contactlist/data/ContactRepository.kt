package com.radustavila.contactlist.data

import com.radustavila.contactlist.model.Contact
import com.radustavila.contactlist.model.Post

interface ContactRepository {

    suspend fun getContactList(): List<Contact>

    suspend fun getContactPostList(): List<Post>

}