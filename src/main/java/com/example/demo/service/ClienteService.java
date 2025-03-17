package com.example.demo.service;

import com.example.demo.entities.Cliente;
import com.example.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> obtenerClientes(){
        return clienteRepository.findAll();
    }


    public ResponseEntity<Cliente> obtenerClientePorId(Long clienteId){
        return clienteRepository.findById(clienteId)
                .map(cliente -> ResponseEntity.ok(cliente))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Cliente> crearCliente(Cliente cliente){

        if (cliente.getClienteId() == null){
            return ResponseEntity.badRequest().build();
        }
        Cliente nuevoCliente = clienteRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }
    public ResponseEntity<Cliente> modificarUsuario(Long clienteId,Cliente cliente){


        if (cliente.getClienteId() == null){
            return ResponseEntity.badRequest().build();
        }
        return clienteRepository.findById(clienteId).map(cliente1 -> {

            cliente1.setNombre(cliente.getNombre());
            cliente1.setGenero(cliente.getGenero());
            cliente1.setEdad(cliente.getEdad());
            cliente1.setTelefono(cliente.getTelefono());
            cliente1.setDireccion(cliente.getDireccion());
            cliente1.setIdentificacion(cliente.getIdentificacion());
            cliente1.setClienteId(cliente.getClienteId());
            cliente1.setContrasenia(cliente.getContrasenia());
            cliente1.setEstado(cliente.getEstado());
            return ResponseEntity.ok(clienteRepository.save(cliente1));
        } ).orElseGet(()-> ResponseEntity.notFound().build());

    }

    public ResponseEntity<Object> eliminarCliente(Long clienteId){
        return clienteRepository.findById(clienteId).map(cliente -> {
            clienteRepository.delete(cliente);
            return ResponseEntity.noContent().build();
        }).orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public ResponseEntity<Cliente> actualizarCliente(Long ClienteId, Map<String, Object> updates){
        return clienteRepository.findById(ClienteId).map(cliente -> {
            updates.forEach((key, value) -> {
                switch (key) {
                    case "clienteid":
                        cliente.setClienteId((String) value);
                        break;
                    case "contrasenia":
                        cliente.setContrasenia((String) value);
                        break;
                    case "estado":
                        cliente.setEstado((String) value);
                        break;
                    case "nombre":
                        cliente.setNombre((String) value);
                        break;
                    case "genero":
                        cliente.setGenero((String) value);
                        break;
                    case "edad":
                        cliente.setEdad((int) value);
                        break;
                    case "identificacion":
                        cliente.setIdentificacion((String) value);
                        break;
                    case "direccion":
                        cliente.setDireccion((String) value);
                        break;
                    case "telefono":
                        cliente.setTelefono((String) value);
                        break;
                }
            });
            return ResponseEntity.ok(clienteRepository.save(cliente));

        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
