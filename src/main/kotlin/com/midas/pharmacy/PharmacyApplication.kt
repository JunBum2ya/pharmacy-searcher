 package com.midas.pharmacy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PharmacyApplication

fun main(args: Array<String>) {
    runApplication<PharmacyApplication>(*args)
}
