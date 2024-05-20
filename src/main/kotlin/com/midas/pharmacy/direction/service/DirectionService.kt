package com.midas.pharmacy.direction.service

import com.midas.pharmacy.api.dto.DocumentDto
import com.midas.pharmacy.direction.dto.DirectionDto
import com.midas.pharmacy.direction.entity.Direction
import com.midas.pharmacy.direction.repository.DirectionRepository
import com.midas.pharmacy.pharmacy.service.PharmacyService
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

@Service
class DirectionService(
    private val pharmacyService: PharmacyService,
    private val directionRepository: DirectionRepository
) {

    private val MAX_SEARCH_COUNT = 3L // 약국 최대 검색 갯수
    private val RADIUS_KM = 10.0 // 반경 10 km
    private val DIRECTION_BASE_URL = "https://map.kakao.com/link/map/"

    fun buildDirectionList(documentDto: DocumentDto): List<DirectionDto> {
        return pharmacyService.findAllPharmacyList()
            .stream()
            .map {
                DirectionDto(
                    inputAddress = documentDto.addressName,
                    inputLatitude = documentDto.latitude,
                    inputLongitude = documentDto.longitude,
                    targetPharmacyName = it.pharmacyName,
                    targetAddress = it.pharmacyAddress,
                    targetLatitude = it.latitude,
                    targetLongitude = it.longitude,
                    distance = calculateDistance(documentDto.latitude, documentDto.longitude, it.latitude, it.longitude)
                )
            }
            .filter { it.distance <= RADIUS_KM }
            .sorted(Comparator.comparing { it.distance })
            .limit(MAX_SEARCH_COUNT)
            .collect(Collectors.toList())
    }

    // Haversine formula
    private fun calculateDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        var lat1 = lat1
        var lon1 = lon1
        var lat2 = lat2
        var lon2 = lon2
        lat1 = Math.toRadians(lat1)
        lon1 = Math.toRadians(lon1)
        lat2 = Math.toRadians(lat2)
        lon2 = Math.toRadians(lon2)

        val earthRadius = 6371.0 //Kilometers
        return earthRadius * acos(sin(lat1) * sin(lat2) + cos(lat1) * cos(lat2) * cos(lon1 - lon2))
    }

}