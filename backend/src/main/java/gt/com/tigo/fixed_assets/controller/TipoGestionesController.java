package gt.com.tigo.fixed_assets.controller;

import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.model.portal.TipoGestionesEntity;
import gt.com.tigo.fixed_assets.security.Authorized;
import gt.com.tigo.fixed_assets.service.TipoGestionesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/requestType")
public class TipoGestionesController implements ICatalog<TipoGestionesEntity> {

    private static final Logger logger = LoggerFactory.getLogger(TipoGestionesController.class);

    @Autowired
    private TipoGestionesService tipoGestionesService;

    @Override
    @Authorized
    @GetMapping("/findAll")
    public ResponseEntity findAll() {
        logger.debug(String.format("@%s::me()", this.getClass().getName()));

        try {
            return ResponseEntity.ok(this.tipoGestionesService.findAll());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    @Authorized
    @PostMapping("/findAllByPage")
    public ResponseEntity findAllByPage(DataTableRequestDto dataTableRequestDto) {
        return null;
    }

    @Override
    @Authorized
    @GetMapping("/findById")
    public ResponseEntity findById(@RequestParam(name = "id", required = true) Long id) {
        logger.debug(String.format("@%s::findById(%s)", this.getClass().getName(), id));

        try {
            return ResponseEntity.ok(this.tipoGestionesService.findById(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/findByName")
    public ResponseEntity findByName(@RequestParam(name = "name", required = true) String name) {
        logger.debug(String.format("@%s::findByName(%s)", this.getClass().getName(), name));

        try {
            return ResponseEntity.ok(this.tipoGestionesService.findByName(name));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    @PostMapping("/create/{requesterId}")
    public ResponseEntity create(TipoGestionesEntity entity, Long requesterId) {
        return null;
    }

    @Override
    @PutMapping("/update/{requesterId}")
    public ResponseEntity update(TipoGestionesEntity entity, Long requesterId) {
        return null;
    }

    @Override
    @DeleteMapping("/delete/{entityId}/{requesterId}")
    public ResponseEntity delete(Long entityId, Long requesterId) {
        return null;
    }
}
