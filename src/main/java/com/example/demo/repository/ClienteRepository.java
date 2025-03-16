package com.example.demo.repository;

import com.example.demo.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {


    Boolean findFirstByClienteId(String clienteId);
}
