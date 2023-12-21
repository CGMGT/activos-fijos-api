package gt.com.tigo.fixed_assets.service;

import gt.com.tigo.fixed_assets.dao.portal.ActivosFijosRepository;
import gt.com.tigo.fixed_assets.dao.portal.EmpleadosEbsRepository;
import gt.com.tigo.fixed_assets.dao.portal.UserRepository;
import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.dto.FilterInfoDto;
import gt.com.tigo.fixed_assets.dto.PaginatedDataDto;
import gt.com.tigo.fixed_assets.model.portal.*;
import gt.com.tigo.fixed_assets.util.Utils;
import gt.com.tigo.fixed_assets.util.XlsxViewBuilder;
import gt.com.tigo.fixed_assets.util.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.JoinType;
import java.text.SimpleDateFormat;
import java.util.*;

import static gt.com.tigo.fixed_assets.util.Utils.*;

@Service
public class ActivosFijosService implements ICatalog<CpActivosFijosEntity> {
    private static final Logger logger = LoggerFactory.getLogger(ActivosFijosService.class);
    public static final String ASSET_NUMBER = "assetNumber";
    public static final String QUERY = "query";
    public static final String SERIAL_NUMBER = "serialNumber";
    public static final String MODEL_NUMBER = "modelNumber";
    public static final String TAG_NUMBER = "tagNumber";
    public static final String LEGACYCODE = "legacycode";
    public static final String DESCRIPTION = "description";
    public static final String EMPLOYEE_NUMBER = "employeeNumber";
    public static final String LOCKED = "locked";
    public static final String LOCACION = "locacion";
    public static final String WAREHOUSE_USER_ID = "warehouseUserId";


    @Autowired
    private ActivosFijosRepository fixedAssetRepository;

    @Autowired
    private EmpleadosEbsRepository empleadosEbsRepo;

    @Autowired
    private UserRepository userRepo;

    /**
     * Returns a list of all resources in this catalog.
     * @return a list of objects containing all resources' data.
     * @throws ResourcesNotFoundException when an error occurs.
     */
    @Override
    public List<CpActivosFijosEntity> findAll() throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::findAll()", this.getClass().getName()));

