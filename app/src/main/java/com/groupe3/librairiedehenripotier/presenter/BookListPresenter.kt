package com.groupe3.librairiedehenripotier.presenter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.groupe3.librairiedehenripotier.api.HenriPotierAPI
import com.groupe3.librairiedehenripotier.api.HenriPotierData
import com.groupe3.librairiedehenripotier.model.Book
import com.groupe3.librairiedehenripotier.view.View
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BookListPresenter (private var view: View, private val api: HenriPotierData): Presenter {

     fun onResume() {
           //https://github.com/antoniolg/androidmvp/blob/master/appkotlin/src/main/java/antonioleiva/com/appkotlin/main/MainPresenter.kt
        // http://henri-potier.xebia.fr/books

    }

    override suspend fun getBookList(): List<Book> {

       //return ArrayList()
        println("----presenter")
        //val books =  api.listBooks()
        //this.view.setDataRecyclerView(books)
        return ArrayList();
    }
}