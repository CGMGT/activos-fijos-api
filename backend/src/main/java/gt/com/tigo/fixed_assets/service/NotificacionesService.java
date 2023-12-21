package gt.com.tigo.fixed_assets.service;

import gt.com.tigo.fixed_assets.dao.portal.*;
import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.dto.FilterInfoDto;
import gt.com.tigo.fixed_assets.dto.PaginatedDataDto;
import gt.com.tigo.fixed_assets.dto.TreeNode;
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
import java.util.*;

import static gt.com.tigo.fixed_assets.util.Utils.*;

@Service
public class NotificacionesService implements ICatalog<NotificacionesEntity> {
    private static final Logger logger = LoggerFactory.getLogger(NotificacionesService.class);
    public static final String UNIDAD_NEGOCIO = "unidadNegocio";
    public static final String AREA_FUNCIONAL = "areaFuncional";
    public static final String UNIDAD_ADMINISTRATIVA = "unidadAdministrativa";

    @Autowired
    private VAreaFuncionalRepository functionalAreaRepository;

    @Autowired
    private VUnidadAdministrativaRepository adminUnitRepository;

    @Autowired
    private VUnidadNegocioRepository businessUnitRepository;

    @Autowired
    private NotificacionesRepository notificationsRepository;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private EmpleadosRepository employeeRepo;

    @Autowired
    private ParameterRepository paramRepo;

    /**
     * Returns a list of nodes to build area tree.
     * @return a List of TreeNodes.
     */
    public List<TreeNode> getAreaTree() {
        List<TreeNode> treeNodes = new LinkedList<>();

        List<String> businessUnits = this.employeeRepo.getBusinessUnits();

        for (String businessUnit : businessUnits) {
            String businessUnitId = UUID.randomUUID().toString();

            TreeNode businessUnitNode = new TreeNode(businessUnitId, businessUnit);

            List<String> functionalAreas = this.employeeRepo.getFunctionalAreaByBusinessUnit(businessUnit);

            for (String functionalArea : functionalAreas) {
                String functionalAreaId = UUID.randomUUID().toString();

                TreeNode functionalAreaNode = new TreeNode(functionalAreaId, businessUnitId, functionalArea);

                List<String> adminAreas = this.employeeRepo.getAdministrativeUnitByFunctionalArea(businessUnit, functionalArea);

                for (String adminArea : adminAreas) {
                    String adminAreaId = UUID.randomUUID().toString();

                    TreeNode adminAreaNode = new TreeNode(adminAreaId, functionalAreaId, adminArea);

                    functionalAreaNode.getChildren().add(adminAreaNode);
                }

                businessUnitNode.getChildren().add(functionalAreaNode);
            }

            treeNodes.add(businessUnitNode);
        }

        return treeNodes;
    }

