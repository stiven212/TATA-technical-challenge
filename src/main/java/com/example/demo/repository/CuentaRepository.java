package com.example.demo.repository;

import com.example.demo.entities.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    Cuenta findFirstByCuentaId(Long cuentaId);

    Cuenta findFirstByNumeroCuenta(String numeroCuenta);
}
