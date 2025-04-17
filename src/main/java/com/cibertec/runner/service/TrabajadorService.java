package com.cibertec.runner.service;

import java.util.Map;

import com.cibertec.runner.model.Trabajador;

public interface TrabajadorService {

    /// Registrar
    Map<String, Object> registrar(Trabajador trabajador);

    /// Actualizar
    Map<String, Object> actualizar(Trabajador trabajador);

    /// Eliminador Lógico
    Map<String, Object> eliminarLogico(Integer id);

    /// Listados
    /////// Todos
    Map<String, Object> listarTodos();

    /////// Activos
    Map<String, Object> listarActivos();

    /////// Inactivos
    Map<String, Object> listarInactivos();

    /// Obtener por ID
    Map<String, Object> obtenerPorId(Integer id);
}
