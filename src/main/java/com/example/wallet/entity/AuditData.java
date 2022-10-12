package com.example.wallet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class AuditData {

    @Column(name = "createdDate", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "createdBy", nullable = false)
    private String createdBy;

    @Column(name = "lastUpdateDate", nullable = false)
    private LocalDateTime lastUpdateDate;

    @Column(name = "lastUpdateBy", nullable = false)
    private String lastUpdateBy;
}
