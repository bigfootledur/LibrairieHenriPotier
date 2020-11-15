package com.groupe3.librairiedehenripotier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.groupe3.librairiedehenripotier.api.HenriPotierAPI
import com.groupe3.librairiedehenripotier.api.HenriPotierData
import com.groupe3.librairiedehenripotier.utils.OfferCalculator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main) {
            val listBooks = HenriPotierData.listBooks()

            val offers = HenriPotierData.commercialOffers(listBooks.map { book -> book.isbn })

            val bestOfferWithPrice = OfferCalculator.getBestOfferWithPrice(listBooks, offers)

            print(bestOfferWithPrice)
        }
    }
}