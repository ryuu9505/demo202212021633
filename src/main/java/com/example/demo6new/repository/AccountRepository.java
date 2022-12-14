package com.example.demo6new.repository;

import com.example.demo6new.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByEmail (String email);
    boolean existsByUsername (String username);
    Account findByEmail(String email);
    Account findByUsername(String username);

    @Query()
    void deleteAllAccount();

}
