package com.example.drones.domain.port;

import java.util.List;
import java.util.Optional;
import com.example.drones.domain.model.Dron;

public interface DronRepository {
    Dron save(Dron dron);

    Optional<Dron> findById(Long id);

    List<Dron> findAll();

    Optional<Dron> findByXAndY(int x, int y);

    void delete(Dron dron);

    void deleteAll();
}
