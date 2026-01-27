package com.example.drones.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.drones.domain.model.Dron;
import com.example.drones.domain.port.DronRepository;
import com.example.drones.infrastructure.persistence.entity.DronEntity;
import com.example.drones.infrastructure.persistence.mapper.DronMapper;
import com.example.drones.infrastructure.persistence.reposity.DronReposity;

@Repository
public class DronRepositoryAdapter implements DronRepository {

    private final DronReposity jpaRepo;

    public DronRepositoryAdapter(DronReposity jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public Dron save(Dron dron) {
        DronEntity entity = DronMapper.toEntity(dron);
        DronEntity saved = jpaRepo.save(entity);
        return DronMapper.toDomain(saved);
    }

    @Override
    public Optional<Dron> findById(Long id) {
        return jpaRepo.findById(id)
                .map(DronMapper::toDomain);
    }

    @Override
    public List<Dron> findAll() {
        return jpaRepo.findAll()
                .stream()
                .map(DronMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Dron> findByXAndY(int x, int y) {
        return jpaRepo.findByXAndY(x, y)
                .map(DronMapper::toDomain);
    }

    @Override
    public void delete(Dron dron) {
        jpaRepo.deleteById(dron.getId());
    }

    @Override
    public void deleteAll() {
        jpaRepo.deleteAll();
    }
}
