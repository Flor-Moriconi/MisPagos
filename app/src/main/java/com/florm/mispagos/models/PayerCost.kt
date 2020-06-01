package com.florm.mispagos.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PayerCost (
    @SerializedName("installments")
    var installments: Int? = null,
    @SerializedName("installment_rate")
    var installmentRate: Double? = null,
    @SerializedName("discount_rate")
    var discountRate: Int? = null,
    @SerializedName("reimbursement_rate")
    var reimbursementRate: Int? = null,
    @SerializedName("labels")
    var labels: List<String>? = null,
    @SerializedName("installment_rate_collector")
    var installmentRateCollector: List<String>? = null,
    @SerializedName("min_allowed_amount")
    var minAllowedAmount: Double? = null,
    @SerializedName("max_allowed_amount")
    var maxAllowedAmount: Long? = null,
    @SerializedName("recommended_message")
    var recommendedMessage: String? = null,
    @SerializedName("installment_amount")
    var installmentAmount: Double? = null,
    @SerializedName("total_amount")
    var totalAmount: Double? = null,
    @SerializedName("payment_method_option_id")
    var paymentMethodOptionId: String? = null
) : Serializable