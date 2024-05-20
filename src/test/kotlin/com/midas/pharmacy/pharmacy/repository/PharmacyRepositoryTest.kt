package com.midas.pharmacy.pharmacy.repository

import com.midas.pharmacy.pharmacy.entity.Pharmacy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.ActiveProfiles

@DisplayName("리포지토리 테스트 - 약국")
@DataJpaTest
@ActiveProfiles("testdb")
class PharmacyRepositoryTest(@Autowired val pharmacyRepository: PharmacyRepository) {

    @DisplayName("약국 저장 기능")
    @Test
    fun savePharmacy() {
        //given
        val pharmacy = Pharmacy(pharmacyName = "독수리약국", pharmacyAddress = "경기도", latitude = 26.4, longitude = 39.5)
        //when
        val savedPharmacy = pharmacyRepository.save(pharmacy)
        //then
        assertThat(savedPharmacy).isNotNull
        assertThat(savedPharmacy.getId()).isNotNull()
        assertThat(savedPharmacy.getPharmacyName()).isEqualTo(pharmacy.getPharmacyName())
        assertThat(savedPharmacy.getPharmacyAddress()).isEqualTo(pharmacy.getPharmacyAddress())
        assertThat(savedPharmacy.getLatitude()).isEqualTo(pharmacy.getLatitude())
        assertThat(savedPharmacy.getLongitude()).isEqualTo(pharmacy.getLongitude())
        assertThat(savedPharmacy.getCreatedAt()).isNotNull()
        assertThat(savedPharmacy.getUpdatedAt()).isNotNull()
    }

    @DisplayName("약극 데이터 리스트를 저장한다.")
    @Test
    fun saveAllPharmacy() {
        //given
        val pharmacyList = listOf(
            Pharmacy(pharmacyName = "독수리약국1", pharmacyAddress = "경기도", latitude = 26.4, longitude = 39.5),
            Pharmacy(pharmacyName = "독수리약국2", pharmacyAddress = "경기도", latitude = 24.4, longitude = 29.5)
        )
        //when
        val savedPharmacyList = pharmacyRepository.saveAll(pharmacyList)
        //then
        assertThat(savedPharmacyList).hasSize(pharmacyList.size)
    }

    @DisplayName("약국 데이터를 조회한다.")
    @Test
    fun searchPharmacy() {
        //given
        val pharmacyList = listOf(
            Pharmacy(pharmacyName = "독수리약국1", pharmacyAddress = "경기도", latitude = 26.4, longitude = 39.5),
            Pharmacy(pharmacyName = "독수리약국2", pharmacyAddress = "경기도", latitude = 24.4, longitude = 29.5)
        )
        pharmacyRepository.saveAllAndFlush(pharmacyList)
        //when
        val page = pharmacyRepository.findAll(Pageable.ofSize(10))
        //then
        assertThat(page.number).isEqualTo(0)
        assertThat(page.totalPages).isEqualTo(1)
        assertThat(page.totalElements).isEqualTo(2)
    }

    @DisplayName("약국 데이터를 수정한다.")
    @Test
    fun updatePharmacy() {
        //given
        val pharmacy = pharmacyRepository.save(
            Pharmacy(
                pharmacyName = "독수리약국1",
                pharmacyAddress = "경기도",
                latitude = 26.4,
                longitude = 39.5
            )
        )
        //when
        pharmacy.update(pharmacyAddress = "경상도")
        pharmacyRepository.flush()
        //then
        val updatedPharmacy = pharmacyRepository.findByIdOrNull(pharmacy.getId()?:-1)
        assertThat(updatedPharmacy).isNotNull
        assertThat(updatedPharmacy?.getPharmacyAddress()).isEqualTo("경상도")
    }

    @DisplayName("약국 데이터를 삭제한다.")
    @Test
    fun deletePharmacy() {
        //given
        val pharmacyId = pharmacyRepository.save(
            Pharmacy(
                pharmacyName = "독수리약국1",
                pharmacyAddress = "경기도",
                latitude = 26.4,
                longitude = 39.5
            )
        ).getId()?:-1L
        //when
        pharmacyRepository.deleteById(pharmacyId)
        val deletedPharmacy = pharmacyRepository.findByIdOrNull(pharmacyId)
        //then
        assertThat(deletedPharmacy).isNull()
    }

}