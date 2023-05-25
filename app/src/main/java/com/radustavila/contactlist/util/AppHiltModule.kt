package com.radustavila.contactlist.util

import com.radustavila.contactlist.data.ContactRepository
import com.radustavila.contactlist.data.ContactRepositoryImpl
import com.radustavila.contactlist.network.BASE_URL
import com.radustavila.contactlist.network.ContactAPI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): ContactAPI {
        val moshiBuilder = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshiBuilder))
            .baseUrl(BASE_URL)
            .build()

        return retrofitBuilder.create(ContactAPI::class.java)
    }

}

@Module
@InstallIn(ViewModelComponent::class)
interface RepoModule {

    @Binds
    fun provideRepository(contactRepositoryImpl: ContactRepositoryImpl): ContactRepository

}