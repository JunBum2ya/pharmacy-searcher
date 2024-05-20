package com.midas.pharmacy.pharmacy.dto

import com.midas.pharmacy.pharmacy.entity.Pharmacy
import java.io.Serializable
import java.time.LocalDateTime

/**
 * DTO for {@link com.midas.pharmacy.pharmacy.entity.Pharmacy}
 */
data class PharmacyDto(
    val id: Long? = null,
    val pharmacyName: String,
    val pharmacyAddress: String,
    val latitude: Double,
    val longitude: Double,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?,
) {
    companion object {
        fun from(pharmacy: Pharmacy): PharmacyDto {
            return PharmacyDto(
                id = pharmacy.getId(),
                pharmacyName = pharmacy.getPharmacyName(),
                pharmacyAddress = pharmacy.getPharmacyAddress(),
                latitude = pharmacy.getLatitude(),
                longitude = pharmacy.getLongitude(),
                createdAt = pharmacy.getCreatedAt(),
                updatedAt = pharmacy.getUpdatedAt(),
            )
        }
    }

    fun toEntity(): Pharmacy {
        return Pharmacy(
            id = id,
            pharmacyName = pharmacyName,
            pharmacyAddress = pharmacyAddress,
            latitude = latitude,
            longitude = longitude
        )
    }
}