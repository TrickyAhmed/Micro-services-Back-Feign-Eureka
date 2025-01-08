package tn.micro.service.cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.micro.service.cloud.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
