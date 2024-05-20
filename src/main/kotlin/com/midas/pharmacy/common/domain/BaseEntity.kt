package com.midas.pharmacy.common.domain

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {
    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private var createdAt: LocalDateTime? = null
    @Column(name = "modified_date", nullable = false)
    @LastModifiedDate
    private var updatedAt: LocalDateTime? = null

    fun getCreatedAt(): LocalDateTime? {
        return this.createdAt
    }

    fun getUpdatedAt(): LocalDateTime? {
        return this.updatedAt
    }
}