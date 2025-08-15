package com.company.springbootbankingapplication.repository;
import com.company.springbootbankingapplication.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface CustomerRepository extends JpaRepository<Customer, String>{
    Optional<Customer> findByUserName(String userName);
    Optional<Customer> findByAccountNumber(String accountNumber);
    boolean existsByUserName(String userName);
}
