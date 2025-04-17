package com.cibertec.runner.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cibertec.runner.model.Trabajador;
import com.cibertec.runner.service.TrabajadorService;

@RestController
@RequestMapping("/trabajador")
@CrossOrigin(origins = "*")
public class TrabajadorController {

    @Autowired
    private TrabajadorService trabajadorService;

    // Listados
    @GetMapping
    public Map<String, Object> listarTodos() {
        return trabajadorService.listarTodos();
    }

    @GetMapping("/activos")
    public Map<String, Object> listarActivos() {
        return trabajadorService.listarActivos();
    }

    @GetMapping("/inactivos")
    public Map<String, Object> listarInactivos() {
        return trabajadorService.listarInactivos();
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public Map<String, Object> obtenerPorId(@PathVariable Integer id) {
        return trabajadorService.obtenerPorId(id);
    }

    // Registrar
    @PostMapping
    public Map<String, Object> registrar(@RequestBody Trabajador trabajador) {
        return trabajadorService.registrar(trabajador);
    }

    // Actualizar
    @PutMapping
    public Map<String, Object> actualizar(@RequestBody Trabajador trabajador) {
        return trabajadorService.actualizar(trabajador);
    }

    // Eliminador lógico
    @DeleteMapping("/{id}")
    public Map<String, Object> eliminarLogico(@PathVariable Integer id) {
        return trabajadorService.eliminarLogico(id);
    }
}
