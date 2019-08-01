package com.tsv.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsv.flightreservation.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	
}
