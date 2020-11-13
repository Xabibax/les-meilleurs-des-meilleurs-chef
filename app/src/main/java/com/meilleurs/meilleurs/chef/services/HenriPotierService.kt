package com.meilleurs.meilleurs.chef.services

import com.meilleurs.meilleurs.chef.Book
import retrofit2.Call
import retrofit2.http.GET

interface HenriPotierService {
    @GET("/books")
    fun listBooks(): Call<List<Book>>
}
