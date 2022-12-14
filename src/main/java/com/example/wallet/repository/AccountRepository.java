package com.example.wallet.repository;

import com.example.wallet.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    List<Account> findAllByCifId(UUID cifId);

    Optional<Account> findByAccountNumber(String accountNumber);
}
