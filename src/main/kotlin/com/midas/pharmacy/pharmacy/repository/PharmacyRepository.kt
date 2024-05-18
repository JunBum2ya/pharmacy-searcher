package com.midas.pharmacy.pharmacy.repository;

import com.midas.pharmacy.pharmacy.entity.Pharmacy
import org.springframework.data.jpa.repository.JpaRepository

interface PharmacyRepository : JpaRepository<Pharmacy, Long> {
}