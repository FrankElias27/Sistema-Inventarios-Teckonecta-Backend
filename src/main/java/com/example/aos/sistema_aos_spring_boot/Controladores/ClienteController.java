package com.example.aos.sistema_aos_spring_boot.Controladores;

import com.example.aos.sistema_aos_spring_boot.Modelo.Categoria;
import com.example.aos.sistema_aos_spring_boot.Modelo.Cliente;
import com.example.aos.sistema_aos_spring_boot.Modelo.Ventas;
import com.example.aos.sistema_aos_spring_boot.Servicios.CategoriaService;
import com.example.aos.sistema_aos_spring_boot.Servicios.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin("*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/")
    public ResponseEntity<Cliente> guardarCliente(@RequestBody Cliente cliente){
        Cliente clienteGuardado = clienteService.agregarCliente(cliente);
        return ResponseEntity.ok(clienteGuardado);
    }

    @GetMapping("/{clienteId}")
    public Cliente listarClientePorId(@PathVariable("clienteId") Long clienteId){
        return clienteService.obtenerCliente(clienteId);
    }

    @GetMapping("/page/{page}")
    public Page<Cliente> listarCliente(@PathVariable("page") int page){
        return clienteService.findAll(PageRequest.of(page,10));
    }

    @GetMapping("/")
    public ResponseEntity<?> listarClientes(){
        return ResponseEntity.ok(clienteService.obtenerClientes());
    }

    @PutMapping("/")
    public Cliente actualizarCliente(@RequestBody Cliente cliente){
        return clienteService.actualizarCliente(cliente);
    }

    @DeleteMapping("/{clienteId}")
    public void eliminarCliente(@PathVariable("clienteId") Long clienteId){
        clienteService.eliminarCliente(clienteId);
    }
}
