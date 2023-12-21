package gt.com.tigo.fixed_assets.controller;

import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.model.portal.GestionesDetalleEntity;
import gt.com.tigo.fixed_assets.security.Authorized;
import gt.com.tigo.fixed_assets.service.GestionesDetalleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/requestDetail")
public class GestionesDetalleController implements ICatalog<GestionesDetalleEntity> {

    private static final Logger logger = LoggerFactory.getLogger(GestionesController.class);

    @Autowired
    private GestionesDetalleService requestDetailService;

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
            return ResponseEntity.ok(this.requestDetailService.findAllByPage(dataTableRequestDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity findById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity create(GestionesDetalleEntity entity, Long requesterId) {
        return null;
    }

    @Override
    public ResponseEntity update(GestionesDetalleEntity entity, Long requesterId) {
        return null;
    }

    @Override
    public ResponseEntity delete(Long entityId, Long requesterId) {
        return null;
    }
}
