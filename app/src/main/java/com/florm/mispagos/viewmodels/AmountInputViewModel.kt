package com.florm.mispagos.viewmodels

import androidx.lifecycle.ViewModel
import com.florm.mispagos.ProcessDataContainer

class AmountInputViewModel : ViewModel() {

    fun saveAmountEntered(amount: String) {
        ProcessDataContainer.processDataContainer.amountEntered = amount
    }
}