        try {
            return (List<CpActivosFijosEntity>) this.fixedAssetRepository.findAll();
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourcesNotFoundException();
        }
    }

    /**
     * Returns a list of all resources in this catalog by page and page size.
     * @param dataTableRequestDto page, sort and filter data.
     * @return a list of objects containing all resources' data.
     * @throws ResourcesNotFoundException when an error occurs.
     */
    @Override
    public PaginatedDataDto<CpActivosFijosEntity> findAllByPage(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::findAllByPage(%s)", this.getClass().getName(), dataTableRequestDto.toString()));

        try {
            Map<String, FilterInfoDto> filtersInfo = new HashMap<>();

            // get column names to filter
            if (dataTableRequestDto.getFiltered() != null) {
                for (FilterInfoDto filterInfo : dataTableRequestDto.getFiltered()) {
                    filtersInfo.put(filterInfo.getId(), filterInfo);
                }
            }

            // put filter specifications together
            Specification filterSpec;

            if (dataTableRequestDto.getOperator() != null && dataTableRequestDto.getOperator().equals("OR")) {
                // build filter specifications
                Specification assetNumberSpec = null;
                try {
                    assetNumberSpec = Utils.<CpActivosFijosEntity>getEqualSpecification(ASSET_NUMBER, getValueAsLong(filtersInfo, QUERY));
                } catch (NumberFormatException nfe) {
                    logger.error(nfe.getMessage());
                }
                Specification serialNumberSpec = Utils.<CpActivosFijosEntity>getLikeSpecification(SERIAL_NUMBER, getValueAsString(filtersInfo, QUERY));
                Specification modelNumberSpec = Utils.<CpActivosFijosEntity>getLikeSpecification(MODEL_NUMBER, getValueAsString(filtersInfo, QUERY));
                Specification tagNumberSpec = Utils.<CpActivosFijosEntity>getLikeSpecification(TAG_NUMBER, getValueAsString(filtersInfo, QUERY));
                Specification legacyCodeSpec = Utils.<CpActivosFijosEntity>getLikeSpecification(LEGACYCODE, getValueAsString(filtersInfo, QUERY));
                Specification statusSpec = Utils.<CpActivosFijosEntity>getEqualSpecification("estado", "A");

                if (assetNumberSpec == null) {
                    filterSpec = Specifications.where(serialNumberSpec)
                            .or(modelNumberSpec)
                            .or(tagNumberSpec)
                            .or(legacyCodeSpec)
                            .and(statusSpec);
                }
                else {
                    filterSpec = Specifications.where(assetNumberSpec)
                            .or(serialNumberSpec)
                            .or(modelNumberSpec)
                            .or(tagNumberSpec)
                            .or(legacyCodeSpec)
                            .and(statusSpec);
                }
            } else {
                // build filter specifications
                Specification assetNumberSpec = Utils.<CpActivosFijosEntity>getEqualSpecification(ASSET_NUMBER, getValueAsLong(filtersInfo, ASSET_NUMBER));
                Specification statusSpec = Utils.<CpActivosFijosEntity>getEqualSpecification("estado", "A");
                Specification descriptionSpec = Utils.<CpActivosFijosEntity>getLikeSpecification(DESCRIPTION, getValueAsString(filtersInfo, DESCRIPTION));
                Specification serialNumberSpec = Utils.<CpActivosFijosEntity>getLikeSpecification(SERIAL_NUMBER, getValueAsString(filtersInfo, SERIAL_NUMBER));
                Specification modelNumberSpec = Utils.<CpActivosFijosEntity>getLikeSpecification(MODEL_NUMBER, getValueAsString(filtersInfo, MODEL_NUMBER));
                Specification employeeNumberSpec = Utils.<CpActivosFijosEntity>getEqualSpecification(EMPLOYEE_NUMBER, getValueAsLong(filtersInfo, EMPLOYEE_NUMBER));
                Specification tagNumberSpec = Utils.<CpActivosFijosEntity>getLikeSpecification(TAG_NUMBER, getValueAsString(filtersInfo, TAG_NUMBER));
                Specification legacyCodeSpec = Utils.<CpActivosFijosEntity>getLikeSpecification(LEGACYCODE, getValueAsString(filtersInfo, "legacyCode"));
                Specification locationSpec = Utils.<CpActivosFijosEntity, CpLocationsEntity>getEqualChildSpecification(LOCACION, "id", getValueAsLong(filtersInfo, "locationId"));
                Specification lockedSpec = Utils.<CpActivosFijosEntity>getEqualSpecification(LOCKED, getValueAsLong(filtersInfo, LOCKED));
                Specification userSpec = Utils.<CpActivosFijosEntity, CpLocationsEntity>getEqualChildSpecification(LOCACION, "encargado1", getValueAsLong(filtersInfo, WAREHOUSE_USER_ID), JoinType.LEFT);
                Specification userSpec2 = Utils.<CpActivosFijosEntity, CpLocationsEntity>getEqualChildSpecification(LOCACION, "encargado2", getValueAsLong(filtersInfo, WAREHOUSE_USER_ID), JoinType.LEFT);
                Specification userSpec3 = Utils.<CpActivosFijosEntity, CpLocationsEntity>getEqualChildSpecification(LOCACION, "encargado3", getValueAsLong(filtersInfo, WAREHOUSE_USER_ID), JoinType.LEFT);
                Specification userSpec4 = Utils.<CpActivosFijosEntity, CpLocationsEntity>getEqualChildSpecification(LOCACION, "encargado4", getValueAsLong(filtersInfo, WAREHOUSE_USER_ID), JoinType.LEFT);
                Specification userSpec5 = Utils.<CpActivosFijosEntity, CpLocationsEntity>getEqualChildSpecification(LOCACION, "encargado5", getValueAsLong(filtersInfo, WAREHOUSE_USER_ID), JoinType.LEFT);

                filterSpec = Specifications.where(getDefaultSpecification())
                        .and(assetNumberSpec)
                        .and(statusSpec)
                        .and(descriptionSpec)
                        .and(serialNumberSpec)
                        .and(employeeNumberSpec)
                        .and(modelNumberSpec)
                        .and(tagNumberSpec)
                        .and(legacyCodeSpec)
                        .and(locationSpec)
                        .and(lockedSpec)
                        .and(Specifications.where(Utils.getDefaultSpecification())
                                .and(userSpec)
                                .or(userSpec2)
                                .or(userSpec3)
                                .or(userSpec4)
                                .or(userSpec5));
            }

            Page<CpActivosFijosEntity> pageData;

            // if sort was not requested then use the default order
            if (dataTableRequestDto.getSorted() == null || dataTableRequestDto.getSorted().isEmpty()) {
                pageData = this.fixedAssetRepository.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Sort.Direction.ASC, DESCRIPTION));
            } else { // else use the requested order
                pageData = this.fixedAssetRepository.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Boolean.TRUE.equals(dataTableRequestDto.getSorted().get(0).getDesc()) ? Sort.Direction.DESC : Sort.Direction.ASC, dataTableRequestDto.getSorted().get(0).getId()));
            }

            // return a transfer object containing the rows, total rows and total pages
            return new PaginatedDataDto<>(pageData.getContent(), pageData.getTotalElements(), pageData.getTotalPages());
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourcesNotFoundException();
        }
    }

    /**
     * Returns a list of all resources in this catalog by page and page size.
     * @param dataTableRequestDto page, sort and filter data.
     * @return a list of objects containing all resources' data.
     * @throws ResourcesNotFoundException when an error occurs.
     */
    public PaginatedDataDto<CpActivosFijosEntity> findAllByPageByUserId(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException, RequesterNotFoundException {
        logger.debug(String.format("@%s::findAllByPageByUserId(%s)", this.getClass().getName(), dataTableRequestDto.toString()));

        // convert filters list to a map
        Map<String, FilterInfoDto> filters = getFiltersAsMap(dataTableRequestDto.getFiltered());

        Long userId = getValueAsLong(filters, "userId");

        if (userId == null) {
            throw new RequesterNotFoundException();
        }

        AdmUsuarioEntity user = this.userRepo.findOne(userId);

        if (user == null) {
            throw new RequesterNotFoundException();
        }

        CpEmpleadosEbsEntity ebsEmployee = this.empleadosEbsRepo.findByEmailAddress(user.getCorreoElectronico());

        if (ebsEmployee == null) {
            throw new RequesterNotFoundException();
        }

        //build the employee number filter
        FilterInfoDto employeeNumberFilter = new FilterInfoDto();
        employeeNumberFilter.setId(EMPLOYEE_NUMBER);
        employeeNumberFilter.setValue(ebsEmployee.getEmployeeNumber());

        //push the employee number filter
        dataTableRequestDto.getFiltered().add(employeeNumberFilter);

        return this.findAllByPage(dataTableRequestDto);
    }

    /**
     * Returns a resource by id.
     * @param id The id of the requested resource
     * @return an object containing the found resource.
     * @throws ResourceNotFoundException when no record is found.
     */
    @Override
    public CpActivosFijosEntity findById(Long id) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findById(%d)", this.getClass().getName(), id));

        CpActivosFijosEntity user = this.fixedAssetRepository.findOne(id);

        if (user == null) {
            throw new ResourceNotFoundException();
        }

        return user;
    }

    /**
     * Returns a resource by serial number.
     * @param serialNumber The serial number of the requested resource.
     * @return an object containing the found resource.
     * @throws ResourceNotFoundException when no record is found.
     */
    public CpActivosFijosEntity findBySerialNumber(String serialNumber) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findBySerialNumber(%s)", this.getClass().getName(), serialNumber));

        CpActivosFijosEntity user = this.fixedAssetRepository.findBySerialNumber(serialNumber);

        if (user == null) {
            throw new ResourceNotFoundException();
        }

        return user;
    }

    /**
     * Returns a resource by serial number.
     * @param modelNumber The model number of the requested resource.
     * @return an object containing the found resource.
     * @throws ResourceNotFoundException when no record is found.
     */
    public CpActivosFijosEntity findByModelNumber(String modelNumber) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findByModelNumber(%s)", this.getClass().getName(), modelNumber));

        CpActivosFijosEntity user = this.fixedAssetRepository.findByModelNumber(modelNumber);

        if (user == null) {
            throw new ResourceNotFoundException();
        }

        return user;
    }

    /**
     * Returns a resource by serial number.
     * @param legacyCode The legacy code of the requested resource.
     * @return an object containing the found resource.
     * @throws ResourceNotFoundException when no record is found.
     */
    public CpActivosFijosEntity findByLegacyCode(String legacyCode) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findByLegacyCode(%s)", this.getClass().getName(), legacyCode));

        CpActivosFijosEntity user = this.fixedAssetRepository.findByLegacycode(legacyCode);

        if (user == null) {
            throw new ResourceNotFoundException();
        }

        return user;
    }

    /**
     * Returns a resource by serial number.
     * @param tagNumber The tag number of the requested resource.
     * @return an object containing the found resource.
     * @throws ResourceNotFoundException when no record is found.
     */
    public CpActivosFijosEntity findByTagNumber(String tagNumber) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findByTagNumber(%s)", this.getClass().getName(), tagNumber));

        CpActivosFijosEntity user = this.fixedAssetRepository.findByTagNumber(tagNumber);

        if (user == null) {
            throw new ResourceNotFoundException();
        }

        return user;
    }

    /**
     * Returns a resource by serial number.
     * @param assetNumber The asset number of the requested resource.
     * @return an object containing the found resource.
     * @throws ResourceNotFoundException when no record is found.
     */
    public CpActivosFijosEntity findByAssetNumber(Long assetNumber) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findByAssetNumber(%d)", this.getClass().getName(), assetNumber));

        CpActivosFijosEntity user = this.fixedAssetRepository.findByAssetNumber(assetNumber);

        if (user == null) {
            throw new ResourceNotFoundException();
        }

        return user;
    }

    public String findRemoveFaMessage() throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findRemoveFaMessage()", this.getClass().getName()));

        String message = this.fixedAssetRepository.getMensajeNotaBaja();

        if (message == null) {
            throw new ResourceNotFoundException();
        }

        return message;
    }

    @Override
    public CpActivosFijosEntity create(CpActivosFijosEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceCreateException {
        return null;
    }

    @Override
    public CpActivosFijosEntity update(CpActivosFijosEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceUpdateException {
        return null;
    }

    @Override
    public CpActivosFijosEntity delete(Long entityId, Long requesterId) throws RequesterNotFoundException, ResourceNotFoundException, ResourceUpdateException {
        return null;
    }

    public ModelAndView exportAsXLSX() throws ResourcesNotFoundException {
        List<CpActivosFijosEntity> entities = this.fixedAssetRepository.findAll();

        if (entities == null) {
            throw new ResourcesNotFoundException();
        }

        String[] columns = {
                "ID",
                "NÚMERO ACTIVO FIJO",
                "DESCRIPCIÓN",
                "ETIQUETA",
                "CÓDIGO LEGADO",
                "MODELO",
                "NÚMERO SERIE",
                "TIPO",
                "CÓDIGO CATEGORÍA",
                "CÓDIGO TIPO LIBRO",
                "FECHA INGRESO",
                "COSTO",
                "COMBINACIÓN",
                "CUENTA DE GASTO",
                "CUENTA DE ACTIVO",
                "CÓDIGO COLABORADOR",
                "NOMBRE COLABORADOR",
                "ESTADO"
        };

        List<Object> rows = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        for (CpActivosFijosEntity entity : entities) {
            List<String> values = new ArrayList<>();

            values.add(String.valueOf(entity.getId()));
            values.add(String.valueOf(entity.getAssetNumber()));
            values.add(entity.getDescription());
            values.add(entity.getTagNumber());
            values.add(entity.getLegacycode());
            values.add(entity.getModelNumber());
            values.add(entity.getSerialNumber());
            values.add(entity.getAssetType());
            values.add(entity.getCategoryCode());
            values.add(entity.getBookTypeCode());
            values.add(entity.getDateIn() == null ? "" : sdf.format(entity.getDateIn()));
            values.add(String.valueOf(entity.getCost()));
            values.add(String.valueOf(entity.getCombination()));
            values.add(entity.getExpenseAccount());
            values.add(entity.getAssetAccount());
            values.add(String.valueOf(entity.getEmployeeNumber()));
            values.add(entity.getEmployeeName());
            values.add(entity.getEstado());
            rows.add(values.toArray());
        }

        Map<String, Object> config = new HashMap<>();
        config.put("sheetName", "Activos Fijos");
        config.put("filename", "ActivosFijos.xlsx");
        config.put("columns", Arrays.asList(columns));
        config.put("rows", rows);

        return new ModelAndView(new XlsxViewBuilder(), config);
    }
}
