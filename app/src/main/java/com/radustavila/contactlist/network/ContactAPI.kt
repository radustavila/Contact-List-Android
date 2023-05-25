package com.radustavila.contactlist.network

import com.radustavila.contactlist.model.Contact
import com.radustavila.contactlist.model.Post
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://gorest.co.in/public/v2/"
interface ContactAPI {

    @GET("users")
    suspend fun getContactList(): List<Contact>

    @GET("users/{id}/posts")
    suspend fun getContactPostList(@Path("id") id: String): List<Post>

}