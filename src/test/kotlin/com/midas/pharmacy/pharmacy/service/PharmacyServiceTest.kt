package com.midas.pharmacy.pharmacy.service

import com.midas.pharmacy.exception.CustomException
import com.midas.pharmacy.exception.ExceptionStatus
import com.midas.pharmacy.pharmacy.entity.Pharmacy
import com.midas.pharmacy.pharmacy.repository.PharmacyRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.engine.test.logging.error
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import io.kotest.matchers.types.shouldBeSameInstanceAs
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.*

class PharmacyServiceTest : BehaviorSpec({
    val pharmacyRepository = mockk<PharmacyRepository>()
    val pharmacyService = PharmacyService(pharmacyRepository)

    Given("DB에 존재하는 ID와 주소가 주어졌을 때") {
        val id = 10L
        val newAddress = "경상도"
        val pharmacy = Pharmacy(
            id = id,
            pharmacyName = "독수리 약국",
            pharmacyAddress = "경기도",
            latitude = 30.4,
            longitude = 30.5
        )
        every { pharmacyRepository.findById(id) }.returns(Optional.of(pharmacy))
        When("약국을 수정하면") {
            val updatedPharmacy = pharmacyService.updatePharmacy(pharmacyId = id, pharmacyAddress = newAddress)
            Then("PharmacyDto가 반환된다.") {
                verify { pharmacyRepository.findById(id) }
                updatedPharmacy.pharmacyAddress shouldBe newAddress
            }
        }
    }
    Given("DB에 존재하지 않는 ID와 주소가 주어졌을 때") {
        val id = 999L
        val newAddress = "경상도"
        every { pharmacyRepository.findById(id) }.returns(Optional.empty())
        When("약국을 수정하면") {
            val exception = shouldThrow<CustomException> {
                pharmacyService.updatePharmacy(
                    pharmacyId = id,
                    pharmacyAddress = newAddress
                )
            }
            Then("예외가 발생한다.") {
                exception.code shouldBe ExceptionStatus.ACCESS_NOT_EXIST_ENTITY.code
                exception shouldHaveMessage ExceptionStatus.ACCESS_NOT_EXIST_ENTITY.message
            }
        }
    }
    Given("아무것도 주어지지 않았을 때") {
        every { pharmacyRepository.findAll() }.returns(
            listOf(
                Pharmacy(
                    id = 1,
                    pharmacyName = "독수리 약국",
                    pharmacyAddress = "경기도",
                    latitude = 30.4,
                    longitude = 30.5
                )
            )
        )
        When("전체 약국을 조회한다면") {
            val list = pharmacyService.findAllPharmacyList()
            Then("리스트가 반환된다.") {
                list shouldHaveSize 1
                verify { pharmacyRepository.findAll() }
            }
        }
    }
})