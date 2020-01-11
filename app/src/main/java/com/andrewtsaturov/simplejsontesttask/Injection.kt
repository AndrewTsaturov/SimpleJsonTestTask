package com.andrewtsaturov.simplejsontesttask

import com.andrewtsaturov.simplejsontesttask.presentation.navigation.LocalCiceroneHolder
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val app = module {
    single { LocalCiceroneHolder() }
    single {
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
            .client(OkHttpClient())
            .baseUrl("http://jsonplaceholder.typicode.com/")
            .build()
    }
}