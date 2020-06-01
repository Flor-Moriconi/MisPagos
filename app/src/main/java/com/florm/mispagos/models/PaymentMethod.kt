package com.florm.mispagos.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PaymentMethod(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("payment_type_id")
    var paymentTypeId: String? = null,
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("secure_thumbnail")
    var secureThumbnail: String? = null,
    @SerializedName("thumbnail")
    var thumbnail: String? = null,
    @SerializedName("deferred_capture")
    var deferredCapture: String? = null,
    @SerializedName("settings")
    var settings: List<Settings>? = null,
    @SerializedName("additional_info_needed")
    var additionalInfoNeeded: List<String>? = null,
    @SerializedName("min_allowed_amount")
    var minAllowedAmount: Double? = null,
    @SerializedName("max_allowed_amount")
    var maxAllowedAmount: Long? = null,
    @SerializedName("accreditation_time")
    var accreditationTime: Long? = null,
    @SerializedName("processing_modes")
    var processingModes: List<String>? = null
) : Serializable