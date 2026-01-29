package com.example.drones.infrastructure.persistence.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.drones.domain.model.Dron;
import com.example.drones.infrastructure.persistence.entity.DronEntity;
import com.example.drones.infrastructure.persistence.reposity.DronReposity;

@ExtendWith(MockitoExtension.class)
class DronRepositoryAdapterTest {

    @Mock
    private DronReposity jpaRepo;

    @InjectMocks
    private DronRepositoryAdapter adpter;

    // Test Save
    @Test
    void debe_guardar_dron(){
        Dron dronDomain  = new Dron();
        dronDomain.setId(1l);
        dronDomain.setX(5);

        DronEntity entity = new DronEntity();
        entity.setId(1L);
        
        when(jpaRepo.save(any(DronEntity.class))).thenReturn(entity);

        Dron resultado = adpter.save(dronDomain);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(jpaRepo,times(1)).save(any(DronEntity.class));
    }
    
    // Test FindById
    @Test
    void bucar_por_id_convertir_a_domain(){
        DronEntity entity = new DronEntity();
        entity.setId(1L);
        when(jpaRepo.findById(1L)).thenReturn(Optional.of(entity));

        Optional<Dron> resultado = adpter.findById(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
    }

    @Test
    void nulo_si_no_encuentra_por_x_and_y(){
        when(jpaRepo.findByXAndY(1, 1)).thenReturn(Optional.empty());

        Optional<Dron> resultado = adpter.findByXAndY(1, 1);

        assertTrue(resultado.isEmpty());
    }

    // Test Find All
    @Test
    void debe_devolvel_una_lista_dron(){
        DronEntity a = new DronEntity();
        List<DronEntity> lista = new ArrayList<>();
        lista.add(a);

        when(jpaRepo.findAll()).thenReturn(lista);

        List<Dron> resultado = adpter.findAll();

        assertEquals(Dron.class, resultado.get(0).getClass());
        assertEquals(1, resultado.size());
        verify(jpaRepo,times(1)).findAll();
    }

    @Test
    void debe_devolver_una_lista_vacia(){
        when(jpaRepo.findAll()).thenReturn(new ArrayList<>());

        List<Dron> resul = adpter.findAll();

        assertTrue(resul.isEmpty());
    }

    // Test Delete By Id
    @Test
    void debe_lanzar_delete(){
        Dron dron = new Dron();
        dron.setId(99L);

        adpter.delete(dron);

        verify(jpaRepo).deleteById(99L);
    }

    // Test Delete All
    @Test
    void delete_all(){
        adpter.deleteAll();

        verify(jpaRepo,times(1)).deleteAll();
    }
}
