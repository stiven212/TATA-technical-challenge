package com.example.demo.service;

import com.example.demo.entities.Cliente;
import com.example.demo.entities.Cuenta;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;


    public List<Cuenta> obtenerCuentas(){
        return cuentaRepository.findAll();
    }

    public Boolean cuentaExiste(String numeroCuenta){
        return cuentaRepository.findFirstByNumeroCuenta(numeroCuenta) != null;
    }
    public ResponseEntity<Cuenta> obtenerCuentaPorId(Long cuentaId){
        return cuentaRepository.findById(cuentaId)
                .map(cuenta -> ResponseEntity.ok(cuenta))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Cuenta> crearCuenta(Cuenta cuenta) throws ClassCastException{
        if(cuentaExiste(cuenta.getNumeroCuenta())){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        if (cuenta.getNumeroCuenta() == null){
            return ResponseEntity.badRequest().build();
        }

        Cuenta nuevaCuenta = cuentaRepository.save(cuenta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCuenta);
    }
    public ResponseEntity<Cuenta> modificarUsuario(Long cuentaId,Cuenta cuenta){
        if(cuentaExiste(cuenta.getNumeroCuenta())){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        if (cuenta.getNumeroCuenta() == null){
            return ResponseEntity.badRequest().build();
        }
        return cuentaRepository.findById(cuentaId).map(cuenta1 -> {
            cuenta1.setNumeroCuenta(cuenta.getNumeroCuenta());
            cuenta1.setTipoCuenta(cuenta.getTipoCuenta());
            cuenta1.setSaldoInicial(cuenta.getSaldoInicial());
            cuenta1.setEstado(cuenta.getEstado());
            return ResponseEntity.ok(cuentaRepository.save(cuenta1));
        } ).orElseGet(()-> ResponseEntity.notFound().build());

    }

    public ResponseEntity<Object> eliminarCuenta(Long cuentaId){
        return cuentaRepository.findById(cuentaId).map(cuenta -> {
            cuentaRepository.delete(cuenta);
            return ResponseEntity.noContent().build();
        }).orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public ResponseEntity<Cuenta> actualizarCuenta(Long cuentaId, Map<String, Object> updates){
        return cuentaRepository.findById(cuentaId).map(cuenta -> {
            updates.forEach((key, value) -> {
                switch (key) {
                    case "numeroCuenta":
                        if (cuentaExiste((String) value)) {
                            ResponseEntity.status(HttpStatus.CONFLICT).build();
                        }else {
                            cuenta.setNumeroCuenta((String) value);
                        }
                        break;
                    case "tipoCuenta":
                        cuenta.setTipoCuenta((String) value);
                        break;
                    case "saldoInicial":
                        cuenta.setSaldoInicial((Double) value);
                        break;
                    case "estado":
                        cuenta.setEstado((String) value);
                        break;
                }
            });
            return ResponseEntity.ok(cuentaRepository.save(cuenta));

        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
