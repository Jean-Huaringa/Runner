

package com.cibertec.runner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.runner.model.Transaccion;
import com.cibertec.runner.model.TransaccionId;

@Repository
public interface ITransacionRepository extends JpaRepository<Transaccion, TransaccionId>{

