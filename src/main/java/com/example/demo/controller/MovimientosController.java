package com.example.demo.controller;

import com.example.demo.entities.Movimiento;
import com.example.demo.service.MovimientoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movimientos")
public class MovimientosController {

    private static final Logger log = LoggerFactory.getLogger(MovimientosController.class);
    @Autowired
    private MovimientoService movimientoService;

    @GetMapping
    public List<Movimiento> obtenerMovimientos(){
        return movimientoService.obtenerTodosMovimientos();
    }

    @GetMapping("/{movimientoId}")
    public ResponseEntity<Movimiento> obtenerMovimientosPorId(@PathVariable Long movimientoId){
        return movimientoService.obtenerMovimientoPorId(movimientoId);
    }
    @PostMapping
    public ResponseEntity<Movimiento> crearMovimiento(@RequestBody Movimiento movimientos){
        return movimientoService.crearMovimiento(movimientos);
    }

    @DeleteMapping(value = "/{movimientoId}")
    public ResponseEntity<Object> eliminarMovimiento(@PathVariable Long movimientoId){
        return movimientoService.eliminarMovimiento(movimientoId);
    }

    @PutMapping(path = "/{movimientoId}")
    public ResponseEntity<Movimiento> modificarMovimiento(@PathVariable long movimientoId, @RequestBody Movimiento movimientos){
        movimientos.setMovimientoId(movimientoId);
        return movimientoService.modificarMovimiento(movimientoId,movimientos);
    }

    @PatchMapping("/{movimientoId}")
    public ResponseEntity<Movimiento> actualizarMovimiento(@PathVariable Long movimientoId, @RequestBody Map<String, Object> updates){
        try{
            return movimientoService.actualizarMovimiento(movimientoId, updates);

        }catch (ClassCastException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }



}
