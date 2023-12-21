package gt.com.tigo.fixed_assets.controller;

import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.model.vhur.EmpleadosVhurEntity;
import gt.com.tigo.fixed_assets.security.Authorized;
import gt.com.tigo.fixed_assets.service.EmpleadosVhurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/vhurEmployee")
public class EmpleadosVhurController implements ICatalog<EmpleadosVhurEntity> {
    private static final Logger logger = LoggerFactory.getLogger(EmpleadosVhurController.class);

    @Autowired
    private EmpleadosVhurService employeesService;

    @Override
    @Authorized
    @GetMapping("/findAll")
    public ResponseEntity findAll() {
        logger.debug(String.format("@%s::findAll()", this.getClass().getName()));

        try {
            return ResponseEntity.ok(this.employeesService.findAll());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
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
            return ResponseEntity.ok(this.employeesService.findAllByPage(offset, fetchRows));
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
            return ResponseEntity.ok(this.employeesService.findById(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }


    @GetMapping("/sync")
    public ResponseEntity populate() {
        logger.debug(String.format("@%s::populate()", this.getClass().getName()));

        try {
            return ResponseEntity.ok(this.employeesService.sync());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity create(EmpleadosVhurEntity entity, Long requesterId) {
        return null;
    }

    @Override
    public ResponseEntity update(EmpleadosVhurEntity entity, Long requesterId) {
        return null;
    }

    @Override
    public ResponseEntity delete(Long entityId, Long requesterId) {
        return null;
    }
}
