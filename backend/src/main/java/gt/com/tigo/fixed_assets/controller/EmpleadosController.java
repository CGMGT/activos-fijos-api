package gt.com.tigo.fixed_assets.controller;

import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.model.portal.CpEmpleadosEntity;
import gt.com.tigo.fixed_assets.security.Authorized;
import gt.com.tigo.fixed_assets.service.EmpleadosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin
@RestController
@RequestMapping("/employee")
public class EmpleadosController implements ICatalog<CpEmpleadosEntity> {
    private static final Logger logger = LoggerFactory.getLogger(EmpleadosController.class);

    @Autowired
    private EmpleadosService employeesService;

    //@Override
    @Authorized
    @GetMapping("/findAll")
    public ResponseEntity findAll() {
        logger.debug(String.format("@%s::me()", this.getClass().getName()));

        try {
            return ResponseEntity.ok(this.employeesService.findAll());
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
            return ResponseEntity.ok(this.employeesService.findAllByPage(dataTableRequestDto));
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

    @Authorized
    @GetMapping("/findByEmailColaborador")
    public ResponseEntity findByEmailColaborador(@RequestParam(name = "emailColaborador", required = true) String emailColaborador) {
        logger.debug(String.format("@%s::findByEmailColaborador(%s)", this.getClass().getName(), emailColaborador));

        try {
            return ResponseEntity.ok(this.employeesService.findByEmailColaborador(emailColaborador));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity create(CpEmpleadosEntity entity, Long requesterId) {
        return null;
    }

    @Override
    public ResponseEntity update(CpEmpleadosEntity entity, Long requesterId) {
        return null;
    }

    @Override
    public ResponseEntity delete(Long entityId, Long requesterId) {
        return null;
    }

    @Authorized
    @PostMapping("/disable/{employeeId}/{requesterId}")
    public ResponseEntity disableEmployee(@PathVariable(name = "employeeId", required = true) Long employeeId, @PathVariable(name = "requesterId", required = true) Long requesterId) {
        logger.debug(String.format("@%s::disableEmployee(%d, %d)", this.getClass().getName(), employeeId, requesterId));

        try {
            return ResponseEntity.ok(this.employeesService.disable(employeeId, requesterId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/export/xlsx")
    public ModelAndView exportAsXLSX() {
        logger.debug(String.format("@%s::exportAsXLSX()", this.getClass().getName()));

        try {
            return this.employeesService.exportAsXLSX();
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            return null;
        }
    }
}
