package com.groupe3.librairiedehenripotier.api

import com.groupe3.librairiedehenripotier.model.Book
import com.groupe3.librairiedehenripotier.model.OffersListDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface HenriPotierAPI {
    @GET("books")
    suspend fun listBooks(): List<Book>

    @GET("books/{id}/commercialOffers")
    suspend fun commercialOffer(@Path("id") bookId: String): OffersListDTO
}