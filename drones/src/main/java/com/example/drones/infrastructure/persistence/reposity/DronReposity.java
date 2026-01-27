package com.example.drones.infrastructure.persistence.reposity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.drones.infrastructure.persistence.entity.DronEntity;

public interface DronReposity extends JpaRepository<DronEntity,Long> {
    Optional<DronEntity> findByXAndY(int x, int y);
}
