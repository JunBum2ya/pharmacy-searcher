package com.midas.pharmacy.direction.repository;

import com.midas.pharmacy.direction.entity.Direction
import org.springframework.data.jpa.repository.JpaRepository

interface DirectionRepository : JpaRepository<Direction, Long> {
}