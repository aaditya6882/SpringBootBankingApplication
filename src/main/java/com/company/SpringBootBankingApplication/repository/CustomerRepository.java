package com.company.SpringBootBankingApplication.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface CustomerRepository extends JpaRepository<CustomerRepository, String>{
    Optional<CustomerRepository> findByUserName(String userName);
    boolean existsByUserName(String userName);
}
