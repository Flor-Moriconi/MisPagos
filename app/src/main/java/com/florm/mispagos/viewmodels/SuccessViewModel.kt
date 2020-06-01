package com.florm.mispagos.viewmodels

import androidx.lifecycle.ViewModel
import com.florm.mispagos.ProcessDataContainer

class SuccessViewModel : ViewModel() {

    fun cleanAllData() {
        ProcessDataContainer.processDataContainer.clearAllData()
    }

}