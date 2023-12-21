package gt.com.tigo.fixed_assets.controller;

import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.model.portal.EmailTemplateEntity;
import gt.com.tigo.fixed_assets.security.Authorized;
import gt.com.tigo.fixed_assets.service.EmailTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/emailTemplate")
public class EmailTemplateController implements ICatalog<EmailTemplateEntity>{
    private static final Logger logger = LoggerFactory.getLogger(EmailTemplateController.class);

    @Autowired
    private EmailTemplateService emailService;

    @Override
    @Authorized
    @GetMapping("/findAll")
    public ResponseEntity findAll() {
        logger.debug(String.format("@%s::me()", this.getClass().getName()));

        try {
            return ResponseEntity.ok(this.emailService.findAll());
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
            return ResponseEntity.ok(this.emailService.findAllByPage(dataTableRequestDto));
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
            return ResponseEntity.ok(this.emailService.findById(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PostMapping("/create/{requesterId}")
    @Authorized
    @Override
    public ResponseEntity create(@RequestBody(required = true) EmailTemplateEntity entity, @PathVariable(required = true)  Long requesterId) {
        logger.debug(String.format("@%s::create(%s, %d)", this.getClass().getName(), entity, requesterId));

        try {
            return ResponseEntity.ok(this.emailService.create(entity, requesterId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PutMapping("/update/{requesterId}")
    @Authorized
    @Override
    public ResponseEntity update(@RequestBody(required = true) EmailTemplateEntity entity, @PathVariable(required = true)  Long requesterId) {
        logger.debug(String.format("@%s::update(%s, %d)", this.getClass().getName(), entity, requesterId));

        try {
            return ResponseEntity.ok(this.emailService.update(entity, requesterId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @DeleteMapping("/delete/{entityId}/{requesterId}")
    @Authorized
    @Override
    public ResponseEntity delete(@PathVariable(name = "entityId", required = true) Long entityId, @PathVariable(name = "requesterId", required = true) Long requesterId) {
        logger.debug(String.format("@%s::delete(%d, %d)", this.getClass().getName(), entityId, requesterId));

        try {
            return ResponseEntity.ok(this.emailService.delete(entityId, requesterId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
}
