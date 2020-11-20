package com.groupe3.librairiedehenripotier.presenter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.groupe3.librairiedehenripotier.api.HenriPotierAPI
import com.groupe3.librairiedehenripotier.model.Book
import com.groupe3.librairiedehenripotier.view.View
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BookListPresenter (private var view: View, private val api: Class<HenriPotierAPI>): Presenter {

    // view et de HenriDataApi
    //

    override fun onResume() {
           //https://github.com/antoniolg/androidmvp/blob/master/appkotlin/src/main/java/antonioleiva/com/appkotlin/main/MainPresenter.kt
        // http://henri-potier.xebia.fr/books

    }

    override fun getBookList(): List<Book> {
/*
        val call: Call<List<Book>> = api.listBooks()

        var bookList:ArrayList<BookItem> = ArrayList()
        call.enqueue(object : Callback<List<BookItem>> {
            override fun onResponse(call: Call<List<BookItem>>, response: Response<List<BookItem>>) {
                println("======"+response.body())
                progressDoalog.hide()
                response.body()?.let { generateDataList(it) }
            }

            override fun onFailure(call: Call<List<BookItem>?>?, t: Throwable?) {
                Log.e("error", "")
                // Log error here since request failed
            }
        })
*/
       return ArrayList()
    }
}