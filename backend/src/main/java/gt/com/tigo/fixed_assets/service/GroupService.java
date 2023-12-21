package gt.com.tigo.fixed_assets.service;

import gt.com.tigo.fixed_assets.dao.portal.GroupRepository;
import gt.com.tigo.fixed_assets.dao.portal.ParameterRepository;
import gt.com.tigo.fixed_assets.dao.portal.UserRepository;
import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.dto.FilterInfoDto;
import gt.com.tigo.fixed_assets.dto.PaginatedDataDto;
import gt.com.tigo.fixed_assets.model.portal.*;
import gt.com.tigo.fixed_assets.util.Utils;
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

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static gt.com.tigo.fixed_assets.util.Utils.*;

@Service
public class GroupService implements ICatalog<AdmGrupoEntity> {

    private static final Logger logger = LoggerFactory.getLogger(GroupService.class);
    public static final String ID = "id";
    public static final String NOMBRE = "nombre";
    public static final String DESCRIPCION = "descripcion";
    public static final String ESTADO = "estado";

    @Autowired
    private GroupRepository groupRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ParameterRepository parameterRepository;

    /**
     * Returns a list of all resources in this catalog.
     * @return a list of objects containing all resources' data.
     * @throws ResourcesNotFoundException when an error occurs.
     */
    @Override
    public List<AdmGrupoEntity> findAll() throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::findAll()", this.getClass().getName()));

        try {
            return this.groupRepo.findAll();
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
    public PaginatedDataDto<AdmGrupoEntity> findAllByPage(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
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
            Specification idSpec = Utils.<AdmGrupoEntity>getEqualSpecification(ID, getValueAsLong(filtersInfo, ID));
            Specification nameSpec = Utils.<AdmGrupoEntity>getLikeSpecification(NOMBRE, getValueAsString(filtersInfo, NOMBRE));
            Specification descriptionSpec = Utils.<AdmGrupoEntity>getLikeSpecification(DESCRIPCION, getValueAsString(filtersInfo, DESCRIPCION));
            Specification statusSpec = Utils.<AdmGrupoEntity>getEqualSpecification(ESTADO, getValueAsString(filtersInfo, ESTADO));

            // put filter specifications together
            Specification filterSpec = Specifications
                    .where(getDefaultSpecification())
                    .and(idSpec)
                    .and(nameSpec)
                    .and(descriptionSpec)
                    .and(statusSpec)
                    ;

            Page<AdmGrupoEntity> pageData;

            // if sort was not requested then use the default order
            if (dataTableRequestDto.getSorted() == null || dataTableRequestDto.getSorted().isEmpty()) {
                pageData = this.groupRepo.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Sort.Direction.ASC, NOMBRE));
            } else { // else use the requested order
                pageData = this.groupRepo.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Boolean.TRUE.equals(dataTableRequestDto.getSorted().get(0).getDesc()) ? Sort.Direction.DESC : Sort.Direction.ASC, dataTableRequestDto.getSorted().get(0).getId()));
            }

            // return a transfer object containing the rows, total rows and total pages
            return new PaginatedDataDto<>(pageData.getContent(), pageData.getTotalElements(), pageData.getTotalPages());
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourcesNotFoundException();
        }
    }

    @Override
    public AdmGrupoEntity findById(Long id) throws ResourceNotFoundException {
        return null;
    }

    public AdmGrupoEntity findByNombre(String nombre) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findByNombre(%s)", this.getClass().getName(), nombre));

        AdmGrupoEntity entity = this.groupRepo.findByNombreAndEstado(nombre, "A");

        if (entity == null) {
            throw new ResourceNotFoundException();
        }

        return entity;
    }

    public AdmGrupoEntity findDefault() throws ResourceNotFoundException {
        logger.debug(String.format("%s::findDefault()", this.getClass().getName()));

        try {
            AdmParametrosEntity parameter = this.parameterRepository.findByNombre("USER_DEFAULT_GROUP");

            return this.findByNombre(parameter.getValor());
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourceNotFoundException();
        }
    }

    /**
     * Saves a new resource.
     * @param entity an object containing the new resource's data
     * @param requesterId the requester user id.
     * @return an object containing the new resource.
     * @throws RequesterNotFoundException when the requester is not found.
     * @throws ResourceCreateException when the resource could not be saved.
     */
    @Override
    public AdmGrupoEntity create(AdmGrupoEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceCreateException {
        logger.debug(String.format("@%s::create(%s, %d)", this.getClass().getName(), entity, requesterId));

        AdmUsuarioEntity requester = this.userRepo.findOne(requesterId);

        if (requester == null) {
            throw new RequesterNotFoundException();
        }

        entity.setEstado("A");
        entity.setEliminable("S");
        entity.setModificable("S");
        entity.setUsuarioCreacion(requester.getUsuario());
        entity.setFechaCreacion(new Timestamp(new Date().getTime()));

        try {
            return this.groupRepo.save(entity);
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourceCreateException();
        }
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
    public AdmGrupoEntity update(AdmGrupoEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceUpdateException {
        logger.debug(String.format("@%s::update(%s, %d)", this.getClass().getName(), entity, requesterId));

        AdmUsuarioEntity requester = this.userRepo.findOne(requesterId);

        if (requester == null) {
            throw new RequesterNotFoundException();
        }

        entity.setUsuarioModificacion(requester.getUsuario());
        entity.setFechaModificacion(new Timestamp(new Date().getTime()));

        try {
            return this.groupRepo.save(entity);
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourceUpdateException();
        }
    }

    /**
     * Deletes an existing resource.
     * @param entityId the id of the resource to delete
     * @param requesterId the requester user id.
     * @return an object containing the deleted resrouce.
     * @throws RequesterNotFoundException when requester is not found.
     * @throws ResourceNotFoundException when no record is found.
     * @throws ResourceUpdateException when the resource could not be updated.
     */
    @Override
    public AdmGrupoEntity delete(Long entityId, Long requesterId) throws RequesterNotFoundException, ResourceNotFoundException, ResourceUpdateException {
        logger.debug(String.format("@%s::delete(%d, %d)", this.getClass().getName(), entityId, requesterId));

        AdmUsuarioEntity requester = this.userRepo.findOne(requesterId);

        if (requester == null) {
            throw new RequesterNotFoundException();
        }

        AdmGrupoEntity group = this.groupRepo.findOne(entityId);

        if (group == null) {
            throw new ResourceNotFoundException();
        }

        group.setEstado("I");
        group.setUsuarioModificacion(requester.getUsuario());
        group.setFechaModificacion(new Timestamp(new Date().getTime()));

        try {
            return this.groupRepo.save(group);
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourceUpdateException();
        }
    }
}
