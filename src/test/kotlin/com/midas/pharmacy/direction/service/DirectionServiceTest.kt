package com.midas.pharmacy.direction.service

import com.midas.pharmacy.api.dto.DocumentDto
import com.midas.pharmacy.direction.repository.DirectionRepository
import com.midas.pharmacy.pharmacy.dto.PharmacyDto
import com.midas.pharmacy.pharmacy.service.PharmacyService
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class DirectionServiceTest: BehaviorSpec({
    val pharmacyService = mockk<PharmacyService>()
    val directionRepository = mockk<DirectionRepository>()
    val directionService = DirectionService(pharmacyService, directionRepository)

    val inputLatitude = 37.5960650456809
    val inputLongitude = 127.037033003036

    Given("DocumentDto가 주어졌을 때") {
        val document = DocumentDto(placeName = "독수리 약국", addressName = "경기도", latitude = inputLatitude, longitude = inputLongitude, distance = 10.0)
        val pharmacy1 = PharmacyDto(id = 1L, pharmacyName = "독수리 약국", pharmacyAddress = "경기도", latitude = 37.61040424, longitude = 127.0569046)
        val pharmacy2 = PharmacyDto(id = 2L, pharmacyName = "사당 약국", pharmacyAddress = "경상도", latitude = 37.60894036, longitude = 127.029052)
        every { pharmacyService.findAllPharmacyList() }.returns(listOf(pharmacy1, pharmacy2))
        When("경로를 조회를 한다면") {
            val directions = directionService.buildDirectionList(document)
            Then("결과 값이 거리순으로 조회가 된다.") {
                directions.size shouldBe 2
                directions[0].targetPharmacyName shouldBe "사당 약국"
                directions[1].targetPharmacyName shouldBe "독수리 약국"
                verify { pharmacyService.findAllPharmacyList() }
            }
        }
    }

    Given("반경 10km에 있는 약국 1개와 밖에 있는 약국 1개가 주어졌을 때") {
        val document = DocumentDto(placeName = "독수리 약국", addressName = "경기도", latitude = inputLatitude, longitude = inputLongitude, distance = 10.0)
        val pharmacy1 = PharmacyDto(id = 1L, pharmacyName = "독수리 약국", pharmacyAddress = "경기도", latitude = 37.3825107393401, longitude = 127.236707811313)
        val pharmacy2 = PharmacyDto(id = 2L, pharmacyName = "성수 약국", pharmacyAddress = "경기도", latitude = 37.61040424, longitude = 127.0569046)
        every { pharmacyService.findAllPharmacyList() }.returns(listOf(pharmacy2, pharmacy1))
        When("경로를 조회를 한다면") {
            val directions = directionService.buildDirectionList(document)
            Then("약국 1개만 조회된다.") {
                directions.size shouldBe 1
                directions[0].targetPharmacyName shouldBe "성수 약국"
                verify { pharmacyService.findAllPharmacyList() }
            }
        }
    }
})