package com.example.demo.service;

import com.example.demo.entities.Movimiento;
import com.example.demo.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MovimientoService {

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private MovimientoRepository movimientoRepository;

    public List<Movimiento> obtenerTodosMovimientos(){
        return movimientoRepository.findAll();

    }

    public ResponseEntity<Movimiento> obtenerMovimientoPorId(Long movimientoId){
        return movimientoRepository.findById(movimientoId)
                .map(movimientos -> ResponseEntity.ok(movimientos))
                .orElseGet(()-> ResponseEntity.notFound().build());
    }
    public ResponseEntity<Movimiento> crearMovimiento(Movimiento movimientos){
        return ResponseEntity.status(201).body(movimientoRepository.save(movimientos));
    }

    public ResponseEntity<Movimiento> modificarMovimiento(Long movimientoId, Movimiento movimientos){

        return movimientoRepository.findById(movimientoId).map(movimiento -> {
            movimiento.setFecha(movimientos.getFecha());
            movimiento.setTipoMovimiento(movimientos.getTipoMovimiento());
            movimiento.setValor(movimientos.getValor());
            movimiento.setSaldo(movimientos.getSaldo());
            return ResponseEntity.ok(movimientoRepository.save(movimiento));
        } ).orElseGet(()-> ResponseEntity.notFound().build());

    }
    public ResponseEntity<Object> eliminarMovimiento(Long movimientoId){
        return movimientoRepository.findById(movimientoId).map(movimiento -> {
            movimientoRepository.delete(movimiento);
            return ResponseEntity.noContent().build();
        }).orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public ResponseEntity<Movimiento> actualizarMovimiento(Long movimientoId, Map<String, Object> updates) throws ClassCastException{
        return movimientoRepository.findById(movimientoId).map(movimientos -> {
            updates.forEach((key, value) -> {
                switch (key) {
                    case "fecha":
                        try {
                            Date date = formatter.parse((String) value);
                            movimientos.setFecha((Date) date);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }finally {
                            break;
                        }
                    case "tipoMovimiento":
                        movimientos.setTipoMovimiento((String) value);
                        break;
                    case "valor":
                        movimientos.setValor((Double) value);
                        break;
                    case "saldo":
                        movimientos.setSaldo((Double) value);
                        break;
                }
            });
            return ResponseEntity.ok(movimientoRepository.save(movimientos));

        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
