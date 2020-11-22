package com.groupe3.librairiedehenripotier.model

data class CommercialOffer(val type: OfferType, val value: Float, val sliceValue: Float?) {
    constructor(type: OfferType, value: Float) : this(type, value, null)
}