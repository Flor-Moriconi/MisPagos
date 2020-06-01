package com.florm.mispagos.viewmodels

import androidx.lifecycle.ViewModel
import com.florm.mispagos.ProcessDataContainer

class SummaryViewModel : ViewModel() {

    fun cleanPayerCostSelected() {
        ProcessDataContainer.processDataContainer.payerCostSelected = null
    }
}