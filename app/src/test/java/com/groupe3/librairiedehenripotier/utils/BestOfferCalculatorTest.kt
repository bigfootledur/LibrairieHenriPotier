package com.groupe3.librairiedehenripotier.utils

import com.groupe3.librairiedehenripotier.model.Book
import com.groupe3.librairiedehenripotier.model.CommercialOffer
import com.groupe3.librairiedehenripotier.model.OfferType
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class BestOfferCalculatorTest {

    @Before
    fun before() {
        mockkObject(OfferCalculator)
    }

    @Test
    fun `getBestOfferWithPrice calls getPriceWithOffer for each offer`() {
        OfferCalculator.getBestOfferWithPrice(basicTestBooks, offersList)

        verify(exactly = offersList.size) {
            OfferCalculator.getPriceWithOffer(
                basicTestBooks,
                allAny()
            )
        }
        verify(exactly = 1) { OfferCalculator.getPriceWithOffer(basicTestBooks, percentageOffer) }
        verify(exactly = 1) { OfferCalculator.getPriceWithOffer(basicTestBooks, minusOffer) }
        verify(exactly = 1) { OfferCalculator.getPriceWithOffer(basicTestBooks, sliceOffer) }
    }

    @Test
    fun `getBestOfferWithPrice returns the best offer with its price`() {

        every {
            OfferCalculator.getPriceWithOffer(
                basicTestBooks,
                allAny()
            )
        } returns basicTestBooksBasePrice - 5
        every {
            OfferCalculator.getPriceWithOffer(
                basicTestBooks,
                percentageOffer
            )
        } returns basicTestBooksBasePrice - 10

        val result = OfferCalculator.getBestOfferWithPrice(basicTestBooks, offersList)

        Assert.assertEquals(percentageOffer, result.first)
        Assert.assertEquals(basicTestBooksBasePrice - 10, result.second)
    }

    @Test
    fun `getBestOfferWithPrice returns null offer and base price if no offer is better than base price`() {

        every {
            OfferCalculator.getPriceWithOffer(
                basicTestBooks,
                allAny()
            )
        } returns basicTestBooksBasePrice + 5

        val result = OfferCalculator.getBestOfferWithPrice(basicTestBooks, offersList)

        Assert.assertEquals(null, result.first)
        Assert.assertEquals(basicTestBooksBasePrice, result.second)
    }

    @Test
    fun `getBestOfferWithPrice returns null offer and base price if best offer is equivalent to base price`() {

        every {
            OfferCalculator.getPriceWithOffer(
                basicTestBooks,
                allAny()
            )
        } returns basicTestBooksBasePrice

        val result = OfferCalculator.getBestOfferWithPrice(basicTestBooks, offersList)

        Assert.assertEquals(null, result.first)
        Assert.assertEquals(basicTestBooksBasePrice, result.second)
    }

    @Test
    fun `getBestOfferWithPrice returns null offer and base price if offers list is empty`() {

        val result = OfferCalculator.getBestOfferWithPrice(basicTestBooks, emptyOffersList)

        verify(exactly = 0) { OfferCalculator.getPriceWithOffer(allAny(), allAny()) }

        Assert.assertEquals(null, result.first)
        Assert.assertEquals(basicTestBooksBasePrice, result.second)
    }

    @Test
    fun `getBestOfferWithPrice returns null offer and 0 if books list is empty`() {
        val result = OfferCalculator.getBestOfferWithPrice(emptyTestBooks, offersList)

        Assert.assertEquals(null, result.first)
        Assert.assertEquals(0f, result.second)
    }


    companion object {
        private val basicTestBooks = listOf(
            testBookWithPrice(10f),
            testBookWithPrice(10f)
        )

        private val basicTestBooksBasePrice = basicTestBooks.map { book -> book.price }.sum()

        private val emptyTestBooks = listOf<Book>()

        private val percentageOffer = CommercialOffer(OfferType.PERCENTAGE, 10f)
        private val minusOffer = CommercialOffer(OfferType.MINUS, 10f)
        private val sliceOffer = CommercialOffer(OfferType.SLICE, 2f, 10f)

        private val offersList = listOf(percentageOffer, minusOffer, sliceOffer)

        private val emptyOffersList = listOf<CommercialOffer>()

        private fun testBookWithPrice(price: Float): Book {
            return Book("test", "Test with price $price", price, "Test")
        }
    }
}