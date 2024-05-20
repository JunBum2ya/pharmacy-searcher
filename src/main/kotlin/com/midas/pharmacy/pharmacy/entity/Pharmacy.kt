package com.midas.pharmacy.pharmacy.entity

import com.midas.pharmacy.common.domain.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Pharmacy (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long? = null,
    private val pharmacyName: String,
    private var pharmacyAddress: String,
    private val latitude: Double,
    private val longitude: Double,
): BaseEntity() {

    fun getId(): Long? {
        return id
    }

    fun getPharmacyName(): String {
        return pharmacyName
    }

    fun getPharmacyAddress(): String {
        return pharmacyAddress
    }

    fun getLatitude(): Double {
        return latitude
    }

    fun getLongitude(): Double {
        return longitude
    }

    /**
     * 데이터 병경 메소드
     */
    fun update(pharmacyAddress: String? = null) {
        pharmacyAddress?.let { this.pharmacyAddress = it }
    }

}