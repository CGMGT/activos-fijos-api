package gt.com.tigo.fixed_assets.controller;

import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.model.portal.AdmParametrosEntity;
import gt.com.tigo.fixed_assets.security.Authorized;
import gt.com.tigo.fixed_assets.service.ParameterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/parameter")
public class ParameterController implements ICatalog<AdmParametrosEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterController.class);

    @Autowired
    private ParameterService paramService;

    @Override
    @Authorized
    @GetMapping("/findAll")
    public ResponseEntity findAll() {
        LOGGER.debug(String.format("@%s::findAll()", this.getClass().getName()));

        try {
            return ResponseEntity.ok(this.paramService.findAll());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/findByName")
    public ResponseEntity findByName(@RequestParam(required = true) String name) {
        LOGGER.debug(String.format("@%s::findByName(%s)", this.getClass().getName(), name));

        try {
            return ResponseEntity.ok(this.paramService.findByNombre(name));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity findAllByPage(DataTableRequestDto dataTableRequestDto) {
        return null;
    }

    @Override
    public ResponseEntity findById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity create(AdmParametrosEntity entity, Long requesterId) {
        return null;
    }

    @Override
    @Authorized
    @PutMapping("/update/{requesterId}")
    public ResponseEntity update(@RequestBody(required = true) AdmParametrosEntity entity, @PathVariable(required = true) Long requesterId) {
        LOGGER.debug(String.format("@%s::update(%s, %s)", this.getClass().getName(), entity, requesterId));

        try {
            return ResponseEntity.ok(this.paramService.update(entity, requesterId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity delete(Long entityId, Long requesterId) {
        return null;
    }
}
