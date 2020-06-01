package com.florm.mispagos.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.florm.mispagos.ProcessDataContainer
import com.florm.mispagos.R
import com.florm.mispagos.viewmodels.AmountInputViewModel
import com.florm.mispagos.viewmodels.SummaryViewModel

class SummaryActivity : AppCompatActivity() {

    private var viewModel: SummaryViewModel? = null

    private var continueButton: Button? = null
    private var backButton: ImageButton? = null
    private var amountEnteredTextView: TextView? = null
    private var paymentMethodTextView: TextView? = null
    private var cardIssuerTextView: TextView? = null
    private var installmentTextView: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        bindViewElements()
        createViewModel()
        addListeners()
        loadData()
    }

    private fun bindViewElements() {
        continueButton = findViewById(R.id.btn_continue)
        backButton = findViewById(R.id.btn_back_arrow)
        amountEnteredTextView = findViewById(R.id.tv_amount_entered)
        paymentMethodTextView = findViewById(R.id.tv_payment_method_selected)
        cardIssuerTextView = findViewById(R.id.tv_card_issuer_selected)
        installmentTextView = findViewById(R.id.tv_payer_cost_selected)
    }

    private fun createViewModel() {
        viewModel = ViewModelProvider(this).get(SummaryViewModel::class.java)
    }

    private fun addListeners() {
        continueButton?.setOnClickListener {
            goToSuccessActivity()
        }

        backButton?.setOnClickListener {
            goBack()
        }
    }

    private fun loadData() {
        amountEnteredTextView?.text = ProcessDataContainer.processDataContainer.amountEntered
        paymentMethodTextView?.text = ProcessDataContainer.processDataContainer.paymentMethodSelected?.name
        cardIssuerTextView?.text = ProcessDataContainer.processDataContainer.cardIssuerSelected?.name
        installmentTextView?.text = ProcessDataContainer.processDataContainer.payerCostSelected?.recommendedMessage
    }

    private fun goToSuccessActivity() {
        val intent = Intent(this, SuccessActivity::class.java)
        startActivity(intent)
    }

    private fun goBack() {
        viewModel?.cleanPayerCostSelected()
        finish()
    }

}