package com.midas.pharmacy.pharmacy.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Pharmacy(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private var id: Long? = null) {


}