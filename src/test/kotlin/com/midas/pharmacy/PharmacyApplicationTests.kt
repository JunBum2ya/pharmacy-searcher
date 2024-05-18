package com.midas.pharmacy

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(properties = ["kakao.rest.api.key=your-test-api-key"])
@ActiveProfiles("testdb")
class PharmacyApplicationTests {

    @Test
    fun contextLoads() {
    }

}
