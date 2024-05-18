package com.midas.pharmacy.api.service

import com.midas.pharmacy.api.dto.KakaoApiResponse
import com.midas.pharmacy.api.dto.MetaDto
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import java.net.URI

class KakaoAddressSearchServiceTest: BehaviorSpec({
    val kakaoApiRestKey = "1234"
    val kakaoUriBuilderService = mockk<KakaoUriBuilderService>()
    val restTemplate = mockk<RestTemplate>()
    val kakaoAddressSearchService = KakaoAddressSearchService(kakaoApiRestKey, kakaoUriBuilderService, restTemplate)

    Given("주소가 들어왔을 때") {
        val address = "경기도 양평군"
        val result = ResponseEntity.ok(KakaoApiResponse(metaDto = MetaDto(totalCount = 0), documentList = listOf()))
        every { kakaoUriBuilderService.buildUriByAddressSearch(any(String::class)) }.returns(URI.create("/kakao"))
        every { restTemplate.exchange<KakaoApiResponse>(any(URI::class), HttpMethod.GET, any(HttpEntity::class)) }
            .returns(result)
        When("kakao api에 주소를 조회를 하면") {
            val response = kakaoAddressSearchService.requestAddressSearch(address)
            Then("response가 반환된다.") {
                response shouldBe result.body
                verify { kakaoUriBuilderService.buildUriByAddressSearch(any(String::class)) }
                verify { restTemplate.exchange<KakaoApiResponse>(any(URI::class), HttpMethod.GET, any(HttpEntity::class)) }
            }
        }
    }
    Given("주소가 빈값이 들어왔을 때") {
        val address = ""
        When("kakao api에 주소를 조회를 하면") {
            val response = kakaoAddressSearchService.requestAddressSearch(address)
            Then("null이 반환된다.") {
                response shouldBe null
            }
        }
    }
})