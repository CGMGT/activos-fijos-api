package gt.com.tigo.fixed_assets.service;

import gt.com.tigo.fixed_assets.controller.TestController;
import gt.com.tigo.fixed_assets.dao.portal.LocacionesRepository;
import gt.com.tigo.fixed_assets.dao.portal.UserRepository;
import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.dto.FilterInfoDto;
import gt.com.tigo.fixed_assets.dto.PaginatedDataDto;
import gt.com.tigo.fixed_assets.model.portal.AdmUsuarioEntity;
import gt.com.tigo.fixed_assets.model.portal.CpLocationsEntity;
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
import java.sql.Timestamp;
import java.util.*;

import static gt.com.tigo.fixed_assets.util.Utils.getValueAsLong;
import static gt.com.tigo.fixed_assets.util.Utils.getValueAsString;

@Service
public class LocacionesService implements ICatalog<CpLocationsEntity> {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    public static final String NOMBRE = "nombre";
    public static final String USER_ID = "userId";

    @Autowired
    private LocacionesRepository locationsRepository;

    @Autowired
    private UserRepository userRepo;

    /**
     * Returns a list of all resources in this catalog.
     * @return a list of objects containing all resources' data.
     * @throws ResourcesNotFoundException when an error occurs.
     */
    @Override
    public List<CpLocationsEntity> findAll() throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::findAll()", this.getClass().getName()));

