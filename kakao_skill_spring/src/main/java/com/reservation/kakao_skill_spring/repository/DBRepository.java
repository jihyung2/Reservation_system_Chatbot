package com.reservation.kakao_skill_spring.repository;

import com.reservation.kakao_skill_spring.entity.Database;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DBRepository extends JpaRepository<Database, Long> {
    List<Database> findByNumber(String DBnumber);
}
