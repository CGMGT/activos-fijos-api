package gt.com.tigo.fixed_assets.controller;

import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.model.portal.TestDetailEntity;
import gt.com.tigo.fixed_assets.service.TestDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test/detail")
public class TestDetailController implements ICatalog<TestDetailEntity> {

    private static final Logger logger = LoggerFactory.getLogger(TestDetailEntity.class);

    @Autowired
    private TestDetailService detailService;

    @Override
    @GetMapping("/findAll")
    public ResponseEntity findAll() {
        logger.debug(String.format("@%s::me()", this.getClass().getName()));

        try {
            return ResponseEntity.ok(this.detailService.findAll());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity findAllByPage(DataTableRequestDto dataTableRequestDto) {
        return null;
    }

    @Override
    public ResponseEntity findById(Long id) {
        return null;
    }

    @Override
    @PostMapping("/create/{requesterId}")
    public ResponseEntity create(@RequestBody(required = true) TestDetailEntity entity, @PathVariable(required = true) Long requesterId) {
        logger.debug(String.format("@%s::create(%s, %s)", this.getClass().getName(), entity, requesterId));

        try {
            return ResponseEntity.ok(this.detailService.create(entity, requesterId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity update(TestDetailEntity entity, Long requesterId) {
        return null;
    }

    @Override
    public ResponseEntity delete(Long entityId, Long requesterId) {
        return null;
    }
}
