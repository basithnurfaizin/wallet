package com.example.wallet.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "account")
public class Account extends AuditData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "cif_id")
    private UUID cifId;

    @Column(name = "account_number")
    private String accountNumber;

    private BigDecimal balance;

    @Column(name = "currency_id")
    private UUID currencyId;
}
