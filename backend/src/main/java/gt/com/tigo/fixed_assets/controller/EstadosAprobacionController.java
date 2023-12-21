package gt.com.tigo.fixed_assets.controller;

import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.model.portal.EstadosAprobacionEntity;
import gt.com.tigo.fixed_assets.security.Authorized;
import gt.com.tigo.fixed_assets.service.EstadosAprobacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/workflowStatus")
public class EstadosAprobacionController implements ICatalog<EstadosAprobacionEntity> {

    private static final Logger logger = LoggerFactory.getLogger(EstadosAprobacionController.class);

    @Autowired
    private EstadosAprobacionService estadosAprobacionService;

    @Override
    @Authorized
    @GetMapping("/findAll")
    public ResponseEntity findAll() {
        logger.debug(String.format("@%s::me()", this.getClass().getName()));

        try {
            return ResponseEntity.ok(this.estadosAprobacionService.findAll());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity findAllByPage(DataTableRequestDto dataTableRequestDto) {
        return null;
    }

    @Authorized
    @Override
    @GetMapping("/findById")
    public ResponseEntity findById(@RequestParam(name = "id", required = true) Long id) {
        logger.debug(String.format("@%s::findById(%d)", this.getClass().getName(), id));

        try {
            return ResponseEntity.ok(this.estadosAprobacionService.findById(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/findByName")
    public ResponseEntity findByName(@RequestParam(name = "name", required = true) String name) {
        logger.debug(String.format("@%s::findByName(%s)", this.getClass().getName(), name));

        try {
            return ResponseEntity.ok(this.estadosAprobacionService.findByName(name));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity create(EstadosAprobacionEntity entity, Long requesterId) {
        return null;
    }

    @Override
    public ResponseEntity update(EstadosAprobacionEntity entity, Long requesterId) {
        return null;
    }

    @Override
    public ResponseEntity delete(Long entityId, Long requesterId) {
        return null;
    }
}
