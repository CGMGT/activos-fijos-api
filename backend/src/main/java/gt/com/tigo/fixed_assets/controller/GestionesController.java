package gt.com.tigo.fixed_assets.controller;

import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.model.portal.DocumentosEntity;
import gt.com.tigo.fixed_assets.model.portal.GestionesEntity;
import gt.com.tigo.fixed_assets.security.Authorized;
import gt.com.tigo.fixed_assets.service.GestionesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin
@RestController
@RequestMapping("/request")
public class GestionesController implements ICatalog<GestionesEntity>{
    private static final Logger logger = LoggerFactory.getLogger(GestionesController.class);

    @Autowired
    private GestionesService requestService;

    @Override
    @Authorized
    @GetMapping("/findAll")
    public ResponseEntity findAll() {
        logger.debug(String.format("@%s::findAll()", this.getClass().getName()));

        try {
            return ResponseEntity.ok(this.requestService.findAll());
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
            return ResponseEntity.ok(this.requestService.findAllByPage(dataTableRequestDto));
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
            return ResponseEntity.ok(this.requestService.findById(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PostMapping("/create/{requesterId}")
    @Authorized
    @Override
    public ResponseEntity create(@RequestBody(required = true) GestionesEntity entity, @PathVariable(required = true)  Long requesterId) {
        logger.debug(String.format("@%s::create(%s, %d)", this.getClass().getName(), entity, requesterId));

        try {
            return ResponseEntity.ok(this.requestService.create(entity, requesterId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PutMapping("/update/{requesterId}")
    @Authorized
    @Override
    public ResponseEntity update(@RequestBody(required = true) GestionesEntity entity, @PathVariable(required = true)  Long requesterId) {
        logger.debug(String.format("@%s::update(%s, %d)", this.getClass().getName(), entity, requesterId));

        try {
            return ResponseEntity.ok(this.requestService.update(entity, requesterId));
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
            return ResponseEntity.ok(this.requestService.delete(entityId, requesterId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @PostMapping("/file/upload")
    public ResponseEntity upload(@RequestParam(name = "file", required = true) MultipartFile file, @RequestParam(required = true) Long requestDetailId, @RequestParam(required = true) Long requesterId) {
        logger.debug(String.format("@%s::upload(%s, %d, %d)", this.getClass().getName(), file.getName(), requestDetailId, requesterId));

        try {
            return ResponseEntity.ok(this.requestService.upload(file, requestDetailId, requesterId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @GetMapping("/file/download")
    public ResponseEntity download(@RequestParam(required = true) Long fileId) {
        logger.debug(String.format("@%s::download(%d)", this.getClass().getName(), fileId));

        try {
            DocumentosEntity file = this.requestService.download(fileId);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s", file.getNombreArchivo().replace(" ", "_")));
            headers.setContentType(MediaType.valueOf(file.getTipoArchivo()));
            headers.setContentLength(file.getContenido().length);

            return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_OCTET_STREAM).body(file.getContenido());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @PostMapping("/count")
    public ResponseEntity count(@RequestBody(required = true) DataTableRequestDto dataTableRequestDto) {
        logger.debug(String.format("@%s::count(%s)", this.getClass().getName(), dataTableRequestDto.toString()));

        try {
            return ResponseEntity.ok(this.requestService.count(dataTableRequestDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @PostMapping("/report")
    public ResponseEntity report(@RequestBody(required = true) DataTableRequestDto dataTableRequestDto) {
        logger.debug(String.format("@%s::report(%s)", this.getClass().getName(), dataTableRequestDto.toString()));

        try {
            return ResponseEntity.ok(this.requestService.report(dataTableRequestDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Authorized
    @PostMapping("/export/xlsx")
    public ModelAndView exportAsXLSX(@RequestBody(required = true) DataTableRequestDto dataTableRequestDto) {
        logger.debug(String.format("@%s::exportAsXLSX(%s)", this.getClass().getName(), dataTableRequestDto.toString()));

        try {
            return this.requestService.exportAsXLSX(dataTableRequestDto);
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            return null;
        }
    }

    @Authorized
    @PostMapping("/export/xlsx2")
    public ModelAndView exportXLSX(@RequestBody(required = true) DataTableRequestDto dataTableRequestDto) {
        logger.debug(String.format("@%s::exportXLSX(%s)", this.getClass().getName(), dataTableRequestDto.toString()));

        try {
            return this.requestService.exportXLSX(dataTableRequestDto);
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            return null;
        }
    }
}
