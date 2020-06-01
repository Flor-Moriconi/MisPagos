package com.florm.mispagos.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Installment(
    @SerializedName("payment_method_id")
    var paymentMethodId: String? = null,
    @SerializedName("payment_type_id")
    var paymentTypeId: String? = null,
    @SerializedName("issuer")
    var issuer: Issuer? = null,
    @SerializedName("merchant_account_id")
    var merchantAccountId: Int? = null,
    @SerializedName("payer_costs")
    var payerCosts: List<PayerCost>? = null
) : Serializable