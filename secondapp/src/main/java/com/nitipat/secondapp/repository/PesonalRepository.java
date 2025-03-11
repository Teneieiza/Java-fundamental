package com.nitipat.secondapp.repository;

import com.nitipat.secondapp.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PesonalRepository extends JpaRepository<Personal, UUID> {

    List<Personal> findAll();

}
