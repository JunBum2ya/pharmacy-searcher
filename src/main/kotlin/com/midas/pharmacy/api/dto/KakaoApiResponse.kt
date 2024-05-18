package com.midas.pharmacy.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class KakaoApiResponse(
    @field:JsonProperty("meta") val metaDto: MetaDto,
    @field:JsonProperty("documents") val documentList: List<DocumentDto>
)
