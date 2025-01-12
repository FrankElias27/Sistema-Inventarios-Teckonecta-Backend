package com.example.aos.sistema_aos_spring_boot.Controladores;


import com.example.aos.sistema_aos_spring_boot.Modelo.StockTransfer;
import com.example.aos.sistema_aos_spring_boot.Servicios.StockTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfer")
@CrossOrigin("*")
public class StockTransferController {

    @Autowired
    private StockTransferService stockTransferService;

    @PostMapping("/")
    public ResponseEntity<StockTransfer> guardarTransferencia(@RequestBody StockTransfer stockTransfer) {
        return ResponseEntity.ok(stockTransferService.agregarTransferencia(stockTransfer));
    }

    @PutMapping("/")
    public ResponseEntity<StockTransfer> actualizarTransferencia(@RequestBody StockTransfer stockTransfer) {
        return ResponseEntity.ok(stockTransferService.actualizarTransferencia(stockTransfer));
    }

    @GetMapping("/")
    public ResponseEntity<?> listarTransferencias() {
        return ResponseEntity.ok(stockTransferService.obtenerTransferencias());
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<Page<StockTransfer>> listarTransferenciasPage(@PathVariable("page") int page) {

        if (page < 0) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Sort sort = Sort.by(Sort.Order.desc("transferId"));
            PageRequest pageRequest = PageRequest.of(page, 8, sort);
            Page<StockTransfer> transferenciasPage = stockTransferService.findAll(pageRequest);

            if (transferenciasPage.isEmpty() && page > 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.ok(transferenciasPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{transferId}")
    public StockTransfer listarTransferencia(@PathVariable("transferId") Long transferId) {
        return stockTransferService.obtenerTransferencia(transferId);
    }

    @DeleteMapping("/{transferId}")
    public void eliminarTransferencia(@PathVariable("transferId") Long transferId) {
        stockTransferService.eliminarTransferencia(transferId);
    }
}
