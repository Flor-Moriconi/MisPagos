package com.florm.mispagos

import com.florm.mispagos.models.CardIssuer
import com.florm.mispagos.models.Installment
import com.florm.mispagos.models.PaymentMethod
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PaymentMethodsRepositoryApi {

    @GET("?public_key=$PUBLIC_KEY")
    fun getPaymentMethods(): Call<List<PaymentMethod>>

    @GET("card_issuers?public_key=$PUBLIC_KEY")
    fun getCardIssuers(@Query("payment_method_id") id: String): Call<List<CardIssuer>>

    @GET("installments?public_key=$PUBLIC_KEY")
    fun getInstallments(@Query("payment_method_id") paymentMethodId: String, @Query("amount") amount: String, @Query("issuer.id") issuerId: String): Call<List<Installment>>
}