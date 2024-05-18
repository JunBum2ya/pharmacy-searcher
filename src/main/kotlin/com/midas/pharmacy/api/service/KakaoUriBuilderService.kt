package com.midas.pharmacy.api.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

@Service
class KakaoUriBuilderService {

    private val logger: Logger = LoggerFactory.getLogger(KakaoUriBuilderService::class.java)

    private val KAKAO_LOCAL_SEARCH_ADDRESS_URL: String = "https://dapi.kakao.com/v2/local/search/address.json"

    private val KAKAO_LOCAL_CATEGORY_SEARCH_URL: String = "https://dapi.kakao.com/v2/local/search/category.json"

    fun buildUriByAddressSearch(address: String): URI {
        val uriBuilder = UriComponentsBuilder.fromHttpUrl(KAKAO_LOCAL_SEARCH_ADDRESS_URL)
        uriBuilder.queryParam("query", address)

        val uri = uriBuilder.build().encode().toUri() //encode default utf-8
        logger.info("[KakaoAddressSearchService buildUriByAddressSearch] address: {}, uri: {}", address, uri)

        return uri;
    }

}