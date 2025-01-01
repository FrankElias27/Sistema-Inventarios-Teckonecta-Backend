package com.example.aos.sistema_aos_spring_boot.Controladores;

import com.example.aos.sistema_aos_spring_boot.Modelo.Categoria;
import com.example.aos.sistema_aos_spring_boot.Modelo.Cliente;
import com.example.aos.sistema_aos_spring_boot.Modelo.Servicio;
import com.example.aos.sistema_aos_spring_boot.Servicios.CategoriaService;
import com.example.aos.sistema_aos_spring_boot.Servicios.ServiciosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/servicio")
@CrossOrigin("*")
public class ServicioController {

    @Autowired
    private ServiciosService serviciosService;

    @PostMapping("/")
    public ResponseEntity<Servicio> guardarServicio(@RequestBody Servicio servicio){
        Servicio servicioGuardada = serviciosService.agregarServicio(servicio);
        return ResponseEntity.ok(servicioGuardada);
    }

    @GetMapping("/{servicioId}")
    public Servicio listarServicioPorId(@PathVariable("servicioId") Long servicioId){
        return serviciosService.obtenerServicios(servicioId);
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<Page<Servicio>> listarServicioPage(@PathVariable("page") int page) {

        if (page < 0) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Sort sort = Sort.by(Sort.Order.desc("servicioId"));
            PageRequest pageRequest = PageRequest.of(page, 8, sort);
            Page<Servicio> eventosPage = serviciosService.findAll(pageRequest);

            if (eventosPage.isEmpty() && page > 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.ok(eventosPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> listarServicios(){
        return ResponseEntity.ok(serviciosService.obtenerServicio());
    }

    @PutMapping("/")
    public Servicio actualizarServicio(@RequestBody Servicio servicio){
        return serviciosService.actualizarServicio(servicio);
    }

    @DeleteMapping("/{servicioId}")
    public void eliminarServicio(@PathVariable("servicioId") Long servicioId){
        serviciosService.eliminarServicio(servicioId);
    }
}
