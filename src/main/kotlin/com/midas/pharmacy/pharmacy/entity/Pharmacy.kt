package com.midas.pharmacy.pharmacy.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "tb_pharmacy")
@Entity
class Pharmacy(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) private var id: Long? = null,
    private val pharmacyName: String,
    private var pharmacyAddress: String,
    private val latitude: Double,
    private val longitude: Double,
) {

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