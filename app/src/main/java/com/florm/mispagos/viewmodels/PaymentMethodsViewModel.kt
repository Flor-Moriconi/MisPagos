package com.florm.mispagos.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.florm.mispagos.ProcessDataContainer
import com.florm.mispagos.models.PaymentMethod
import com.florm.mispagos.repositories.PaymentMethodsRepository

class PaymentMethodsViewModel :  ViewModel() {

    var repository = PaymentMethodsRepository()
    var paymentMethodsList = MutableLiveData<List<PaymentMethod>>()

    fun getPaymentMethods() {
        repository.getPaymentMethods(paymentMethodsList)
    }

    fun savePaymentMethodSelected(item: PaymentMethod) {
        ProcessDataContainer.processDataContainer.paymentMethodSelected = item
    }

    fun cleanPaymentMethodSelected() {
        ProcessDataContainer.processDataContainer.paymentMethodSelected = null
    }

    fun cleanData() {
        ProcessDataContainer.processDataContainer.amountEntered = ""
        ProcessDataContainer.processDataContainer.paymentMethodSelected = null
    }
}