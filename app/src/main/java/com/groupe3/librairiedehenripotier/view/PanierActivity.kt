package com.groupe3.librairiedehenripotier.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.groupe3.librairiedehenripotier.R

class PanierActivity : AppCompatActivity() {

//    private val presenter = PanierPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panier)

        val openButton = findViewById<Button>(R.id.textButton)
        openButton.setOnClickListener {
            Toast.makeText(this@PanierActivity, R.string.text_button, Toast.LENGTH_SHORT).show()
        }

        // Mock
        val bookIds = listOf<String>("id_1", "id_2")
//        val (promotion, price) = presenter.getPrix(bookIds)
        val promotion = 2.0
        val price = 10.0

        val montantPrix = findViewById<TextView>(R.id.montant_prix)
        val montantReduction = findViewById<TextView>(R.id.montant_reduction)
        val livreAchetes = findViewById<TextView>(R.id.nombre_livres)
        livreAchetes.text = getString(R.string.nombre_livres, bookIds.size.toString())
        montantPrix.text = getString(R.string.montant_prix, price.toString())
        montantReduction.text = getString(R.string.montant_reduction, promotion.toString())
    }
}