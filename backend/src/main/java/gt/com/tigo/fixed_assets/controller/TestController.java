package gt.com.tigo.fixed_assets.controller;

import com.google.gson.Gson;
import gt.com.tigo.fixed_assets.dao.portal.TestRepository;
import gt.com.tigo.fixed_assets.dto.PaginatedDataDto;
import gt.com.tigo.fixed_assets.dto.SortInfoDto;
import gt.com.tigo.fixed_assets.model.portal.TestEntity;
import gt.com.tigo.fixed_assets.util.XlsxViewBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    private static final String FIND_ALL = "@%s::findAll()";

    @Autowired
    private TestRepository testRepo;

    @GetMapping("/findAll")
    public List<TestEntity> findAll() {
        logger.info(String.format(FIND_ALL, this.getClass().getName()));
        logger.debug(String.format(FIND_ALL, this.getClass().getName()));
        logger.error(String.format(FIND_ALL, this.getClass().getName()));
        logger.warn(String.format(FIND_ALL, this.getClass().getName()));
        logger.trace(String.format(FIND_ALL, this.getClass().getName()));

        return (List<TestEntity>) this.testRepo.findAll();

    }

    @GetMapping("/findAllByPage")
    public PaginatedDataDto<TestEntity> findAllByPage(
            @RequestParam(name = "page", required = true) Integer page,
            @RequestParam(name = "pageSize", required = true) Integer pageSize,
            @RequestParam(name = "sorted[]", required = false) String sorted,
            @RequestParam(name = "filtered[]", required = false) String filtered) {
        logger.debug(String.format("@%s::findAllByPage(%d, %d, %s, %s)", this.getClass().getName(), page, pageSize, sorted, filtered));

        Gson gson = new Gson();
        SortInfoDto sortInfo = null;

        if (sorted != null) {
            sortInfo = gson.fromJson(sorted, SortInfoDto.class);

            logger.info(sortInfo.toString());
        }

        Page<TestEntity> pageData;

        if (sorted == null) {
            pageData = this.testRepo.findAll(new PageRequest(page, pageSize, Sort.Direction.ASC, "name"));
        } else {
            pageData = this.testRepo.findAll(new PageRequest(page, pageSize, Boolean.TRUE.equals(sortInfo.getDesc()) ? Sort.Direction.DESC : Sort.Direction.ASC, sortInfo.getId()));
        }

        return new PaginatedDataDto<>(pageData.getContent(), pageData.getTotalElements(), pageData.getTotalPages());
    }

    @PostMapping("/file/upload")
    public ResponseEntity upload(@RequestParam(name = "file", required = true) MultipartFile file, @RequestParam(required = true) Long requestId, @RequestParam(required = true) Long requesterId) {
       return ResponseEntity.ok().build();
    }

    @GetMapping("/export/excel")
    public ModelAndView exportExcel() {
        String[] columns = {
                "Col1",
                "Col2",
                "Col3"
        };

        Object[] rows = {
                new String[]{"val11", "val12", "val13"},
                new String[]{"val21", "val22", "val23"},
                new String[]{"val31", "val32", "val33"}
        };

        Map<String, Object> config = new HashMap<>();
        config.put("sheetName", "Mis Datos");
        config.put("filename", "MisDatos.xlsx");
        config.put("columns", Arrays.asList(columns));
        config.put("rows", Arrays.asList(rows));

        return new ModelAndView(new XlsxViewBuilder(), config);
    }
}
