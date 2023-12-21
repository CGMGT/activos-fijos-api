package gt.com.tigo.fixed_assets.controller;

import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.model.portal.CpLocationsEntity;
import gt.com.tigo.fixed_assets.security.Authorized;
import gt.com.tigo.fixed_assets.service.LocacionesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin
@RestController
@RequestMapping("/location")
public class LocacionesController implements ICatalog<CpLocationsEntity> {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private LocacionesService locationsService;


    @Override
    @Authorized
    @GetMapping("/findAll")
    public ResponseEntity findAll() {
        logger.debug(String.format("@%s::me()", this.getClass().getName()));

        try {
            return ResponseEntity.ok(this.locationsService.findAll());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    @Authorized
    @PostMapping("/findAllByPage")
    public ResponseEntity findAllByPage(@RequestBody(required = true) DataTableRequestDto dataTableRequestDto) {
        logger.debug(String.format("@%s::findAllByPage(%s)", this.getClass().getName(), dataTableRequestDto.toString()));

        try {
            return ResponseEntity.ok(this.locationsService.findAllByPage(dataTableRequestDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/findByUserId")
    public ResponseEntity findByUserId(@RequestParam(name = "userId", required = true) Long userId) {
        logger.debug(String.format("@%s::findByUserId(%s)", this.getClass().getName(), userId));

        try {
            return ResponseEntity.ok(this.locationsService.getLocationsByUserId(userId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/getDefaultWarehouse")
    public ResponseEntity getDefaultWarehouse() {
        logger.debug(String.format("@%s::findByUserId()", this.getClass().getName()));

        try {
            return ResponseEntity.ok(this.locationsService.getDefaultWarehouse());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    @Authorized
    @GetMapping("/findById")
    public ResponseEntity findById(@RequestParam(name = "id", required = true) Long id) {
        logger.debug(String.format("@%s::findById(%s)", this.getClass().getName(), id));

        try {
            return ResponseEntity.ok(this.locationsService.findById(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity create(CpLocationsEntity entity, Long requesterId) {
        return null;
    }

    @Override
    @Authorized
    @PutMapping("/update/{requesterId}")
    public ResponseEntity update(@RequestBody(required = true) CpLocationsEntity entity, @PathVariable(required = true) Long requesterId) {
        logger.debug(String.format("@%s::update(%s, %s)", this.getClass().getName(), entity, requesterId));

        try {
            return ResponseEntity.ok(this.locationsService.update(entity, requesterId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity delete(Long entityId, Long requesterId) {
        return null;
    }

    @Authorized
    @GetMapping("/export/xlsx")
    public ModelAndView exportAsXLSX() {
        logger.debug(String.format("@%s::exportAsXLSX()", this.getClass().getName()));

        try {
            return this.locationsService.exportAsXLSX();
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            return null;
        }
    }
}
