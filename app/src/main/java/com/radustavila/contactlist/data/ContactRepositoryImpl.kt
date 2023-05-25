package com.radustavila.contactlist.data

import com.radustavila.contactlist.model.Contact
import com.radustavila.contactlist.model.Post

class ContactRepositoryImpl : ContactRepository {

    override suspend fun getContactList(): List<Contact> {
        TODO("Not yet implemented")
    }

    override suspend fun getContactPostList(): List<Post> {
        TODO("Not yet implemented")
    }
}