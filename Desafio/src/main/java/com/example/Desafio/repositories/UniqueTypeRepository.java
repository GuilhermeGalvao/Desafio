package com.example.Desafio.repositories;

import com.example.Desafio.entities.UniqueType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniqueTypeRepository extends JpaRepository<UniqueType, Long> {
    List<UniqueType> findByUserId(String user_id);
}
