package com.florm.mispagos.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CardNumber(
    @SerializedName("validation")
    var validation: String? = null,
    @SerializedName("length")
    var length: Int? = null
) : Serializable