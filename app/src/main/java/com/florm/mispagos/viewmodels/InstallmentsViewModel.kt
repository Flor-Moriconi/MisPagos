package com.florm.mispagos.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.florm.mispagos.ProcessDataContainer
import com.florm.mispagos.models.Installment
import com.florm.mispagos.models.PayerCost
import com.florm.mispagos.repositories.PaymentMethodsRepository

class InstallmentsViewModel :  ViewModel() {

    var repository = PaymentMethodsRepository()
    var installmentList = MutableLiveData<List<Installment>>()

    fun getInstallments() {
        repository.getInstallments(installmentList)
    }

    fun savePayerCostSelected(item: PayerCost) {
        ProcessDataContainer.processDataContainer.payerCostSelected = item
    }

    fun cleanPayerCostSelected() {
        ProcessDataContainer.processDataContainer.payerCostSelected = null
    }

    fun cleanDataSelected() {
        ProcessDataContainer.processDataContainer.cardIssuerSelected = null
        ProcessDataContainer.processDataContainer.payerCostSelected = null
    }
}