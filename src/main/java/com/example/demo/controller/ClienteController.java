package com.example.demo.controller;

import com.example.demo.entities.Cliente;
import com.example.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> obtenerClientes(){
        return clienteService.obtenerClientes();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long clienteId){
        return clienteService.obtenerClientePorId(clienteId);
    }

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente){
        return clienteService.crearCliente(cliente);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Object> eliminarCliente(@PathVariable Long clienteId){
        return clienteService.eliminarCliente(clienteId);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> modificarUsuario(@PathVariable long clienteId, @RequestBody Cliente cliente){
        cliente.setClienteId(String.valueOf(clienteId));
        return clienteService.modificarUsuario(clienteId,cliente);
    }
    @PatchMapping("/{clienteId}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long clienteId, @RequestBody Map<String, Object> updates){
        return clienteService.actualizarCliente(clienteId, updates);
    }

}
