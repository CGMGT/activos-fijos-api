package gt.com.tigo.fixed_assets.controller;

import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.model.portal.CpActivosFijosEntity;
import gt.com.tigo.fixed_assets.security.Authorized;
import gt.com.tigo.fixed_assets.service.ActivosFijosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin
@RestController
@RequestMapping("/fixedAsset")
public class ActivosFijosController implements ICatalog<CpActivosFijosEntity> {
    private static final Logger logger = LoggerFactory.getLogger(ActivosFijosController.class);

    @Autowired
    private ActivosFijosService faService;


    @Override
    @Authorized
    @GetMapping("/findAll")
    public ResponseEntity findAll() {
        logger.debug(String.format("@%s::me()", this.getClass().getName()));

        try {
            return ResponseEntity.ok(this.faService.findAll());
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
            return ResponseEntity.ok(this.faService.findAllByPage(dataTableRequestDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @PostMapping("/findAllByPageByUserId")
    public ResponseEntity findAllByPageByUserId(@RequestBody(required = true) DataTableRequestDto dataTableRequestDto) {
        logger.debug(String.format("@%s::findAllByPage(%s)", this.getClass().getName(), dataTableRequestDto.toString()));

        try {
            return ResponseEntity.ok(this.faService.findAllByPageByUserId(dataTableRequestDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    @Authorized
    @GetMapping("/findById")
    public ResponseEntity findById(@RequestParam(name = "id", required = true) Long id) {
        logger.debug(String.format("@%s::findById(%d)", this.getClass().getName(), id));

        try {
            return ResponseEntity.ok(this.faService.findById(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/findByAssetNumber")
    public ResponseEntity findByAssetNumber(@RequestParam(name = "assetNumber", required = true) Long assetNumber) {
        logger.debug(String.format("@%s::findByAssetNumber(%d)", this.getClass().getName(), assetNumber));

        try {
            return ResponseEntity.ok(this.faService.findByAssetNumber(assetNumber));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/findBySerialNumber")
    public ResponseEntity findBySerialNumber(@RequestParam(name = "serialNumber", required = true) String serialNumber) {
        logger.debug(String.format("@%s::findBySerialNumber(%s)", this.getClass().getName(), serialNumber));

        try {
            return ResponseEntity.ok(this.faService.findBySerialNumber(serialNumber));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/findByModelNumber")
    public ResponseEntity findByModelNumber(@RequestParam(name = "modelNumber", required = true) String modelNumber) {
        logger.debug(String.format("@%s::findByModelNumber(%s)", this.getClass().getName(), modelNumber));

        try {
            return ResponseEntity.ok(this.faService.findByModelNumber(modelNumber));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/findByLegacyCode")
    public ResponseEntity findByLegacyCode(@RequestParam(name = "legacyCode", required = true) String legacyCode) {
        logger.debug(String.format("@%s::findByLegacyCode(%s)", this.getClass().getName(), legacyCode));

        try {
            return ResponseEntity.ok(this.faService.findByLegacyCode(legacyCode));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/findByTagNumber")
    public ResponseEntity findByTagNumber(@RequestParam(name = "tagNumber", required = true) String tagNumber) {
        logger.debug(String.format("@%s::findByTagNumber(%s)", this.getClass().getName(), tagNumber));

        try {
            return ResponseEntity.ok(this.faService.findByTagNumber(tagNumber));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/findRemoveFaMessage")
    public ResponseEntity findRemoveFaMessage() {
        logger.debug(String.format("@%s::findRemoveFaMessage()", this.getClass().getName()));

        try {
            return ResponseEntity.ok(this.faService.findRemoveFaMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity create(CpActivosFijosEntity entity, Long requesterId) {
        return null;
    }

    @Override
    public ResponseEntity update(CpActivosFijosEntity entity, Long requesterId) {
        return null;
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
            return this.faService.exportAsXLSX();
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            return null;
        }
    }
}
