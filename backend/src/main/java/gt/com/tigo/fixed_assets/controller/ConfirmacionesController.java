package gt.com.tigo.fixed_assets.controller;

import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.model.portal.ConfirmacionesEntity;
import gt.com.tigo.fixed_assets.security.Authorized;
import gt.com.tigo.fixed_assets.service.ConfirmacionesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin
@RestController
@RequestMapping("/confirmation")
public class ConfirmacionesController implements ICatalog<ConfirmacionesEntity> {
    private static final Logger logger = LoggerFactory.getLogger(ConfirmacionesController.class);

    @Autowired
    private ConfirmacionesService service;

    @Override
    public ResponseEntity findAll() {
        return null;
    }

    @Override
    @Authorized
    @PostMapping("/findAllByPage")
    public ResponseEntity findAllByPage(@RequestBody(required = true) DataTableRequestDto dataTableRequestDto) {
        logger.debug(String.format("@%s::findAllByPage(%s)", this.getClass().getName(), dataTableRequestDto.toString()));

        try {
            return ResponseEntity.ok(this.service.findAllByPage(dataTableRequestDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity findById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity create(ConfirmacionesEntity entity, Long requesterId) {
        return null;
    }

    @Override
    @Authorized
    @PutMapping("/update/{requesterId}")
    public ResponseEntity update(@RequestBody(required = true) ConfirmacionesEntity entity, @PathVariable(required = true)  Long requesterId) {
        logger.debug(String.format("@%s::update(%s, %d)", this.getClass().getName(), entity, requesterId));

        try {
            return ResponseEntity.ok(this.service.update(entity, requesterId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity delete(Long entityId, Long requesterId) {
        return null;
    }

    @Authorized
    @PostMapping("/report")
    public ResponseEntity report(@RequestBody(required = true) DataTableRequestDto dataTableRequestDto) {
        logger.debug(String.format("@%s::report(%s)", this.getClass().getName(), dataTableRequestDto.toString()));

        try {
            return ResponseEntity.ok(this.service.report(dataTableRequestDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @PostMapping("/export/xlsx")
    public ModelAndView exportAsXLSX(@RequestBody(required = true) DataTableRequestDto dataTableRequestDto) {
        logger.debug(String.format("@%s::exportAsXLSX(%s)", this.getClass().getName(), dataTableRequestDto.toString()));

        try {
            return this.service.exportAsXLSX(dataTableRequestDto);
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            return null;
        }
    }
}
