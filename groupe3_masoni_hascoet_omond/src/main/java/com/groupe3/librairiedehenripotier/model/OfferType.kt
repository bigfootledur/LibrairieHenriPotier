package com.groupe3.librairiedehenripotier.model

import com.google.gson.annotations.SerializedName

enum class OfferType() {
    @SerializedName("percentage")
    PERCENTAGE,

    @SerializedName("minus")
    MINUS,

    @SerializedName("slice")
    SLICE
}