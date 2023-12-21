package gt.com.tigo.fixed_assets.controller;

import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.model.portal.AdmUsuarioEntity;
import gt.com.tigo.fixed_assets.security.Authorized;
import gt.com.tigo.fixed_assets.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController implements ICatalog<AdmUsuarioEntity> {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity me(@RequestHeader(name = "Authorization", required = true) String token) {
        logger.debug(String.format("@%s::me(%s)", this.getClass().getName(), token));

        try {
            return ResponseEntity.ok(this.userService.getInfo(token));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/photo")
    public ResponseEntity photo(@RequestHeader("Authorization") String token) {
        logger.debug(String.format("@%s::photo(%s)", this.getClass().getName(), token));

        try {
            return ResponseEntity.ok(this.userService.getPhoto(token));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    @Authorized
    @GetMapping("/findAll")
    public ResponseEntity findAll() {
        logger.debug(String.format("@%s::me()", this.getClass().getName()));

        try {
            return ResponseEntity.ok(this.userService.findAll());
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
            return ResponseEntity.ok(this.userService.findAllByPage(dataTableRequestDto));
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
            return ResponseEntity.ok(this.userService.findById(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/findByUid")
    public ResponseEntity findByUid(@RequestHeader(name = "Authorization", required = true) String token) {
        logger.debug(String.format("@%s::findByUid(********)", this.getClass().getName()));

        try {
            return ResponseEntity.ok(this.userService.findByUid(token));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/findByEmail")
    public ResponseEntity findByEmail(@RequestParam(name = "email", required = true) String email) {
        logger.debug(String.format("@%s::findByEmail(%s)", this.getClass().getName(), email));

        try {
            return ResponseEntity.ok(this.userService.findByEmail(email));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    @Authorized
    @PostMapping("/create/{requesterId}")
    public ResponseEntity create(@RequestBody(required = true) AdmUsuarioEntity entity, @PathVariable(required = true) Long requesterId) {
        logger.debug(String.format("@%s::create(%s, %s)", this.getClass().getName(), entity, requesterId));

        try {
            return ResponseEntity.ok(this.userService.create(entity, requesterId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    @Authorized
    @PutMapping("/update/{requesterId}")
    public ResponseEntity update(@RequestBody(required = true) AdmUsuarioEntity entity, @PathVariable(required = true) Long requesterId) {
        logger.debug(String.format("@%s::update(%s, %s)", this.getClass().getName(), entity, requesterId));

        try {
            return ResponseEntity.ok(this.userService.update(entity, requesterId));
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
            return ResponseEntity.ok(this.userService.delete(entityId, requesterId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
}
