package com.example.drones.aplication.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.drones.application.usecase.SimulacionService;
import com.example.drones.domain.model.Dron;
import com.example.drones.domain.model.Matriz;
import com.example.drones.domain.model.ValorOrden;
import com.example.drones.domain.model.ValorOrientacion;
import com.example.drones.domain.port.DronRepository;
import com.example.drones.dto.DronRequest;
import com.example.drones.service.DronService;

@ExtendWith(MockitoExtension.class)
class SimulacionServiceTest {
    @Mock
    DronRepository repo;

    @Mock
    DronService ds;

    @InjectMocks
    SimulacionService service;

    // Test De FindById
    @Test
    void deberia_volver_dron_cuando_exista() {
        Dron dron = new Dron();
        dron.setId(1L);
        dron.setNombre("Dron A");
        when(repo.findById(1L)).thenReturn(Optional.of(dron));

        Dron resultado = service.findDronById(1L);

        assertEquals(1L, resultado.getId());
        assertEquals("Dron A", resultado.getNombre());

    }

    @Test
    void deberia_lanzar_404_cuando_dron_no_existe() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> service.findDronById(99L));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    // Test de FindAll
    @Test
    void deberia_volver_una_lista_de_drones() {
        Dron dron1 = new Dron();
        dron1.setNombre("Dron 1");
        Dron dron2 = new Dron();
        dron2.setNombre("Dron 2");

        when(repo.findAll()).thenReturn(List.of(dron1, dron2));

        List<Dron> resultado = service.findallDrones();

        assertEquals(2, resultado.size());
        assertEquals("Dron 1", resultado.get(0).getNombre());
        assertEquals("Dron 2", resultado.get(1).getNombre());
    }

    @Test
    void deberia_volver_lista_vacia() {
        when(repo.findAll()).thenReturn(List.of());

        List<Dron> list = service.findallDrones();

        assertNotNull(list);
        assertTrue(list.isEmpty());

    }

    // Test de Delete
    @Test
    void deberia_devolver_lista_vacia_cuando_se_borra_todo() {
        when(repo.findAll()).thenReturn(List.of());

        List<Dron> lista = service.deleteall();

        assertTrue(lista.isEmpty());
        verify(repo).deleteAll();

    }

    // Test de Delete By ID
    @Test
    void deberia_devolver_un_dron() {
        Dron dron = new Dron();
        dron.setId(1L);
        dron.setOrdenes(new ArrayList<>());
        when(repo.findById(1L)).thenReturn(Optional.of(dron));

        Dron resultado = service.deleteById(1L);

        assertNotNull(resultado);
        assertEquals(dron, resultado);
        verify(repo).delete(dron);
    }

    @Test
    void deberia_lanzar_404_cuando_se_intenta_borrar_dron_inesistente() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> service.deleteById(99L));

        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }

    // Test de Edit All
    @Test
    void deberia_devolver_dron_cambiado() {
        Dron dron = new Dron();
        dron.setId(1L);
        dron.setNombre("Dron A");
        dron.setModelo("Modelo A");
        dron.setOrdenes(new ArrayList<>());

        Dron nuevo = new Dron();
        nuevo.setNombre("Dron B");
        nuevo.setModelo("Modelo B");
        nuevo.setX(3);
        nuevo.setY(2);
        nuevo.setOrientacion(ValorOrientacion.N);
        nuevo.setOrdenes(new ArrayList<>());

        when(repo.findById(1L)).thenReturn(Optional.of(dron));
        when(repo.save(any(Dron.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Dron resultado = service.editall(1L, nuevo);

        assertEquals("Dron B", resultado.getNombre());
        assertEquals("Modelo B", resultado.getModelo());
        assertEquals(3, resultado.getX());
        assertEquals(2, resultado.getY());

        verify(repo).save(dron);

    }


    @Test
    void deberia_lanzar_404_cuando_se_intenta_editall_dron_inexistente() {
        Dron nuevo = new Dron();
        nuevo.setX(1);
        nuevo.setY(1);

        when(repo.findById(99L)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> service.editall(99L, nuevo));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    // Test de Edit By ID
    @Test
    void deberia_devolver_dron_cambando_posi() {
        Dron original = new Dron();
        original.setId(1L);
        original.setX(5);
        original.setY(5);

        Dron nuevo = new Dron();
        nuevo.setX(10);
        nuevo.setY(1);

        when(repo.findById(1L)).thenReturn(Optional.of(original));
        when(repo.save(any(Dron.class))).thenAnswer(a -> a.getArgument(0));

        Dron resultado = service.editbyId(1L, 10, 1);

        assertEquals(10, resultado.getX());
        assertEquals(1, resultado.getY());
    }

    @Test
    void deberia_devolver_dron_original_id() {
        Dron original = new Dron();
        original.setId(1L);
        original.setX(5);
        original.setY(5);

        when(repo.findById(1L)).thenReturn(Optional.of(original));
        when(repo.save(any(Dron.class))).thenAnswer(a -> a.getArgument(0));

        Dron resultado = service.editbyId(1L, 10, 1);

        assertEquals(1L, resultado.getId());
        assertEquals(10, resultado.getX());
        assertEquals(1, resultado.getY());
    }

    @Test
    void deberia_lanzar_404_cuando_se_intenta_editar_dron_inexistente() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> service.editbyId(99L, 10, 10));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    // Test de Find By Cord
    @Test
    void deberia_devolver_dron_buscado_cord() {
        Dron dron = new Dron();
        dron.setId(1L);
        dron.setX(5);
        dron.setY(3);
        when(repo.findByXAndY(5, 3)).thenReturn(Optional.of(dron));

        Dron resultado = service.findByCord(5, 3);

        assertEquals(1L, resultado.getId());
        assertEquals(5, resultado.getX());
        assertEquals(3, resultado.getY());
    }

    @Test
    void deberia_lanzar_404_cuando_no_existe_dron_en_coordenadas() {
        when(repo.findByXAndY(5, 1)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> service.findByCord(5, 1));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    // Test Ejecutar Simulacion
    @Test
    void deberia_devolver_una_lista_dronrequest() {
        Matriz matriz = new Matriz();
        matriz.setMaxX(10);
        matriz.setMaxY(10);

        Dron dron = new Dron();
        dron.setId(1L);
        dron.setOrientacion(ValorOrientacion.N);
        List<ValorOrden> listaOrdenes = new ArrayList<>();
        listaOrdenes.add(ValorOrden.MOVE_FORWARD);
        dron.setOrdenes(listaOrdenes);

        List<Dron> list = new ArrayList<>();
        list.add(dron);

        when(repo.save(any(Dron.class))).thenAnswer(i -> i.getArguments()[0]);

        List<DronRequest> resultado = service.ejecutarSimulacion(matriz, list);

        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getId());
        assertNull(dron.getId());
        assertEquals(0, resultado.get(0).getX());
        assertEquals(1, resultado.get(0).getY());

        verify(repo, times(1)).save(any(Dron.class));
    }

    @Test
    void deberia_devolver_una_lista_dronrequest_vacia() {
        Matriz matriz = new Matriz();
        matriz.setMaxX(10);
        matriz.setMaxY(10);

        Dron dron = new Dron();
        dron.setId(1L);
        dron.setX(15);
        dron.setY(15);
        dron.setOrientacion(ValorOrientacion.N);
        List<ValorOrden> listaOrdenes = new ArrayList<>();
        listaOrdenes.add(ValorOrden.MOVE_FORWARD);
        dron.setOrdenes(listaOrdenes);

        List<Dron> list = new ArrayList<>();
        list.add(dron);

        List<DronRequest> resultado = service.ejecutarSimulacion(matriz, list);

        assertEquals(0, resultado.size());

        verify(repo, times(0)).save(any(Dron.class));
    }
}
