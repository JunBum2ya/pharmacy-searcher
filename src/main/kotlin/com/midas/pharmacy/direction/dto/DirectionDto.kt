package com.midas.pharmacy.direction.dto

data class DirectionDto(
    val id: Long? = null,
    val distance: Double,
    val inputAddress: String? = null,
    val targetAddress: String? = null,
    val inputLatitude: Double,
    val inputLongitude: Double,
    val targetLatitude: Double,
    val targetLongitude: Double,
    val targetPharmacyName: String? = null
)
