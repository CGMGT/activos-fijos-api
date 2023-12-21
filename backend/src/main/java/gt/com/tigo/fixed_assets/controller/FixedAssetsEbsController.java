package gt.com.tigo.fixed_assets.controller;

import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.model.ebs.FixedAssetsEbsEntity;
import gt.com.tigo.fixed_assets.security.Authorized;
import gt.com.tigo.fixed_assets.service.FixedAssetsEbsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/ebsFixedAsset")
public class FixedAssetsEbsController implements ICatalog<FixedAssetsEbsEntity>{
    private static final Logger logger = LoggerFactory.getLogger(FixedAssetsEbsController.class);

    @Autowired
    private FixedAssetsEbsService faService;


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
            return ResponseEntity.ok(this.faService.findAllByPage(offset, fetchRows));
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
            return ResponseEntity.ok(this.faService.findById(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/sync")
    public ResponseEntity populate() {
        logger.debug(String.format("@%s::sync()", this.getClass().getName()));

        try {
            return ResponseEntity.ok(this.faService.sync());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity create(FixedAssetsEbsEntity entity, Long requesterId) {
        return null;
    }

    @Override
    public ResponseEntity update(FixedAssetsEbsEntity entity, Long requesterId) {
        return null;
    }

    @Override
    public ResponseEntity delete(Long entityId, Long requesterId) {
        return null;
    }
}
