package com.groupe3.librairiedehenripotier.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.groupe3.librairiedehenripotier.R
import com.groupe3.librairiedehenripotier.api.HenriPotierData
import com.groupe3.librairiedehenripotier.model.Book
import com.groupe3.librairiedehenripotier.presenter.PanierPresenter

class PanierActivity : AppCompatActivity(), PanierView {

    private val presenter = PanierPresenter(this, HenriPotierData)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panier)

        val openButton = findViewById<Button>(R.id.textButton)
        openButton.setOnClickListener { showPayerToast() }

        val bookIds = listOf<Book>(
            Book("c8fabf68-8374-48fe-a7ea-a00ccd07afff",
                "oij", 10.0F, "zef", listOf<String>("zef")))
        presenter.getReducedPriceAndPromotion(bookIds)

        setLivreAchetes(bookIds.size.toString())
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

    override fun setLivreAchetes(number: String) {
        val livreAchetes = findViewById<TextView>(R.id.nombre_livres)
        livreAchetes.text = getString(R.string.nombre_livres, number)
    }
}