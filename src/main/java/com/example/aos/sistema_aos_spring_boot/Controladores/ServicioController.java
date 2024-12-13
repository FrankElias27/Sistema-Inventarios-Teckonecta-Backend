package com.example.aos.sistema_aos_spring_boot.Controladores;

import com.example.aos.sistema_aos_spring_boot.Modelo.Categoria;
import com.example.aos.sistema_aos_spring_boot.Modelo.Servicio;
import com.example.aos.sistema_aos_spring_boot.Servicios.CategoriaService;
import com.example.aos.sistema_aos_spring_boot.Servicios.ServiciosService;
import org.springframework.beans.factory.annotation.Autowired;
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
