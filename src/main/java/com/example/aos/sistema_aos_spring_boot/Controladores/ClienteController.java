package com.example.aos.sistema_aos_spring_boot.Controladores;

import com.example.aos.sistema_aos_spring_boot.Modelo.Categoria;
import com.example.aos.sistema_aos_spring_boot.Modelo.Cliente;
import com.example.aos.sistema_aos_spring_boot.Modelo.Ventas;
import com.example.aos.sistema_aos_spring_boot.Servicios.CategoriaService;
import com.example.aos.sistema_aos_spring_boot.Servicios.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Page<Cliente>> listarClientePage(@PathVariable("page") int page) {

        if (page < 0) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Sort sort = Sort.by(Sort.Order.desc("clienteId"));
            PageRequest pageRequest = PageRequest.of(page, 8, sort);
            Page<Cliente> eventosPage = clienteService.findAll(pageRequest);

            if (eventosPage.isEmpty() && page > 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.ok(eventosPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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
