package com.challenge.albo.repository;

import com.challenge.albo.model.CharacterDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterDO,Long> {
}