    /**
     * Returns a list of all resources in this catalog by page and page size.
     * @param dataTableRequestDto page, sort and filter data.
     * @return a list of objects containing all resources' data.
     * @throws ResourcesNotFoundException when an error occurs.
     */
    public PaginatedDataDto<VUnidadNegocioEntity> findUnidadNegocioByPage(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::findUnidadNegocioByPage(%s)", this.getClass().getName(), dataTableRequestDto.toString()));

        try {
            Map<String, FilterInfoDto> filtersInfo = new HashMap<>();

            // get column names to filter
            if (dataTableRequestDto.getFiltered() != null) {
                for (FilterInfoDto filterInfo : dataTableRequestDto.getFiltered()) {
                    filtersInfo.put(filterInfo.getId(), filterInfo);
                }
            }

            // build filter specifications
            Specification unidadNegocioSpec = Utils.<VUnidadNegocioEntity>getEqualSpecification(UNIDAD_NEGOCIO, getValueAsString(filtersInfo, UNIDAD_NEGOCIO));

            // put filter specifications together
            Specification filterSpec = Specifications
                    .where(getDefaultSpecification())
                    .and(unidadNegocioSpec)
                    ;

            Page<VUnidadNegocioEntity> pageData;

            // if sort was not requested then use the default order
            if (dataTableRequestDto.getSorted() == null || dataTableRequestDto.getSorted().isEmpty()) {
                pageData = this.businessUnitRepository.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Sort.Direction.ASC, UNIDAD_NEGOCIO));
            } else { // else use the requested order
                pageData = this.businessUnitRepository.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Boolean.TRUE.equals(dataTableRequestDto.getSorted().get(0).getDesc()) ? Sort.Direction.DESC : Sort.Direction.ASC, dataTableRequestDto.getSorted().get(0).getId()));
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
    public PaginatedDataDto<VAreaFuncionalEntity> findAreaFuncionalByPage(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::findAreaFuncionalByPage(%s)", this.getClass().getName(), dataTableRequestDto.toString()));

        try {
            Map<String, FilterInfoDto> filtersInfo = new HashMap<>();

            // get column names to filter
            if (dataTableRequestDto.getFiltered() != null) {
                for (FilterInfoDto filterInfo : dataTableRequestDto.getFiltered()) {
                    filtersInfo.put(filterInfo.getId(), filterInfo);
                }
            }

            // build filter specifications
            Specification unidadNegocioSpec = Utils.<VAreaFuncionalEntity>getEqualSpecification(UNIDAD_NEGOCIO, getValueAsString(filtersInfo, UNIDAD_NEGOCIO));
            Specification areaFuncionalSpec = Utils.<VAreaFuncionalEntity>getEqualSpecification(AREA_FUNCIONAL, getValueAsString(filtersInfo, AREA_FUNCIONAL));

            // put filter specifications together
            Specification filterSpec = Specifications
                    .where(getDefaultSpecification())
                    .and(unidadNegocioSpec)
                    .and(areaFuncionalSpec)
                    ;

            Page<VAreaFuncionalEntity> pageData;

            // if sort was not requested then use the default order
            if (dataTableRequestDto.getSorted() == null || dataTableRequestDto.getSorted().isEmpty()) {
                pageData = this.functionalAreaRepository.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Sort.Direction.ASC, AREA_FUNCIONAL));
            } else { // else use the requested order
                pageData = this.functionalAreaRepository.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Boolean.TRUE.equals(dataTableRequestDto.getSorted().get(0).getDesc()) ? Sort.Direction.DESC : Sort.Direction.ASC, dataTableRequestDto.getSorted().get(0).getId()));
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
    public PaginatedDataDto<VUnidadAdministrativaEntity> findUnidadAdministrativaByPage(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::findUnidadAdministrativaByPage(%s)", this.getClass().getName(), dataTableRequestDto.toString()));

        try {
            Map<String, FilterInfoDto> filtersInfo = new HashMap<>();

            // get column names to filter
            if (dataTableRequestDto.getFiltered() != null) {
                for (FilterInfoDto filterInfo : dataTableRequestDto.getFiltered()) {
                    filtersInfo.put(filterInfo.getId(), filterInfo);
                }
            }

            // build filter specifications
            Specification unidadNegocioSpec = Utils.<VUnidadAdministrativaEntity>getEqualSpecification(UNIDAD_NEGOCIO, getValueAsString(filtersInfo, UNIDAD_NEGOCIO));
            Specification areaFuncionalSpec = Utils.<VUnidadAdministrativaEntity>getEqualSpecification(AREA_FUNCIONAL, getValueAsString(filtersInfo, AREA_FUNCIONAL));
            Specification unidadAdministrativaSpec = Utils.<VUnidadAdministrativaEntity>getEqualSpecification(UNIDAD_ADMINISTRATIVA, getValueAsString(filtersInfo, UNIDAD_ADMINISTRATIVA));

            // put filter specifications together
            Specification filterSpec = Specifications
                    .where(getDefaultSpecification())
                    .and(unidadNegocioSpec)
                    .and(areaFuncionalSpec)
                    .and(unidadAdministrativaSpec)
                    ;

            Page<VUnidadAdministrativaEntity> pageData;

            // if sort was not requested then use the default order
            if (dataTableRequestDto.getSorted() == null || dataTableRequestDto.getSorted().isEmpty()) {
                pageData = this.adminUnitRepository.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Sort.Direction.ASC, UNIDAD_ADMINISTRATIVA));
            } else { // else use the requested order
                pageData = this.adminUnitRepository.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Boolean.TRUE.equals(dataTableRequestDto.getSorted().get(0).getDesc()) ? Sort.Direction.DESC : Sort.Direction.ASC, dataTableRequestDto.getSorted().get(0).getId()));
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
    public PaginatedDataDto<NotificacionesEntity> findNotificacionesByPage(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::findNotificacionesByPage(%s)", this.getClass().getName(), dataTableRequestDto.toString()));

        try {
            Map<String, FilterInfoDto> filtersInfo = new HashMap<>();

            // get column names to filter
            if (dataTableRequestDto.getFiltered() != null) {
                for (FilterInfoDto filterInfo : dataTableRequestDto.getFiltered()) {
                    filtersInfo.put(filterInfo.getId(), filterInfo);
                }
            }

            // build filter specifications
            Specification unidadNegocioSpec = Utils.<NotificacionesEntity>getEqualSpecification(UNIDAD_NEGOCIO, getValueAsString(filtersInfo, UNIDAD_NEGOCIO));
            Specification areaFuncionalSpec = Utils.<NotificacionesEntity>getEqualSpecification(AREA_FUNCIONAL, getValueAsString(filtersInfo, AREA_FUNCIONAL));
            Specification unidadAdministrativaSpec = Utils.<NotificacionesEntity>getEqualSpecification(UNIDAD_ADMINISTRATIVA, getValueAsString(filtersInfo, UNIDAD_ADMINISTRATIVA));
            Specification estadoSpec = Utils.<NotificacionesEntity>getEqualSpecification("estado", getValueAsString(filtersInfo, "estado"));

            // put filter specifications together
            Specification filterSpec = Specifications
                    .where(getDefaultSpecification())
                    .and(unidadNegocioSpec)
                    .and(areaFuncionalSpec)
                    .and(unidadAdministrativaSpec)
                    .and(estadoSpec)
                    ;

            Page<NotificacionesEntity> pageData;

            // if sort was not requested then use the default order
            if (dataTableRequestDto.getSorted() == null || dataTableRequestDto.getSorted().isEmpty()) {
                pageData = this.notificationsRepository.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Sort.Direction.DESC,"id"));
            } else { // else use the requested order
                pageData = this.notificationsRepository.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Boolean.TRUE.equals(dataTableRequestDto.getSorted().get(0).getDesc()) ? Sort.Direction.DESC : Sort.Direction.ASC, dataTableRequestDto.getSorted().get(0).getId()));
            }

            // return a transfer object containing the rows, total rows and total pages
            return new PaginatedDataDto<>(pageData.getContent(), pageData.getTotalElements(), pageData.getTotalPages());
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourcesNotFoundException();
        }
    }

    @Override
    public List<NotificacionesEntity> findAll() throws ResourcesNotFoundException {
        return Collections.emptyList();
    }

    @Override
    public PaginatedDataDto<NotificacionesEntity> findAllByPage(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
        return null;
    }

    @Override
    public NotificacionesEntity findById(Long id) throws ResourceNotFoundException {
        return null;
    }

    public NotificacionesEntity create(NotificacionesEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceCreateException {
        logger.debug(String.format("@%s::create(%s, %d)", this.getClass().getName(), entity, requesterId));

        AdmUsuarioEntity requester = this.userRepo.findOne(requesterId);

        if (requester == null) {
            throw new RequesterNotFoundException();
        }

        AdmParametrosEntity param = this.paramRepo.findByNombre("CONFIRMATION_PERIOD");

        if (param == null) {
            throw new ResourceCreateException();
        }

        entity.setEstado("CREADA");
        entity.setDiasRecordatorio(7l);
        entity.setDiasEscalamiento(10l);
        entity.setPeriodo(param.getValor());
        entity.setUsuarioCreacion(requester.getUsuario());
        entity.setFechaCreacion(new Timestamp(new Date().getTime()));

        try {
            return this.notificationsRepository.save(entity);
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourceCreateException();
        }
    }

    public NotificacionesEntity update(NotificacionesEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceUpdateException {
        logger.debug(String.format("@%s::update(%s, %d)", this.getClass().getName(), entity, requesterId));

        AdmUsuarioEntity requester = this.userRepo.findOne(requesterId);

        if (requester == null) {
            throw new RequesterNotFoundException();
        }

        entity.setUsuarioModificacion(requester.getUsuario());
        entity.setFechaModificacion(new Timestamp(new Date().getTime()));

        try {
            return this.notificationsRepository.save(entity);
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourceUpdateException();
        }
    }

    public NotificacionesEntity delete(Long entityId, Long requesterId) throws RequesterNotFoundException, ResourceNotFoundException, ResourceUpdateException {
        logger.debug(String.format("@%s::delete(%d, %d)", this.getClass().getName(), entityId, requesterId));

        AdmUsuarioEntity requester = this.userRepo.findOne(requesterId);

        if (requester == null) {
            throw new RequesterNotFoundException();
        }

        NotificacionesEntity entity = this.notificationsRepository.findOne(entityId);

        if (entity == null) {
            throw new ResourceNotFoundException();
        }


        entity.setEstado("ANULADA");
        entity.setUsuarioModificacion(requester.getUsuario());
        entity.setFechaModificacion(new Timestamp(new Date().getTime()));

        try {
            return this.notificationsRepository.save(entity);
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourceUpdateException();
        }
    }

    public Long send(Long userId) throws ResourceUpdateException {
        logger.debug(String.format("@%s::send(%d)", this.getClass().getName(), userId));

        try {
            return this.notificationsRepository.sendNotification(userId);
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourceUpdateException();
        }
    }
}
