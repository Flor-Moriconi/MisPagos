package com.florm.mispagos.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SecurityCode (
    @SerializedName("length")
    var length: Int? = null,
    @SerializedName("card_location")
    var cardLocation: String? = null,
    @SerializedName("mode")
    var mode: String? = null
) : Serializable