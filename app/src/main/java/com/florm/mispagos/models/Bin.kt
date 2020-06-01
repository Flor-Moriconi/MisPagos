package com.florm.mispagos.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Bin(
    @SerializedName("pattern")
    var pattern: String? = null,
    @SerializedName("installments_pattern")
    var installmentsPattern: String? = null,
    @SerializedName("exclusion_pattern")
    var exclusionPattern: String? = null
) : Serializable