        try {
            return (List<CpLocationsEntity>) this.locationsRepository.findAll();
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
    public PaginatedDataDto<CpLocationsEntity> findAllByPage(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::findAllByPage(%s)", this.getClass().getName(), dataTableRequestDto.toString()));

        try {
            Map<String, FilterInfoDto> filtersInfo = new HashMap<>();

            // get column names to filter
            if (dataTableRequestDto.getFiltered() != null) {
                for (FilterInfoDto filterInfo : dataTableRequestDto.getFiltered()) {
                    filtersInfo.put(filterInfo.getId(), filterInfo);
                }
            }

            // build filter specifications
            Specification idLocacionSpec = Utils.<CpLocationsEntity>getEqualSpecification("idLocacion", getValueAsLong(filtersInfo, "idLocacion"));
            Specification idSpec = Utils.<CpLocationsEntity>getEqualSpecification("id", getValueAsLong(filtersInfo, "id"));
            Specification nombreSpec = Utils.<CpLocationsEntity>getLikeSpecification(NOMBRE, getValueAsString(filtersInfo, NOMBRE));
            Specification summaryFlagSpec = Utils.<CpLocationsEntity>getLikeSpecification("summaryFlag", getValueAsString(filtersInfo, "summaryFlag"));
            Specification userSpec = Utils.<CpLocationsEntity, AdmUsuarioEntity>getEqualChildSpecification("encargado1", "id", getValueAsLong(filtersInfo, USER_ID), JoinType.LEFT);
            Specification userSpec2 = Utils.<CpLocationsEntity, AdmUsuarioEntity>getEqualChildSpecification("encargado2", "id", getValueAsLong(filtersInfo, USER_ID), JoinType.LEFT);
            Specification userSpec3 = Utils.<CpLocationsEntity, AdmUsuarioEntity>getEqualChildSpecification("encargado3", "id", getValueAsLong(filtersInfo, USER_ID), JoinType.LEFT);
            Specification userSpec4 = Utils.<CpLocationsEntity, AdmUsuarioEntity>getEqualChildSpecification("encargado4", "id", getValueAsLong(filtersInfo, USER_ID), JoinType.LEFT);
            Specification userSpec5 = Utils.<CpLocationsEntity, AdmUsuarioEntity>getEqualChildSpecification("encargado5", "id", getValueAsLong(filtersInfo, USER_ID), JoinType.LEFT);

            // put filter specifications together
            Specification filterSpec = Specifications
                    .where(Utils.getDefaultSpecification())
                    .and(idLocacionSpec)
                    .and(idSpec)
                    .and(nombreSpec)
                    .and(summaryFlagSpec)
                    .and(Specifications.where(Utils.getDefaultSpecification())
                            .and(userSpec)
                            .or(userSpec2)
                            .or(userSpec3)
                            .or(userSpec4)
                            .or(userSpec5))
                    ;








            Page<CpLocationsEntity> pageData;

            // if sort was not requested then use the default order
            if (dataTableRequestDto.getSorted() == null || dataTableRequestDto.getSorted().isEmpty()) {
                pageData = this.locationsRepository.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Sort.Direction.ASC, NOMBRE));
            } else { // else use the requested order
                pageData = this.locationsRepository.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Boolean.TRUE.equals(dataTableRequestDto.getSorted().get(0).getDesc()) ? Sort.Direction.DESC : Sort.Direction.ASC, dataTableRequestDto.getSorted().get(0).getId()));
            }

            // return a transfer object containing the rows, total rows and total pages
            return new PaginatedDataDto<>(pageData.getContent(), pageData.getTotalElements(), pageData.getTotalPages());
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourcesNotFoundException();
        }
    }

    /**
     * Returns a list of locations for a specific user.
     * @param userId The user unique identifier.
     * @return A list of locations for the user.
     * @throws ResourcesNotFoundException When no data is found.
     */
    public List<CpLocationsEntity> getLocationsByUserId(Long userId) throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::getLocationsByUserId(%d)", this.getClass().getName(), userId));

        List<CpLocationsEntity> locations = this.locationsRepository.getAllByUserId(userId);

        if (locations == null) {
            throw new ResourcesNotFoundException();
        }

        return locations;
    }

    public CpLocationsEntity getDefaultWarehouse() throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::getDefaultWarehouse()", this.getClass().getName()));

        CpLocationsEntity locations = this.locationsRepository.findByNombre(this.locationsRepository.getDefaultWarehouseName());

        if (locations == null) {
            throw new ResourcesNotFoundException();
        }

        return locations;
    }

    /**
     * Returns a resource by id.
     * @param id The id of the requested resource
     * @return an object containing the found resource.
     * @throws ResourceNotFoundException when no record is found.
     */
    @Override
    public CpLocationsEntity findById(Long id) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findById(%d)", this.getClass().getName(), id));

        CpLocationsEntity entity = this.locationsRepository.findOne(id);

        if (entity == null) {
            throw new ResourceNotFoundException();
        }

        return entity;
    }

    @Override
    public CpLocationsEntity create(CpLocationsEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceCreateException {
        return null;
    }

    /**
     * Updates an existing resource.
     * @param entity an object containing the updated resource's data.
     * @param requesterId the requester user id.
     * @return an object containing the updated resource.
     * @throws RequesterNotFoundException when requester is not found.
     * @throws ResourceUpdateException when the resource could not be updated.
     */
    @Override
    public CpLocationsEntity update(CpLocationsEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceUpdateException {
        logger.debug(String.format("@%s::update(%s, %d)", this.getClass().getName(), entity, requesterId));

        AdmUsuarioEntity requester = this.userRepo.findOne(requesterId);

        if (requester == null) {
            throw new RequesterNotFoundException();
        }

        entity.setUsuarioModificacion(requester.getUsuario());
        entity.setFechaModificacion(new Timestamp(new Date().getTime()));

        try {
            return this.locationsRepository.save(entity);
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourceUpdateException();
        }
    }

    @Override
    public CpLocationsEntity delete(Long entityId, Long requesterId) throws RequesterNotFoundException, ResourceNotFoundException, ResourceUpdateException {
        return null;
    }

    public ModelAndView exportAsXLSX() throws ResourcesNotFoundException {
        List<CpLocationsEntity> entities = this.locationsRepository.findAll();

        if (entities == null) {
            throw new ResourcesNotFoundException();
        }

        String[] columns = {
                "ID",
                "ID LOCACION",
                "NOMBRE",
                "ORIG SYSTEM REFERENCE",
                "CÓDIGO PAÍS",
                "DIRECCIÓN 1",
                "DIRECCIÓN 2",
                "DIRECCIÓN 3",
                "DIRECCIÓN 4",
                "CIUDAD",
                "CÓDIGO POSTAL",
                "ESTADO",
                "PROVINCIA",
                "PAÍS",
                "ADDRESS KEY",
                "FA LOCATION CODE"
        };

        List<Object> rows = new ArrayList<>();

        for (CpLocationsEntity entity : entities) {
            List<String> values = new ArrayList<>();

            values.add(String.valueOf(entity.getId()));
            values.add(String.valueOf(entity.getIdLocacion()));
            values.add(entity.getNombre());
            values.add(entity.getOrigSystemReference());
            values.add(entity.getCountry());
            values.add(entity.getAddress1());
            values.add(entity.getAddress2());
            values.add(entity.getAddress3());
            values.add(entity.getAddress4());
            values.add(entity.getCity());
            values.add(entity.getPostalCode());
            values.add(entity.getState());
            values.add(entity.getProvince());
            values.add(entity.getCounty());
            values.add(entity.getAddressKey());
            values.add(entity.getAttribute1());

            rows.add(values.toArray());
        }

        Map<String, Object> config = new HashMap<>();
        config.put("sheetName", "Locaciones");
        config.put("filename", "Locaciones.xlsx");
        config.put("columns", Arrays.asList(columns));
        config.put("rows", rows);

        return new ModelAndView(new XlsxViewBuilder(), config);
    }
}
