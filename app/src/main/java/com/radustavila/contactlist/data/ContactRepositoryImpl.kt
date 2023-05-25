package com.radustavila.contactlist.data

import com.radustavila.contactlist.model.Post
import com.radustavila.contactlist.network.ContactAPI
import javax.inject.Inject

private const val INACTIVE_STATUS = "inactive"

class ContactRepositoryImpl @Inject constructor(
    private val contactAPI: ContactAPI
): ContactRepository {

    override suspend fun getContactList() = contactAPI.getContactList().filter {
        it.status != INACTIVE_STATUS
    }

    override suspend fun getContactPostList(): List<Post> {
        TODO("Not yet implemented")
    }

}