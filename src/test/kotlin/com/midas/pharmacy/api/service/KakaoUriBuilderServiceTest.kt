package com.midas.pharmacy.api.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class KakaoUriBuilderServiceTest: BehaviorSpec({
    val kakaoUriBuilderService = KakaoUriBuilderService()

    Given("주소가 주어지면") {
        val address = "경기도 용인시"
        When("Uri를 생성하면") {
            val uri = kakaoUriBuilderService.buildUriByAddressSearch(address)
            Then("Uri를 반환한다.") {
                uri shouldNotBe null
                uri.query shouldBe "query=$address"
            }
        }
    }
})