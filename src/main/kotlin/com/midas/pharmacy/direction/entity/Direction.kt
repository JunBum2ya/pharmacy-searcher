package com.midas.pharmacy.direction.entity

import com.midas.pharmacy.common.domain.BaseEntity
import com.midas.pharmacy.pharmacy.entity.Pharmacy
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.Instant

@Entity
@Table(name = "direction")
class Direction(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long? = null,
    @Column(nullable = false) private val distance: Double,
    @Column(length = 255) private val inputAddress: String? = null,
    @Column(length = 255) private val targetAddress: String? = null,
    @Column(nullable = false) private val inputLatitude: Double,
    @Column(nullable = false) private val inputLongitude: Double,
    @Column(nullable = false) private val targetLatitude: Double,
    @Column(nullable = false) private val targetLongitude: Double,
    @Column(length = 255) private val targetPharmacyName: String? = null
) : BaseEntity() {

    fun getId(): Long? {
        return id
    }

    fun getDistance(): Double {
        return distance
    }

    fun getInputAddress(): String? {
        return inputAddress
    }

    fun getInputLatitude(): Double {
        return inputLatitude
    }

    fun getInputLongitude(): Double {
        return inputLongitude
    }

    fun getTargetAddress(): String? {
        return targetAddress
    }

    fun getTargetLatitude(): Double {
        return targetLatitude
    }

    fun getTargetLongitude(): Double {
        return targetLongitude
    }

    fun getTargetPharmacyName(): String? {
        return targetPharmacyName
    }
}