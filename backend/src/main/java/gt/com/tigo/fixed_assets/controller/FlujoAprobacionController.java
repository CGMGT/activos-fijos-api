package gt.com.tigo.fixed_assets.controller;

import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.model.portal.FlujoAprobacionEntity;
import gt.com.tigo.fixed_assets.security.Authorized;
import gt.com.tigo.fixed_assets.service.FlujoAprobacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/workflow")
public class FlujoAprobacionController implements ICatalog<FlujoAprobacionEntity>{

    private static final Logger logger = LoggerFactory.getLogger(FlujoAprobacionController.class);

    @Autowired
    private FlujoAprobacionService workflowService;

    @Override
    @Authorized
    @GetMapping("/findAll")
    public ResponseEntity findAll() {
        logger.debug(String.format("@%s::me()", this.getClass().getName()));

        try {
            return ResponseEntity.ok(this.workflowService.findAll());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity findAllByPage(DataTableRequestDto dataTableRequestDto) {
        return null;
    }

    @Override
    @Authorized
    @GetMapping("/findById")
    public ResponseEntity findById(@RequestParam(name = "id", required = true) Long id) {
        logger.debug(String.format("@%s::findById(%d)", this.getClass().getName(), id));

        try {
            return ResponseEntity.ok(this.workflowService.findById(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @PostMapping("/findByWorkflowAndEvent")
    public ResponseEntity findByWorkflowAndEvent(@RequestBody(required = true) FlujoAprobacionEntity flujo) {
        logger.debug(String.format("@%s::findByWorkflowAndEvent(%s)", this.getClass().getName(), flujo));

        try {
            return ResponseEntity.ok(this.workflowService.findByWorkFlowAndEvent(flujo.getTipoGestion(),
                    flujo.getEstadoInicial(), flujo.getEvento()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity create(FlujoAprobacionEntity entity, Long requesterId) {
        return null;
    }

    @Override
    public ResponseEntity update(FlujoAprobacionEntity entity, Long requesterId) {
        return null;
    }

    @Override
    public ResponseEntity delete(Long entityId, Long requesterId) {
        return null;
    }
}
