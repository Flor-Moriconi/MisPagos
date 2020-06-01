package com.florm.mispagos.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.florm.mispagos.ProcessDataContainer
import com.florm.mispagos.models.CardIssuer
import com.florm.mispagos.repositories.PaymentMethodsRepository

class CardIssuerViewModel :  ViewModel() {

    var repository = PaymentMethodsRepository()
    var cardIssuersList = MutableLiveData<List<CardIssuer>>()

    fun getCardIssuers() {
        repository.getCardIssuers(cardIssuersList)
    }

    fun saveCardIssuerSelected(item: CardIssuer) {
        ProcessDataContainer.processDataContainer.cardIssuerSelected = item
    }

    fun cleanCardIssuerSelected() {
        ProcessDataContainer.processDataContainer.cardIssuerSelected = null
    }

    fun cleanDataSelected() {
        ProcessDataContainer.processDataContainer.paymentMethodSelected = null
        ProcessDataContainer.processDataContainer.cardIssuerSelected = null
    }

}