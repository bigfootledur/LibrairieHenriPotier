package com.groupe3.librairiedehenripotier.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.groupe3.librairiedehenripotier.MainActivity
import com.groupe3.librairiedehenripotier.R
import com.groupe3.librairiedehenripotier.api.HenriPotierData
import com.groupe3.librairiedehenripotier.model.Book
import com.groupe3.librairiedehenripotier.presenter.PanierContent
import com.groupe3.librairiedehenripotier.presenter.PanierPresenter

class PanierActivity : AppCompatActivity(), PanierView {

    private val presenter = PanierPresenter(this, HenriPotierData)
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panier)
        setNavigationListener()

        val booksWithQuantity = PanierContent.getBooksWithQuantity()
        recyclerView = findViewById(R.id.recycler_panier_list_books)
        recyclerView.adapter = PanierBooksAdapter(this, booksWithQuantity)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val openButton = findViewById<Button>(R.id.textButton)
        openButton.setOnClickListener { showPayerToast() }

        showPanier()
    }

    override fun showPayerToast() {
        Toast.makeText(this@PanierActivity, R.string.text_button, Toast.LENGTH_SHORT).show()
    }

    override fun setMontantPrix(price: Float) {
        val montantPrix = findViewById<TextView>(R.id.montant_prix)
        montantPrix.text = getString(R.string.montant_prix, price.toString())
    }

    override fun setMontantPromotion(promotion: Float) {
        val montantReduction = findViewById<TextView>(R.id.montant_reduction)
        montantReduction.text = getString(R.string.montant_reduction, promotion.toString())
    }

    override fun removeBook(book: Book) {
        PanierContent.removeBook(book)
        showPanier()
        showBadge()
    }

    private fun showPanier() {
        val books = PanierContent.getBooks()
        presenter.getPriceAndPromotion(books)
    }

    private fun showBadge() {
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val badge = bottomNavigation.getOrCreateBadge(R.id.bottom_navigation_menu2)
        badge.number = PanierContent.getBooks().size
        badge.isVisible = PanierContent.getBooks().isNotEmpty()
    }

    private fun setNavigationListener() {
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.selectedItemId = R.id.bottom_navigation_menu2
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.bottom_navigation_menu1 -> {
                    val panierIntent = Intent(this, MainActivity::class.java)
                    startActivity(panierIntent)
                }
            }
            true
        }

        showBadge()
    }
}