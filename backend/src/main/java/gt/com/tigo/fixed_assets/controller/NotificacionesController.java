package gt.com.tigo.fixed_assets.controller;

import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.model.portal.NotificacionesEntity;
import gt.com.tigo.fixed_assets.security.Authorized;
import gt.com.tigo.fixed_assets.service.NotificacionesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/notification")
public class NotificacionesController implements ICatalog<NotificacionesEntity> {
    private static final Logger logger = LoggerFactory.getLogger(NotificacionesController.class);

    @Autowired
    private NotificacionesService notificationService;

    @Authorized
    @GetMapping("/getAreaTree")
    public ResponseEntity getAreaTree() {
        logger.debug(String.format("@%s::getAreaTree()", this.getClass().getName()));

        try {
            return ResponseEntity.ok(this.notificationService.getAreaTree());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @PostMapping("/findBusinessUnitByPage")
    public ResponseEntity findUnidadNegocioByPage(@RequestBody(required = true) DataTableRequestDto dataTableRequestDto) {
        logger.debug(String.format("@%s::findUnidadNegocioByPage(%s)", this.getClass().getName(), dataTableRequestDto.toString()));

        try {
            return ResponseEntity.ok(this.notificationService.findUnidadNegocioByPage(dataTableRequestDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @PostMapping("/findFunctionalAreaByPage")
    public ResponseEntity findAreaFuncionalByPage(@RequestBody(required = true) DataTableRequestDto dataTableRequestDto) {
        logger.debug(String.format("@%s::findAreaFuncionalByPage(%s)", this.getClass().getName(), dataTableRequestDto.toString()));

        try {
            return ResponseEntity.ok(this.notificationService.findAreaFuncionalByPage(dataTableRequestDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @PostMapping("/findAdminUnitByPage")
    public ResponseEntity findUnidadAdministrativaByPage(@RequestBody(required = true) DataTableRequestDto dataTableRequestDto) {
        logger.debug(String.format("@%s::findUnidadAdministrativaByPage(%s)", this.getClass().getName(), dataTableRequestDto.toString()));

        try {
            return ResponseEntity.ok(this.notificationService.findUnidadAdministrativaByPage(dataTableRequestDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @PostMapping("/send/{userId}")
    public ResponseEntity send(@PathVariable(name = "userId", required = true) Long userId) {
        logger.debug(String.format("@%s::send(%d)", this.getClass().getName(), userId));

        try {
            return ResponseEntity.ok(this.notificationService.send(userId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

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
            return ResponseEntity.ok(this.notificationService.findNotificacionesByPage(dataTableRequestDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity findById(Long id) {
        return null;
    }

    @Authorized
    @PostMapping("/create/{requesterId}")
    public ResponseEntity create(@RequestBody(required = true) NotificacionesEntity entity, @PathVariable(required = true)  Long requesterId) {
        logger.debug(String.format("@%s::create(%s, %d)", this.getClass().getName(), entity, requesterId));

        try {
            return ResponseEntity.ok(this.notificationService.create(entity, requesterId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @PutMapping("/update/{requesterId}")
    public ResponseEntity update(@RequestBody(required = true) NotificacionesEntity entity, @PathVariable(required = true)  Long requesterId) {
        logger.debug(String.format("@%s::update(%s, %d)", this.getClass().getName(), entity, requesterId));

        try {
            return ResponseEntity.ok(this.notificationService.update(entity, requesterId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @DeleteMapping("/delete/{entityId}/{requesterId}")
    public ResponseEntity delete(@PathVariable(name = "entityId", required = true) Long entityId, @PathVariable(name = "requesterId", required = true) Long requesterId) {
        logger.debug(String.format("@%s::delete(%d, %d)", this.getClass().getName(), entityId, requesterId));

        try {
            return ResponseEntity.ok(this.notificationService.delete(entityId, requesterId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
}
