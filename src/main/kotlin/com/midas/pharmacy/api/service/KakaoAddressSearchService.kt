package com.midas.pharmacy.api.service

import com.midas.pharmacy.api.dto.KakaoApiResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange

@Service
class KakaoAddressSearchService(
    @Value("\${kakao.rest.api.key}") private val kakaoRestApiKey: String,
    private val kakaoUriBuilderService: KakaoUriBuilderService,
    private val restTemplate: RestTemplate
) {

    fun requestAddressSearch(address: String): KakaoApiResponse? {
        if (address.isEmpty()) return null

        val uri = kakaoUriBuilderService.buildUriByAddressSearch(address)

        val headers = HttpHeaders()
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK $kakaoRestApiKey")
        val httpEntity = HttpEntity<HttpHeaders>(headers)

        //카카오 rest api 호출
        return restTemplate.exchange<KakaoApiResponse>(uri, HttpMethod.GET, httpEntity).body
    }

}