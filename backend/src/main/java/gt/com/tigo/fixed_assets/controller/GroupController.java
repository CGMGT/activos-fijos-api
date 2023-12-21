package gt.com.tigo.fixed_assets.controller;

import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.model.portal.AdmGrupoEntity;
import gt.com.tigo.fixed_assets.security.Authorized;
import gt.com.tigo.fixed_assets.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/group")
public class GroupController implements ICatalog<AdmGrupoEntity> {

    private static final Logger logger = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    private GroupService groupService;

    @Override
    @Authorized
    @GetMapping("/findAll")
    public ResponseEntity findAll() {
        logger.debug(String.format("@%s::findAll()", this.getClass().getName()));

        try {
            return ResponseEntity.ok(this.groupService.findAll());
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
            return ResponseEntity.ok(this.groupService.findAllByPage(dataTableRequestDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity findById(Long id) {
        return null;
    }

    @Authorized
    @GetMapping("/findDefault")
    public ResponseEntity findDefault() {
        logger.debug(String.format("%s::findDefault()", this.getClass().getName()));

        try {
            return ResponseEntity.ok(this.groupService.findDefault());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    @Authorized
    @PostMapping("/create/{requesterId}")
    public ResponseEntity create(@RequestBody(required = true) AdmGrupoEntity entity, @PathVariable(required = true) Long requesterId) {
        logger.debug(String.format("@%s::create(%s, %s)", this.getClass().getName(), entity, requesterId));

        try {
            return ResponseEntity.ok(this.groupService.create(entity, requesterId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    @Authorized
    @PutMapping("/update/{requesterId}")
    public ResponseEntity update(@RequestBody(required = true) AdmGrupoEntity entity, @PathVariable(required = true) Long requesterId) {
        logger.debug(String.format("@%s::update(%s, %s)", this.getClass().getName(), entity, requesterId));

        try {
            return ResponseEntity.ok(this.groupService.update(entity, requesterId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    @Authorized
    @DeleteMapping("/delete/{entityId}/{requesterId}")
    public ResponseEntity delete(@PathVariable(required = true) Long entityId, @PathVariable(required = true) Long requesterId) {
        logger.debug(String.format("@%s::delete(%s, %s)", this.getClass().getName(), entityId, requesterId));

        try {
            return ResponseEntity.ok(this.groupService.delete(entityId, requesterId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
}
