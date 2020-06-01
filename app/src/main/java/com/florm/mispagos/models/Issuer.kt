package com.florm.mispagos.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Issuer (
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("secure_thumbnail")
    var secureThumbnail: String? = null,
    @SerializedName("thumbnail")
    var thumbnail: String? = null
) : Serializable