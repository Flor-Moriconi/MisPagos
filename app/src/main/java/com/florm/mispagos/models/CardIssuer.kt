package com.florm.mispagos.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CardIssuer(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("secure_thumbnail")
    var secureThumbnail: String? = null,
    @SerializedName("thumbnail")
    var thumbnail: String? = null,
    @SerializedName("processing_mode")
    var processingMode: String? = null
) : Serializable