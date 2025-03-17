package com.example.demo.controller;

import com.example.demo.entities.Cuenta;
import com.example.demo.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public List<Cuenta> obtenerCuentas(){
        return cuentaService.obtenerCuentas();
    }

    @GetMapping("/{cuentaId}")
    public ResponseEntity<Cuenta> obtenerCuentaPorId(@PathVariable Long cuentaId){
        return cuentaService.obtenerCuentaPorId(cuentaId);
    }
    @PostMapping
    public ResponseEntity<Cuenta> crearCuenta(@RequestBody Cuenta cuenta){
        try {
            return cuentaService.crearCuenta(cuenta);
        }catch (ClassCastException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/{cuentaId}")
    public ResponseEntity<Object> eliminarCuenta(@PathVariable Long cuentaId){
        return cuentaService.eliminarCuenta(cuentaId);
    }

    @PutMapping(path = "/{cuentaId}")
    public ResponseEntity<Cuenta> modificarUsuario(@PathVariable long cuentaId, @RequestBody Cuenta cuenta){
        cuenta.setCuentaId(cuentaId);
        return cuentaService.modificarUsuario(cuentaId,cuenta);
    }

    @PatchMapping("/{cuentaId}")
    public ResponseEntity<Cuenta> actualizarCuenta(@PathVariable Long cuentaId, @RequestBody Map<String, Object> updates){
        return cuentaService.actualizarCuenta(cuentaId, updates);
    }



}
