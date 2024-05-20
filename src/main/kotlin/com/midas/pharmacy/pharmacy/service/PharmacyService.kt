package com.midas.pharmacy.pharmacy.service

import com.midas.pharmacy.pharmacy.entity.Pharmacy
import com.midas.pharmacy.pharmacy.repository.PharmacyRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class PharmacyService(private val pharmacyRepository: PharmacyRepository) {

    /**
     * 약국 수정 메소드
     */
    fun updatePharmacy(pharmacyId: Long, pharmacyAddress: String? = null) {
        val pharmacy = pharmacyRepository.findByIdOrNull(pharmacyId)

    }

}