package com.midas.pharmacy.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class DocumentDto(
    @field:JsonProperty("place_name") val placeName: String,
    @field:JsonProperty("address_name") val addressName: String,
    @field:JsonProperty("y") val latitude: Double,
    @field:JsonProperty("x") val longitude: Double,
    @field:JsonProperty("distance") val distance: Double
)
