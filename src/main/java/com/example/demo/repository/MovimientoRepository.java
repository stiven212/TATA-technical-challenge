package com.example.demo.repository;

import com.example.demo.entities.Cuenta;
import com.example.demo.entities.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    Movimiento findFirstByMovimientoId(Long movimientoId);

    Double findFirstByCuenta(Cuenta cuenta);
}
