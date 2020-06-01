package com.florm.mispagos

import android.app.Application
import com.florm.mispagos.models.CardIssuer
import com.florm.mispagos.models.Installment
import com.florm.mispagos.models.PayerCost
import com.florm.mispagos.models.PaymentMethod

class ProcessDataContainer: Application() {

    var amountEntered = ""
    var paymentMethodSelected: PaymentMethod? = null
    var cardIssuerSelected: CardIssuer? = null
    var payerCostSelected: PayerCost? = null

    companion object {
        lateinit var processDataContainer: ProcessDataContainer
    }

    override fun onCreate() {
        super.onCreate()
        processDataContainer = this
    }

    fun clearAllData() {
        amountEntered = ""
        paymentMethodSelected = null
        cardIssuerSelected = null
        payerCostSelected = null
    }

    fun clearAmount(){
        amountEntered = ""
    }

    fun clearPaymentMethodSelected(){
        paymentMethodSelected = null
    }

    fun clearCardIssuerSelected(){
        cardIssuerSelected = null
    }

    fun clearInstallmentSelected(){
        payerCostSelected = null
    }
}