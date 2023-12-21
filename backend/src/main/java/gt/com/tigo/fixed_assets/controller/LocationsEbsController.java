package gt.com.tigo.fixed_assets.controller;

import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.model.ebs.LocationsEbsEntity;
import gt.com.tigo.fixed_assets.security.Authorized;
import gt.com.tigo.fixed_assets.service.LocationsEbsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/ebsLocation")
public class LocationsEbsController implements ICatalog<LocationsEbsEntity>{
    private static final Logger logger = LoggerFactory.getLogger(LocationsEbsController.class);

    @Autowired
    private LocationsEbsService locationService;

    @Override
    public ResponseEntity findAll() {
        return null;
    }

    @Override
    public ResponseEntity findAllByPage(DataTableRequestDto dataTableRequestDto) {
        return null;
    }

    @Authorized
    @GetMapping("/findAllByPage")
    public ResponseEntity findAllByPage(@RequestParam(name = "offset", required = true) Long offset, @RequestParam(name = "fetchRows", required = true) Long fetchRows) {
        logger.debug(String.format("@%s::findAllByPage(%d,%d)", this.getClass().getName(), offset, fetchRows));

        try {
            return ResponseEntity.ok(this.locationService.findAllByPage(offset, fetchRows));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    @Authorized
    @GetMapping("/findById")
    public ResponseEntity findById(Long id) {
        logger.debug(String.format("@%s::findById(%d)", this.getClass().getName(), id));

        try {
            return ResponseEntity.ok(this.locationService.findById(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/sync")
    public ResponseEntity populate() {
        logger.debug(String.format("@%s::sync()", this.getClass().getName()));

        try {
            return ResponseEntity.ok(this.locationService.sync());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity create(LocationsEbsEntity entity, Long requesterId) {
        return null;
    }

    @Override
    public ResponseEntity update(LocationsEbsEntity entity, Long requesterId) {
        return null;
    }

    @Override
    public ResponseEntity delete(Long entityId, Long requesterId) {
        return null;
    }
}
