package com.midas.pharmacy.pharmacy.service

import com.midas.pharmacy.exception.CustomException
import com.midas.pharmacy.exception.ExceptionStatus
import com.midas.pharmacy.pharmacy.dto.PharmacyDto
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
    @Throws(CustomException::class)
    fun updatePharmacy(pharmacyId: Long, pharmacyAddress: String? = null): PharmacyDto {
        val pharmacy = pharmacyRepository.findById(pharmacyId)
            .orElseThrow { CustomException(ExceptionStatus.ACCESS_NOT_EXIST_ENTITY) }
        pharmacy.update(pharmacyAddress = pharmacyAddress)
        return PharmacyDto.from(pharmacy)
    }

}