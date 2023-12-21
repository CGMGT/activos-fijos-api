package gt.com.tigo.fixed_assets.controller;

import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.model.portal.EmailBlacklistEntity;
import gt.com.tigo.fixed_assets.security.Authorized;
import gt.com.tigo.fixed_assets.service.EmailBlacklistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/blackList")
public class EmailBlacklistController implements ICatalog<EmailBlacklistEntity>{
    private static final Logger logger = LoggerFactory.getLogger(EmailBlacklistController.class);

    @Autowired
    private EmailBlacklistService blacklistService;

    @Override
    public ResponseEntity findAll() {
        return null;
    }

    @Override
    @Authorized
    @PostMapping("/findAllByPage")
    public ResponseEntity findAllByPage(@RequestBody(required = true)  DataTableRequestDto dataTableRequestDto) {
        logger.debug(String.format("@%s::findAllByPage(%s)", this.getClass().getName(), dataTableRequestDto.toString()));

        try {
            return ResponseEntity.ok(this.blacklistService.findAllByPage(dataTableRequestDto));
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
            return ResponseEntity.ok(this.blacklistService.findById(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    @Authorized
    @PostMapping("/create/{requesterId}")
    public ResponseEntity create(@RequestBody(required = true) EmailBlacklistEntity entity, @PathVariable(required = true) Long requesterId) {
        logger.debug(String.format("@%s::create(%s, %d)", this.getClass().getName(), entity, requesterId));

        try {
            return ResponseEntity.ok(this.blacklistService.create(entity, requesterId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    @Authorized
    @PutMapping("/update/{requesterId}")
    public ResponseEntity update(@RequestBody(required = true) EmailBlacklistEntity entity, @PathVariable(required = true) Long requesterId) {
        logger.debug(String.format("@%s::update(%s, %d)", this.getClass().getName(), entity, requesterId));

        try {
            return ResponseEntity.ok(this.blacklistService.update(entity, requesterId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    @Authorized
    @DeleteMapping("/delete/{entityId}/{requesterId}")
    public ResponseEntity delete(@PathVariable(name = "entityId", required = true) Long entityId, @PathVariable(name = "requesterId", required = true) Long requesterId) {
        logger.debug(String.format("@%s::delete(%d, %d)", this.getClass().getName(), entityId, requesterId));

        try {
            return ResponseEntity.ok(this.blacklistService.delete(entityId, requesterId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

}
