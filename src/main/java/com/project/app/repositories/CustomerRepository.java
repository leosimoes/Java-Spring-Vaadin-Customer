package com.project.app.repositories;

import com.project.app.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {

    Optional<CustomerEntity> findById(UUID id);

    List<CustomerEntity> findByFirstName(String firstName);

    List<CustomerEntity> findByLastName(String lastName);

    List<CustomerEntity> findByFirstNameAndLastName(String firstName, String lastName);

}
