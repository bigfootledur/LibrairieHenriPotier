package com.groupe3.librairiedehenripotier.api;

import com.groupe3.librairiedehenripotier.model.Book
import retrofit2.http.GET;

public interface HenriPotierAPI {
    @GET("books")
    suspend fun listBooks(): List<Book>
}