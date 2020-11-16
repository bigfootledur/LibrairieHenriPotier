package com.groupe3.librairiedehenripotier.utils

import com.groupe3.librairiedehenripotier.model.Book
import com.groupe3.librairiedehenripotier.model.CommercialOffer
import com.groupe3.librairiedehenripotier.model.OfferType
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.math.floor

@RunWith(value = Parameterized::class)
class OfferCalculatorTest(
    private val books: List<Book>,
    private val offer: CommercialOffer,
    private val expected: Any
) {

    @Test
    fun `the price with offers is calculated correctly`() {
        Assert.assertEquals(expected, OfferCalculator.getPriceWithOffer(books, offer))
    }

    companion object {

        private val basicTestBooks = listOf(testBookWithPrice(10f), testBookWithPrice(10f))

        private val basicTestBooksBasePrice = basicTestBooks.map { book -> book.price }.sum()

        private val emptyTestBooks = listOf<Book>()

        @JvmStatic
        @Parameterized.Parameters(name = "{index}: offer {1} with books {0} = {2}")
        fun data(): Iterable<Array<Any>> {
            return arrayListOf(

                /* Percentage tests */
                arrayOf(emptyTestBooks, CommercialOffer(OfferType.PERCENTAGE, 10f), 0f),
                arrayOf(emptyTestBooks, CommercialOffer(OfferType.PERCENTAGE, 0f), 0f),
                arrayOf(emptyTestBooks, CommercialOffer(OfferType.PERCENTAGE, -10f), 0f),

                arrayOf(
                    basicTestBooks,
                    CommercialOffer(OfferType.PERCENTAGE, 10f),
                    basicTestBooksBasePrice - basicTestBooksBasePrice * 0.1f
                ),
                arrayOf(basicTestBooks, CommercialOffer(OfferType.PERCENTAGE, 110f), 0f),
                arrayOf(
                    basicTestBooks,
                    CommercialOffer(OfferType.PERCENTAGE, 0f),
                    basicTestBooksBasePrice
                ),
                arrayOf(
                    basicTestBooks,
                    CommercialOffer(OfferType.PERCENTAGE, -10f),
                    basicTestBooksBasePrice
                ),

                /* Minus tests */
                arrayOf(emptyTestBooks, CommercialOffer(OfferType.MINUS, 10f), 0f),
                arrayOf(emptyTestBooks, CommercialOffer(OfferType.MINUS, 0f), 0f),
                arrayOf(emptyTestBooks, CommercialOffer(OfferType.MINUS, -10f), 0f),

                arrayOf(
                    basicTestBooks,
                    CommercialOffer(OfferType.MINUS, 10f),
                    basicTestBooksBasePrice - 10
                ),
                arrayOf(basicTestBooks, CommercialOffer(OfferType.MINUS, 30f), 0f),
                arrayOf(
                    basicTestBooks,
                    CommercialOffer(OfferType.MINUS, 0f),
                    basicTestBooksBasePrice
                ),
                arrayOf(
                    basicTestBooks,
                    CommercialOffer(OfferType.MINUS, -10f),
                    basicTestBooksBasePrice
                ),

                /* Slice tests */
                arrayOf(emptyTestBooks, CommercialOffer(OfferType.SLICE, 2f, 10f), 0f),
                arrayOf(emptyTestBooks, CommercialOffer(OfferType.SLICE, -2f, 10f), 0f),
                arrayOf(emptyTestBooks, CommercialOffer(OfferType.SLICE, 2f, -10f), 0f),

                arrayOf(
                    basicTestBooks,
                    CommercialOffer(OfferType.SLICE, 2f, 10f),
                    basicTestBooksBasePrice - (floor(basicTestBooksBasePrice / 10f) * 2f)
                ),
                arrayOf(basicTestBooks, CommercialOffer(OfferType.SLICE, 20f, 10f), 0f),
                arrayOf(
                    basicTestBooks,
                    CommercialOffer(OfferType.SLICE, 2f, -10f),
                    basicTestBooksBasePrice
                ),
                arrayOf(
                    basicTestBooks,
                    CommercialOffer(OfferType.SLICE, -2f, -10f),
                    basicTestBooksBasePrice
                ),
                arrayOf(
                    basicTestBooks,
                    CommercialOffer(OfferType.SLICE, 2f, 0f),
                    basicTestBooksBasePrice
                ),
                arrayOf(
                    basicTestBooks,
                    CommercialOffer(OfferType.SLICE, 2f, null),
                    basicTestBooksBasePrice
                ),
                arrayOf(
                    basicTestBooks,
                    CommercialOffer(OfferType.SLICE, 0f, 10f),
                    basicTestBooksBasePrice
                ),
                arrayOf(
                    basicTestBooks,
                    CommercialOffer(OfferType.SLICE, -2f, 10f),
                    basicTestBooksBasePrice
                )
            ).toList()
        }

        private fun testBookWithPrice(price: Float): Book {
            return Book("test", "Test with price $price", price, "Test")
        }
    }

}