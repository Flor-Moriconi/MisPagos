package com.florm.mispagos.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Settings(
    @SerializedName("card_number")
    var cardNumber: CardNumber? = null,
    @SerializedName("bin")
    var bin: Bin? = null,
    @SerializedName("security_code")
    var securityCode: SecurityCode? = null
) : Serializable