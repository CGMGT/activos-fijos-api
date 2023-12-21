package gt.com.tigo.fixed_assets.controller;

import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.model.portal.TestMasterEntity;
import gt.com.tigo.fixed_assets.service.TestMasterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test/master/")
public class TestMasterController implements ICatalog<TestMasterEntity> {

    private static final Logger logger = LoggerFactory.getLogger(TestMasterController.class);

    @Autowired
    private TestMasterService masterService;

    @Override
    @GetMapping("/findAll")
    public ResponseEntity findAll() {
        logger.debug(String.format("@%s::me()", this.getClass().getName()));

        try {
            return ResponseEntity.ok(this.masterService.findAll());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    @PostMapping("/findAllByPage")
    public ResponseEntity findAllByPage(@RequestBody(required = true) DataTableRequestDto dataTableRequestDto) {
        logger.debug(String.format("@%s::findAllByPage(%s)", this.getClass().getName(), dataTableRequestDto.toString()));

        try {
            return ResponseEntity.ok(this.masterService.findAllByPage(dataTableRequestDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity findById(Long id) {
        return null;
    }

    @Override
    @PostMapping("/create/{requesterId}")
    public ResponseEntity create(@RequestBody(required = true) TestMasterEntity entity, @PathVariable(required = true) Long requesterId) {
        logger.debug(String.format("@%s::create(%s, %s)", this.getClass().getName(), entity, requesterId));

        try {
            return ResponseEntity.ok(this.masterService.create(entity, requesterId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    @PutMapping("/update/{requesterId}")
    public ResponseEntity update(@RequestBody(required = true) TestMasterEntity entity, @PathVariable(required = true) Long requesterId) {
        logger.debug(String.format("@%s::update(%s, %s)", this.getClass().getName(), entity, requesterId));

        try {
            return ResponseEntity.ok(this.masterService.update(entity, requesterId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity delete(Long entityId, Long requesterId) {
        return null;
    }
}
