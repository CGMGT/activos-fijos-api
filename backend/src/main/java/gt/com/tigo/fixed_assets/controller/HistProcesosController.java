package gt.com.tigo.fixed_assets.controller;

import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.model.portal.HistProcesosEntity;
import gt.com.tigo.fixed_assets.security.Authorized;
import gt.com.tigo.fixed_assets.service.HistProcesosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/process")
public class HistProcesosController implements ICatalog<HistProcesosEntity>{
    private static final Logger logger = LoggerFactory.getLogger(HistProcesosController.class);

    @Autowired
    private HistProcesosService processService;


    @Override
    public ResponseEntity findAll() {
        return null;
    }

    @Override
    @Authorized
    @PostMapping("/findAllByPage")
    public ResponseEntity findAllByPage(@RequestBody(required = true) DataTableRequestDto dataTableRequestDto)  {
        logger.debug(String.format("@%s::findAllByPage(%s)", this.getClass().getName(), dataTableRequestDto.toString()));

        try {
            return ResponseEntity.ok(this.processService.findAllByPage(dataTableRequestDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/syncEmployeesVhur")
    public ResponseEntity syncEmployeesVhur(@RequestParam(name = "usuario", required = true) String usuario,
                                  @RequestParam(name = "descripcion", required = true) String descripcion) {
        logger.debug(String.format("@%s::syncEmployeesVhur(%s, %s)", this.getClass().getName(), usuario, descripcion));

        try {
            return ResponseEntity.ok(this.processService.executeProcess("EMPLEADOS_VHUR", usuario, descripcion));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/syncEmployeesEbs")
    public ResponseEntity syncEmployeesEbs(@RequestParam(name = "usuario", required = true) String usuario,
                                  @RequestParam(name = "descripcion", required = true) String descripcion) {
        logger.debug(String.format("@%s::syncEmployeesEbs(%s, %s)", this.getClass().getName(), usuario, descripcion));

        try {
            return ResponseEntity.ok(this.processService.executeProcess("EMPLEADOS_EBS", usuario, descripcion));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/syncFixedAssetsEbs")
    public ResponseEntity syncFixedAssetsEbs(@RequestParam(name = "usuario", required = true) String usuario,
                                           @RequestParam(name = "descripcion", required = true) String descripcion) {
        logger.debug(String.format("@%s::syncFixedAssetsEbs(%s, %s)", this.getClass().getName(), usuario, descripcion));

        try {
            return ResponseEntity.ok(this.processService.executeProcess("FIXED_ASSETS_EBS", usuario, descripcion));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/syncLocationsEbs")
    public ResponseEntity syncLocationsEbs(@RequestParam(name = "usuario", required = true) String usuario,
                                             @RequestParam(name = "descripcion", required = true) String descripcion) {
        logger.debug(String.format("@%s::syncLocationsEbs(%s, %s)", this.getClass().getName(), usuario, descripcion));

        try {
            return ResponseEntity.ok(this.processService.executeProcess("LOCATIONS_EBS", usuario, descripcion));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity findById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity create(HistProcesosEntity entity, Long requesterId) {
        return null;
    }

    @Override
    public ResponseEntity update(HistProcesosEntity entity, Long requesterId) {
        return null;
    }

    @Override
    public ResponseEntity delete(Long entityId, Long requesterId) {
        return null;
    }

    @Authorized
    @PostMapping("/report")
    public ResponseEntity report(@RequestBody(required = true) DataTableRequestDto dataTableRequestDto)  {
        logger.debug(String.format("@%s::report(%s)", this.getClass().getName(), dataTableRequestDto.toString()));

        try {
            return ResponseEntity.ok(this.processService.report(dataTableRequestDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
}
