package com.florm.mispagos.repositories

import androidx.lifecycle.MutableLiveData
import com.florm.mispagos.ProcessDataContainer
import com.florm.mispagos.RetrofitService
import com.florm.mispagos.models.CardIssuer
import com.florm.mispagos.models.Installment
import com.florm.mispagos.models.PaymentMethod
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaymentMethodsRepository {

    val service = RetrofitService().service

    fun getPaymentMethods(list: MutableLiveData<List<PaymentMethod>>) {
        service.getPaymentMethods().enqueue(object : Callback<List<PaymentMethod>> {

            override fun onResponse(call: Call<List<PaymentMethod>>, response: Response<List<PaymentMethod>>) {
                val paymentMethodResponse = response.body()
                paymentMethodResponse?.let { response ->
                    list.postValue(response)
                }
            }

            override fun onFailure(call: Call<List<PaymentMethod>>, t: Throwable) {
                t.printStackTrace()
                list.postValue(null)
            }
        })
    }

    fun getCardIssuers(list: MutableLiveData<List<CardIssuer>>) {

        ProcessDataContainer.processDataContainer.paymentMethodSelected?.id?.let { pmID ->

            service.getCardIssuers(pmID).enqueue(object : Callback<List<CardIssuer>> {

                override fun onResponse(call: Call<List<CardIssuer>>, response: Response<List<CardIssuer>>) {
                    val cardIssuersResponse = response.body()
                    cardIssuersResponse?.let { response ->
                        list.postValue(response)
                    }
                }

                override fun onFailure(call: Call<List<CardIssuer>>, t: Throwable) {
                    t.printStackTrace()
                    list.postValue(null)
                }
            })
        }
    }

    fun getInstallments(list: MutableLiveData<List<Installment>>) {
        val amount = ProcessDataContainer.processDataContainer.amountEntered
        var issuerId = ""
        var paymentMethodId = ""

        ProcessDataContainer.processDataContainer.cardIssuerSelected?.id?.let { id -> issuerId = id}
        ProcessDataContainer.processDataContainer.paymentMethodSelected?.id?.let { pmID -> paymentMethodId = pmID }

        if(amount.isNotEmpty() && paymentMethodId.isNotEmpty() && issuerId.isNotEmpty()) {
            service.getInstallments(paymentMethodId, amount, issuerId).enqueue(object : Callback<List<Installment>> {
                override fun onResponse(call: Call<List<Installment>>, response: Response<List<Installment>>) {
                    val installmentsResponse = response.body()
                    installmentsResponse?.let { response ->
                        list.postValue(response)
                    }
                }

                override fun onFailure(call: Call<List<Installment>>, t: Throwable) {
                    t.printStackTrace()
                    list.postValue(null)
                }
            })
        }
    }

}