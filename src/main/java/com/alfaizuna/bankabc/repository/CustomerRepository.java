package com.alfaizuna.bankabc.repository;

import com.alfaizuna.bankabc.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByNik(Long nik);

    Optional<Customer> findByAccountNumber(String accountNumber);
}
