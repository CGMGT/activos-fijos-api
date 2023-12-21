package gt.com.tigo.fixed_assets.service;

import gt.com.tigo.fixed_assets.dao.portal.*;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import static gt.com.tigo.fixed_assets.util.Utils.*;

@Service
public class GestionesService implements ICatalog<GestionesEntity> {
    private static final Logger logger = LoggerFactory.getLogger(GestionesService.class);
    public static final String USUARIO_CREACION = "usuarioCreacion";
    public static final String DETALLE = "detalle";
    public static final String SERIE = "serie";
    public static final String MODELO = "modelo";
    public static final String LEGACY_CODE = "legacyCode";
    public static final String ETIQUETA = "etiqueta";
    public static final String DESCRIPCION = "descripcion";
    public static final String RECEPTOR = "receptor";
    public static final String TIPO_GESTION = "tipoGestion";
    public static final String ESTADO = "estado";
    public static final String NOMBRE = "nombre";
    public static final String FECHA_CREACION = "fechaCreacion";
    public static final String ASSET_NUMBER = "assetNumber";
    public static final String APROBADA_POR_RECEPTOR = "Aprobada por receptor";
    public static final String APROBADA_POR_GERENTE = "Aprobada por gerente";
    public static final String TRASLADO = "TRASLADO";
    public static final String CREADA = "Creada";
    public static final String MANAGER_CAN_APPROVE_TRANSFER_REQUESTS = "MANAGER_CAN_APPROVE_TRANSFER_REQUESTS";
    public static final String NO = "NO";
    public static final String LA_GESTION_DE_S_NO_D_HA_SIDO_APROBADA_POR_S = "La gestión de %s No. %d ha sido aprobada por %s.";
    public static final String LOCACION = "Locación";
    public static final String ANALYST = "ANALYST";
    public static final String MANAGER = "MANAGER";
    public static final String EMPLOYEE = "EMPLOYEE";
    public static final String ID_RECEPTOR = "idReceptor";
    public static final String ID_JEFE = "idJefe";
    public static final String ANULADA = "Anulada";

    @Autowired
    private GestionesRepository requestsRepository;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private EstadosAprobacionRepository requestStatusRepo;

    @Autowired
    private DocumentosRepository documentsRepo;

    @Autowired
    private MensajesService msgService;

    @Autowired
    private UserService userService;

    @Autowired
    private ParameterService paramService;

    @Autowired
    private ActivosFijosService assetService;

    @Autowired
    private LocacionesRepository locationsRepository;

    @Autowired
    private EmpleadosRepository empleadosRepository;

    /**
     * Returns a list of all resources in this catalog.
     * @return a list of objects containing all resources' data.
     * @throws ResourcesNotFoundException when an error occurs.
     */
    @Override
    public List<GestionesEntity> findAll() throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::findAll()", this.getClass().getName()));

        try {
            return (List<GestionesEntity>) this.requestsRepository.findAll();
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
    public PaginatedDataDto<GestionesEntity> findAllByPage(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::findAllByPage(%s)", this.getClass().getName(), dataTableRequestDto.toString()));

        try {
            Map<String, FilterInfoDto> filtersInfo = new HashMap<>();

            // get column names to filter
            if (dataTableRequestDto.getFiltered() != null) {
                for (FilterInfoDto filterInfo : dataTableRequestDto.getFiltered()) {
                    filtersInfo.put(filterInfo.getId(), filterInfo);
                }
            }

            Specification specialSpec = null;

            // special case - return transfer requests of type location to the analyst
            if (getValueAsLong(filtersInfo, TIPO_GESTION) == 3) {
                if (filtersInfo.containsKey("mode") && getValueAsString(filtersInfo, "mode").equalsIgnoreCase(ANALYST)) {
                    specialSpec = this.<GestionesEntity>getCustomSpecification();
                } else if (filtersInfo.containsKey("mode") && getValueAsString(filtersInfo, "mode").equalsIgnoreCase(MANAGER)
                        || filtersInfo.containsKey("mode") && getValueAsString(filtersInfo, "mode").equalsIgnoreCase(EMPLOYEE)) {
                    specialSpec = Utils.<GestionesEntity, EstadosAprobacionEntity>getLikeChildSpecification(ESTADO, NOMBRE, getValueAsString(filtersInfo, ESTADO));
                } else {
                    specialSpec = Utils.getConjunction();
                }
            } else {
                specialSpec = Utils.<GestionesEntity, EstadosAprobacionEntity>getLikeChildSpecification(ESTADO, NOMBRE, getValueAsString(filtersInfo, ESTADO));
            }

            // build filter specifications
            Specification idSpec = Utils.<GestionesEntity>getEqualSpecification("id", getValueAsLong(filtersInfo, "id"));
            Specification createdUserSpec = Utils.<GestionesEntity>getEqualSpecification(USUARIO_CREACION, getValueAsString(filtersInfo, USUARIO_CREACION));
            Specification serialNumberSpec = Utils.<GestionesEntity, GestionesDetalleEntity>getLikeChildSpecification(DETALLE, SERIE, getValueAsString(filtersInfo, SERIE));
            Specification modelSpec = Utils.<GestionesEntity, GestionesDetalleEntity>getLikeChildSpecification(DETALLE, MODELO, getValueAsString(filtersInfo, MODELO));
            Specification legacyCodeSpec = Utils.<GestionesEntity, GestionesDetalleEntity>getLikeChildSpecification(DETALLE, LEGACY_CODE, getValueAsString(filtersInfo, LEGACY_CODE));
            Specification tagNumberSpec = Utils.<GestionesEntity, GestionesDetalleEntity>getLikeChildSpecification(DETALLE, ETIQUETA, getValueAsString(filtersInfo, ETIQUETA));
            Specification descriptionSpec = Utils.<GestionesEntity, GestionesDetalleEntity>getLikeChildSpecification(DETALLE, DESCRIPCION, getValueAsString(filtersInfo, DESCRIPCION));
            Specification receiverSpec = Utils.<GestionesEntity, CpEmpleadosEntity>getEqualChildSpecification(RECEPTOR,"id", getValueAsLong(filtersInfo, ID_RECEPTOR));
            Specification requestTypeSpec = Utils.<GestionesEntity, TipoGestionesEntity>getEqualChildSpecification(TIPO_GESTION, "id", getValueAsLong(filtersInfo, TIPO_GESTION));
            Specification managerSpec = Utils.<GestionesEntity, AdmUsuarioEntity>getEqualChildSpecification("jefe", "id", getValueAsLong(filtersInfo, ID_JEFE));

            // put filter specifications together
            Specification filterSpec = Specifications
                    .where(getDefaultSpecification())
                    .and(idSpec)
                    .and(createdUserSpec)
                    .and(serialNumberSpec)
                    .and(modelSpec)
                    .and(legacyCodeSpec)
                    .and(tagNumberSpec)
                    .and(descriptionSpec)
                    .and(receiverSpec)
                    .and(requestTypeSpec)
                    .and(managerSpec)
                    .and(specialSpec)
                    ;

            Page<GestionesEntity> pageData;

            // if sort was not requested then use the default order
            if (dataTableRequestDto.getSorted() == null || dataTableRequestDto.getSorted().isEmpty()) {
                pageData = this.requestsRepository.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Sort.Direction.DESC, FECHA_CREACION));
            } else { // else use the requested order
                pageData = this.requestsRepository.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Boolean.TRUE.equals(dataTableRequestDto.getSorted().get(0).getDesc()) ? Sort.Direction.DESC : Sort.Direction.ASC, dataTableRequestDto.getSorted().get(0).getId()));
            }

            // return a transfer object containing the rows, total rows and total pages
            return new PaginatedDataDto<>(pageData.getContent(), pageData.getTotalElements(), pageData.getTotalPages());
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourcesNotFoundException();
        }
    }

    private <T> Specification<T> getCustomSpecification() {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                final Predicate transferType1 =  builder.equal(root.<String>get("tipoTraslado"), "Colaborador");
                final Predicate requestStatus1 =  builder.equal(builder.upper(getJoin(root, ESTADO, JoinType.INNER).<String>get(NOMBRE)), APROBADA_POR_RECEPTOR.toUpperCase());

                final Predicate transferType2 =  builder.equal(root.<String>get("tipoTraslado"), LOCACION);
                final Predicate requestStatus2 =  builder.equal(builder.upper(getJoin(root, ESTADO, JoinType.INNER).<String>get(NOMBRE)), APROBADA_POR_RECEPTOR.toUpperCase());

                final Predicate and1 = builder.and(transferType1, requestStatus1);
                final Predicate and2 = builder.and(transferType2, requestStatus2);

                final Predicate or = builder.or(and1, and2);

                query.distinct(true);

                return or;
            }
        };
    }

    /**
     * Returns a resource by id.
     * @param id The id of the requested resource
     * @return an object containing the found resource.
     * @throws ResourceNotFoundException when no record is found.
     */
    @Override
    public GestionesEntity findById(Long id) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findById(%d)", this.getClass().getName(), id));

        GestionesEntity entidad = this.requestsRepository.findOne(id);

        if (entidad == null) {
            throw new ResourceNotFoundException();
        }

        return entidad;
    }

    public Collection<GestionesEntity> findByUserAndType(Long id, Long type) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findByUserAndType(%d)", this.getClass().getName(), id));

        Collection<GestionesEntity> entidad = this.requestsRepository.findByReceptorIdAndTipoGestionId(id, type);

        if (entidad == null) {
            throw new ResourceNotFoundException();
        }

        return entidad;
    }

    public Collection<GestionesEntity> findByType(Long type) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findByType(%s)", this.getClass().getName(), type));

        Collection<GestionesEntity> entidad = this.requestsRepository.findByTipoGestionId(type);

        if (entidad == null) {
            throw new ResourceNotFoundException();
        }

        return entidad;
    }


    @Override
    public GestionesEntity create(GestionesEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceCreateException {
        logger.debug(String.format("@%s::create(%s, %d)", this.getClass().getName(), entity, requesterId));

        AdmUsuarioEntity requester = this.userRepo.findOne(requesterId);

        if (requester == null) {
            throw new RequesterNotFoundException();
        }

        try {
            if (entity.getTipoGestion().getNombre().equals("ALTA") && entity.getEstado().getNombre().equals(CREADA) && this.paramService.findByNombre("MANAGER_CAN_APPROVE_ADD_REQUESTS").getValor().equalsIgnoreCase(NO)) {
                entity.setEstado(this.requestStatusRepo.findByNombre(APROBADA_POR_GERENTE));
            } else if (entity.getTipoGestion().getNombre().equals("BAJA") && entity.getEstado().getNombre().equals(CREADA) && this.paramService.findByNombre("MANAGER_CAN_APPROVE_REMOVE_REQUESTS").getValor().equalsIgnoreCase(NO)) {
                entity.setEstado(this.requestStatusRepo.findByNombre(APROBADA_POR_GERENTE));
            } else if (entity.getTipoGestion().getNombre().equals(TRASLADO) && entity.getEstado().getNombre().equals(CREADA) && entity.getTipoTraslado().equals("Colaborador") && this.paramService.findByNombre(MANAGER_CAN_APPROVE_TRANSFER_REQUESTS).getValor().equalsIgnoreCase(NO)) {
                entity.setEstado(this.requestStatusRepo.findByNombre(APROBADA_POR_GERENTE));
            } else if (entity.getTipoGestion().getNombre().equals(TRASLADO) && entity.getEstado().getNombre().equals(CREADA) && entity.getTipoTraslado().equals(LOCACION) && this.paramService.findByNombre(MANAGER_CAN_APPROVE_TRANSFER_REQUESTS).getValor().equalsIgnoreCase(NO)) {
                entity.setEstado(this.requestStatusRepo.findByNombre(APROBADA_POR_RECEPTOR));
            }

            if (entity.getReceptor() == null && entity.getLocacion().getEncargado1() != null){
                AdmUsuarioEntity user = this.userRepo.findOne(entity.getLocacion().getEncargado1().getId());
                CpEmpleadosEntity emp = this.empleadosRepository.findByEmailColaborador(user.getCorreoElectronico());
                AdmUsuarioEntity jefe = this.userRepo.findByCorreoElectronico(emp.getEmailJefeInmediato());
                entity.setReceptor(emp);
                entity.setJefe(jefe);
            }

            entity.setUsuarioCreacion(requester.getUsuario());
            entity.setFechaCreacion(new Timestamp(new Date().getTime()));

            entity = this.requestsRepository.save(entity);
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourceCreateException();
        }

        for (GestionesDetalleEntity detail : entity.getDetalle()) {
            Long assetNumber = detail.getAssetNumber();

            CpActivosFijosEntity asset = null;

            try {
                asset = this.assetService.findByAssetNumber(assetNumber);
            } catch (ResourceNotFoundException e) {
                logger.warn(e.getMessage());
            }

            if (asset != null) {
                asset.setLocked(1);

                try {
                    this.assetService.update(asset, requesterId);
                } catch (ResourceUpdateException e) {
                    logger.error(e.getMessage());

                    throw new ResourceCreateException();
                }
            }
        }

        MensajesEntity message = new MensajesEntity();
        message.setUsuario(requester);
        message.setMensaje(String.format("Nueva gestión de %s", entity.getTipoGestion().getNombre().toLowerCase()));
        message.setComentarios(String.format("La gestión de %s No. %d ha sido creada.", entity.getTipoGestion().getNombre().toLowerCase(), entity.getId()));
        message.setEstado("A");

        try {
            this.msgService.create(message, requesterId);
        } catch (Exception ex) {
            throw new ResourceCreateException();
        }

        return entity;
    }

    @Override
    public GestionesEntity update(GestionesEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceUpdateException {
        logger.debug(String.format("@%s::update(%s, %d)", this.getClass().getName(), entity, requesterId));

        AdmUsuarioEntity requester = this.userRepo.findOne(requesterId);

        if (requester == null) {
            throw new RequesterNotFoundException();
        }

        if (entity.getTipoGestion().getNombre().equals(TRASLADO) && entity.getTipoTraslado().equals(LOCACION) && entity.getEstado().getNombre().equals(APROBADA_POR_GERENTE)) {
            entity.setEstado(this.requestStatusRepo.findByNombre(APROBADA_POR_RECEPTOR));
        }

        entity.setUsuarioModificacion(requester.getUsuario());
        entity.setFechaModificacion(new Timestamp(new Date().getTime()));

        try {
            entity = this.requestsRepository.save(entity);
            if (entity.getEstado().getNombre().equals(ANULADA)) this.requestsRepository.enviaCorreo(entity.getUsuarioCreacion(), (String.valueOf(entity.getId())));
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourceUpdateException();
        }

        if (entity.getEstado().getNombre().equals("Aprobada por analista") || entity.getEstado().getNombre().equals("Rechazada")
                || entity.getEstado().getNombre().equals(ANULADA)) {
            for (GestionesDetalleEntity detail : entity.getDetalle()) {
                Long assetNumber = detail.getAssetNumber();

                CpActivosFijosEntity asset = null;

                try {
                    asset = this.assetService.findByAssetNumber(assetNumber);
                } catch (ResourceNotFoundException e) {
                    logger.warn(e.getMessage());
                }

                if (asset != null) {
                    asset.setLocked(0);

                    try {
                        this.assetService.update(asset, requesterId);
                    } catch (ResourceUpdateException e) {
                        logger.error(e.getMessage());

                        throw new ResourceUpdateException();
                    }
                }
            }
        }

        try {
            MensajesEntity message = new MensajesEntity();

            if (entity.getEstado().getNombre().equalsIgnoreCase("APROBADA POR GERENTE")) {
                message.setUsuario(userService.findByEmail(entity.getUsuarioCreacion()));
                message.setMensaje(String.format("Gestión de %s No. %d aprobada por gerente", entity.getTipoGestion().getNombre().toLowerCase(), entity.getId()));
                message.setComentarios(String.format(LA_GESTION_DE_S_NO_D_HA_SIDO_APROBADA_POR_S, entity.getTipoGestion().getNombre().toLowerCase(), entity.getId(), requester.getNombreMostrado()));
            } else if (entity.getEstado().getNombre().equalsIgnoreCase("APROBADA POR ANALISTA")) {
                message.setUsuario(userService.findByEmail(entity.getUsuarioCreacion()));
                message.setMensaje(String.format("Gestión de %s No. %d aprobada por el analista", entity.getTipoGestion().getNombre().toLowerCase(), entity.getId()));
                message.setComentarios(String.format(LA_GESTION_DE_S_NO_D_HA_SIDO_APROBADA_POR_S, entity.getTipoGestion().getNombre().toLowerCase(), entity.getId(), requester.getNombreMostrado()));
            } else if (entity.getEstado().getNombre().equalsIgnoreCase("APROBADA POR RECEPTOR")) {
                message.setUsuario(userService.findByEmail(entity.getUsuarioCreacion()));
                message.setMensaje(String.format("Gestión de %s No. %d aprobada por el receptor", entity.getTipoGestion().getNombre().toLowerCase(), entity.getId()));
                message.setComentarios(String.format(LA_GESTION_DE_S_NO_D_HA_SIDO_APROBADA_POR_S, entity.getTipoGestion().getNombre().toLowerCase(), entity.getId(), requester.getNombreMostrado()));
            } else if (entity.getEstado().getNombre().equalsIgnoreCase("RECHAZADA")) {
                message.setUsuario(userService.findByEmail(entity.getUsuarioCreacion()));
                message.setMensaje(String.format("Gestión de %s No. %d rechazada", entity.getTipoGestion().getNombre().toLowerCase(), entity.getId()));
                message.setComentarios(String.format("La gestión de %s No. %d ha sido rechazada por %s.", entity.getTipoGestion().getNombre().toLowerCase(), entity.getId(), requester.getNombreMostrado()));
            } else if (entity.getEstado().getNombre().equalsIgnoreCase("ANULADA")) {
                message.setUsuario(userService.findByEmail(entity.getUsuarioCreacion()));
                message.setMensaje(String.format("Gestión de %s No. %d anulada", entity.getTipoGestion().getNombre().toLowerCase(), entity.getId()));
                message.setComentarios(String.format("La gestión de %s No. %d ha sido anulada por %s.", entity.getTipoGestion().getNombre().toLowerCase(), entity.getId(), entity.getComentarios()));
            }

            message.setEstado("A");

            this.msgService.create(message, requesterId);
        } catch (Exception ex) {
            throw new ResourceUpdateException();
        }

        return entity;
    }

    @Override
    public GestionesEntity delete(Long entityId, Long requesterId) throws RequesterNotFoundException, ResourceNotFoundException, ResourceUpdateException {
        logger.debug(String.format("@%s::delete(%d, %d)", this.getClass().getName(), entityId, requesterId));

        AdmUsuarioEntity requester = this.userRepo.findOne(requesterId);

        if (requester == null) {
            throw new RequesterNotFoundException();
        }

        GestionesEntity entity = this.requestsRepository.findOne(entityId);

        if (entity == null) {
            throw new ResourceNotFoundException();
        }

        EstadosAprobacionEntity status = this.requestStatusRepo.findByNombre(ANULADA);

        if (status == null) {
            throw new ResourceNotFoundException();
        }

        entity.setEstado(status);
        entity.setUsuarioModificacion(requester.getUsuario());
        entity.setFechaModificacion(new Timestamp(new Date().getTime()));

        try {
            entity = this.requestsRepository.save(entity);
            this.requestsRepository.enviaCorreo(entity.getUsuarioCreacion(), entityId.toString());
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourceUpdateException();
        }

        for (GestionesDetalleEntity detail : entity.getDetalle()) {
            Long assetNumber = detail.getAssetNumber();

            CpActivosFijosEntity asset = null;

            try {
                asset = this.assetService.findByAssetNumber(assetNumber);
            } catch (ResourceNotFoundException e) {
                logger.warn(e.getMessage());
            }

            if (asset != null) {
                asset.setLocked(0);

                try {
                    this.assetService.update(asset, requesterId);
                } catch (ResourceUpdateException e) {
                    logger.error(e.getMessage());

                    throw new ResourceUpdateException();
                }
            }
        }

        try {
            MensajesEntity message = new MensajesEntity();
            message.setUsuario(requester);
            message.setMensaje(String.format("Gestión de %s No. %d anulada", entity.getTipoGestion().getNombre().toLowerCase(), entity.getId()));
            message.setComentarios(String.format("La gestión de %s No. %d ha sido anulada.", entity.getTipoGestion().getNombre().toLowerCase(), entity.getId()));

            message.setEstado("A");

            this.msgService.create(message, requesterId);
        } catch (Exception ex) {
            throw new ResourceUpdateException();
        }

        return entity;
    }

    public DocumentosEntity upload(MultipartFile file, Long requestDetailId, Long requesterId) throws RequesterNotFoundException, ResourceCreateException {
        logger.debug(String.format("@%s::upload(%s, %d, %d)", this.getClass().getName(), file.getName(), requestDetailId, requesterId));

        AdmUsuarioEntity requester = this.userRepo.findOne(requesterId);

        if (requester == null) {
            throw new RequesterNotFoundException();
        }

        DocumentosEntity entity = new DocumentosEntity();

        try {
            entity.setTipoArchivo(file.getContentType());
            entity.setNombreArchivo(file.getOriginalFilename());
            entity.setDescripcion("Archivo adjunto a la gestión.");
            entity.setPeso(file.getSize());
            entity.setContenido(file.getBytes());
            entity.setIdGestionDet(requestDetailId);
            entity.setUsuarioCreacion(requester.getUsuario());
            entity.setFechaCreacion(new Timestamp(new Date().getTime()));
        } catch (IOException ex) {
            logger.error(ex.getMessage());

            throw new ResourceCreateException();
        }

        try {
            return this.documentsRepo.save(entity);
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourceCreateException();
        }
    }

    /**
     * Returns a reference to a document file.
     * @param id The id of the document.
     * @return A DocumentosEntity object containing the file bytes.
     * @throws ResourceNotFoundException When no document is found with the provided id.
     */
    public DocumentosEntity download(Long id) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::download(%d)", this.getClass().getName(), id));

        DocumentosEntity entity = this.documentsRepo.findOne(id);

        if (entity == null) {
            throw new ResourceNotFoundException();
        }

        return entity;
    }

    /**
     * Returns the count of requests
     * @param dataTableRequestDto The filters.
     * @return A numeric value of how many items in the list.
     * @throws ResourcesNotFoundException When there's a problem during requests search.
     */
    public Long count(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::count(%s)", this.getClass().getName(), dataTableRequestDto.toString()));

        try {
            Map<String, FilterInfoDto> filtersInfo = new HashMap<>();

            // get column names to filter
            if (dataTableRequestDto.getFiltered() != null) {
                for (FilterInfoDto filterInfo : dataTableRequestDto.getFiltered()) {
                    filtersInfo.put(filterInfo.getId(), filterInfo);
                }
            }

            Specification specialSpec = null;

            // special case - return transfer requests of type location to the analyst
            if (getValueAsLong(filtersInfo, TIPO_GESTION) == 3) {
                if (filtersInfo.containsKey("mode") && getValueAsString(filtersInfo, "mode").toUpperCase().equals(ANALYST)) {
                    specialSpec = this.<GestionesEntity>getCustomSpecification();
                } else if (filtersInfo.containsKey("mode") && getValueAsString(filtersInfo, "mode").equalsIgnoreCase(MANAGER)
                        || filtersInfo.containsKey("mode") && getValueAsString(filtersInfo, "mode").equalsIgnoreCase(EMPLOYEE)) {
                    specialSpec = Utils.<GestionesEntity, TipoGestionesEntity>getLikeChildSpecification(ESTADO, NOMBRE, getValueAsString(filtersInfo, ESTADO));
                } else {
                    specialSpec = Utils.getConjunction();
                }
            } else {
                specialSpec = Utils.<GestionesEntity, TipoGestionesEntity>getLikeChildSpecification(ESTADO, NOMBRE, getValueAsString(filtersInfo, ESTADO));
            }

            // build filter specifications
            Specification idSpec = Utils.<GestionesEntity>getEqualSpecification("id", getValueAsLong(filtersInfo, "id"));
            Specification createdUserSpec = Utils.<GestionesEntity>getEqualSpecification(USUARIO_CREACION, getValueAsString(filtersInfo, USUARIO_CREACION));
            Specification serialNumberSpec = Utils.<GestionesEntity, GestionesDetalleEntity>getLikeChildSpecification(DETALLE, SERIE, getValueAsString(filtersInfo, SERIE));
            Specification modelSpec = Utils.<GestionesEntity, GestionesDetalleEntity>getLikeChildSpecification(DETALLE, MODELO, getValueAsString(filtersInfo, MODELO));
            Specification legacyCodeSpec = Utils.<GestionesEntity, GestionesDetalleEntity>getLikeChildSpecification(DETALLE, LEGACY_CODE, getValueAsString(filtersInfo, LEGACY_CODE));
            Specification tagNumberSpec = Utils.<GestionesEntity, GestionesDetalleEntity>getLikeChildSpecification(DETALLE, ETIQUETA, getValueAsString(filtersInfo, ETIQUETA));
            Specification descriptionSpec = Utils.<GestionesEntity, GestionesDetalleEntity>getLikeChildSpecification(DETALLE, DESCRIPCION, getValueAsString(filtersInfo, DESCRIPCION));
            Specification receiverSpec = Utils.<GestionesEntity, CpEmpleadosEntity>getEqualChildSpecification(RECEPTOR,"id", getValueAsLong(filtersInfo, ID_RECEPTOR));
            Specification requestTypeSpec = Utils.<GestionesEntity, TipoGestionesEntity>getEqualChildSpecification(TIPO_GESTION, "id", getValueAsLong(filtersInfo, TIPO_GESTION));
            Specification managerSpec = Utils.<GestionesEntity, AdmUsuarioEntity>getEqualChildSpecification("jefe", "id", getValueAsLong(filtersInfo, ID_JEFE));

            // put filter specifications together
            Specification filterSpec = Specifications
                    .where(getDefaultSpecification())
                    .and(idSpec)
                    .and(createdUserSpec)
                    .and(serialNumberSpec)
                    .and(modelSpec)
                    .and(legacyCodeSpec)
                    .and(tagNumberSpec)
                    .and(descriptionSpec)
                    .and(receiverSpec)
                    .and(requestTypeSpec)
                    .and(managerSpec)
                    .and(specialSpec)
                    ;

            // return a transfer object containing the rows, total rows and total pages
            return this.requestsRepository.count(filterSpec);
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
    public PaginatedDataDto<GestionesEntity> report(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::report(%s)", this.getClass().getName(), dataTableRequestDto.toString()));

        try {
            Map<String, FilterInfoDto> filtersInfo = new HashMap<>();

            // get column names to filter
            if (dataTableRequestDto.getFiltered() != null) {
                for (FilterInfoDto filterInfo : dataTableRequestDto.getFiltered()) {
                    filtersInfo.put(filterInfo.getId(), filterInfo);
                }
            }

            // build filter specifications
            Specification createdDateSpec = Utils.<GestionesEntity>getBetweenSpecification(FECHA_CREACION, getValueAsDate(filtersInfo, "createdDateFrom"), getValueAsDate(filtersInfo, "createdDateTo"));
            Specification createdUserSpec = Utils.<GestionesEntity>getLikeSpecification(USUARIO_CREACION, getValueAsString(filtersInfo, "createdBy"));
            Specification idSpec = Utils.<GestionesEntity>getEqualSpecification("id", getValueAsLong(filtersInfo, "requestId"));
            Specification requestTypeSpec = Utils.<GestionesEntity, TipoGestionesEntity>getEqualChildSpecification(TIPO_GESTION, "id", getValueAsLong(filtersInfo, "requestType"));
            Specification workflowStatusSpec = Utils.<GestionesEntity, TipoGestionesEntity>getLikeChildSpecification(ESTADO, NOMBRE, getValueAsString(filtersInfo, "status"));
            Specification assetNumberSpec = Utils.<GestionesEntity, GestionesDetalleEntity>getLikeChildSpecification(DETALLE, ASSET_NUMBER, getValueAsString(filtersInfo, ASSET_NUMBER));
            Specification serialNumberSpec = Utils.<GestionesEntity, GestionesDetalleEntity>getLikeChildSpecification(DETALLE, SERIE, getValueAsString(filtersInfo, "serialNumber"));
            Specification modelSpec = Utils.<GestionesEntity, GestionesDetalleEntity>getLikeChildSpecification(DETALLE, MODELO, getValueAsString(filtersInfo, "modelNumber"));
            Specification legacyCodeSpec = Utils.<GestionesEntity, GestionesDetalleEntity>getLikeChildSpecification(DETALLE, LEGACY_CODE, getValueAsString(filtersInfo, LEGACY_CODE));
            Specification tagNumberSpec = Utils.<GestionesEntity, GestionesDetalleEntity>getLikeChildSpecification(DETALLE, ETIQUETA, getValueAsString(filtersInfo, "tagNumber"));

            // put filter specifications together
            Specification filterSpec = Specifications
                    .where(getDefaultSpecification())
                    .and(createdDateSpec)
                    .and(createdUserSpec)
                    .and(idSpec)
                    .and(requestTypeSpec)
                    .and(workflowStatusSpec)
                    .and(assetNumberSpec)
                    .and(serialNumberSpec)
                    .and(modelSpec)
                    .and(legacyCodeSpec)
                    .and(tagNumberSpec)
                    ;

            Page<GestionesEntity> pageData;

            // if sort was not requested then use the default order
            if (dataTableRequestDto.getSorted() == null || dataTableRequestDto.getSorted().isEmpty()) {
                pageData = this.requestsRepository.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Sort.Direction.DESC, FECHA_CREACION));
            } else { // else use the requested order
                pageData = this.requestsRepository.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Boolean.TRUE.equals(dataTableRequestDto.getSorted().get(0).getDesc()) ? Sort.Direction.DESC : Sort.Direction.ASC, dataTableRequestDto.getSorted().get(0).getId()));
            }

            // return a transfer object containing the rows, total rows and total pages
            return new PaginatedDataDto<>(pageData.getContent(), pageData.getTotalElements(), pageData.getTotalPages());
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourcesNotFoundException();
        }
    }

    /**
     *
     * @param dataTableRequestDto
     * @return
     * @throws ResourcesNotFoundException
     */
    public ModelAndView exportAsXLSX(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::exportAsXLSX(%s)", this.getClass().getName(), dataTableRequestDto.toString()));

        try {
            Map<String, FilterInfoDto> filtersInfo = new HashMap<>();

            // get column names to filter
            if (dataTableRequestDto.getFiltered() != null) {
                for (FilterInfoDto filterInfo : dataTableRequestDto.getFiltered()) {
                    filtersInfo.put(filterInfo.getId(), filterInfo);
                }
            }

            // build filter specifications
            Specification createdDateSpec = Utils.<GestionesEntity>getBetweenSpecification(FECHA_CREACION, getValueAsDate(filtersInfo, "createdDateFrom"), getValueAsDate(filtersInfo, "createdDateTo"));
            Specification createdUserSpec = Utils.<GestionesEntity>getLikeSpecification(USUARIO_CREACION, getValueAsString(filtersInfo, "createdBy"));
            Specification idSpec = Utils.<GestionesEntity>getEqualSpecification("id", getValueAsLong(filtersInfo, "requestId"));
            Specification requestTypeSpec = Utils.<GestionesEntity, TipoGestionesEntity>getEqualChildSpecification(TIPO_GESTION, "id", getValueAsLong(filtersInfo, "requestType"));
            Specification workflowStatusSpec = Utils.<GestionesEntity, TipoGestionesEntity>getLikeChildSpecification(ESTADO, NOMBRE, getValueAsString(filtersInfo, "status"));
            Specification assetNumberSpec = Utils.<GestionesEntity, GestionesDetalleEntity>getLikeChildSpecification(DETALLE, ASSET_NUMBER, getValueAsString(filtersInfo, ASSET_NUMBER));
            Specification serialNumberSpec = Utils.<GestionesEntity, GestionesDetalleEntity>getLikeChildSpecification(DETALLE, SERIE, getValueAsString(filtersInfo, "serialNumber"));
            Specification modelSpec = Utils.<GestionesEntity, GestionesDetalleEntity>getLikeChildSpecification(DETALLE, MODELO, getValueAsString(filtersInfo, "modelNumber"));
            Specification legacyCodeSpec = Utils.<GestionesEntity, GestionesDetalleEntity>getLikeChildSpecification(DETALLE, LEGACY_CODE, getValueAsString(filtersInfo, LEGACY_CODE));
            Specification tagNumberSpec = Utils.<GestionesEntity, GestionesDetalleEntity>getLikeChildSpecification(DETALLE, ETIQUETA, getValueAsString(filtersInfo, "tagNumber"));

            // put filter specifications together
            Specification filterSpec = Specifications
                    .where(getDefaultSpecification())
                    .and(createdDateSpec)
                    .and(createdUserSpec)
                    .and(idSpec)
                    .and(requestTypeSpec)
                    .and(workflowStatusSpec)
                    .and(assetNumberSpec)
                    .and(serialNumberSpec)
                    .and(modelSpec)
                    .and(legacyCodeSpec)
                    .and(tagNumberSpec)
                    ;

            List<GestionesEntity> entities = this.requestsRepository.findAll(filterSpec, new Sort(Sort.Direction.ASC, "id"));

            String[] columns = {
                    "NO. GESTIÓN",
                    "NO. DETALLE",
                    "TIPO GESTIÓN",
                    "ESTADO",
                    "FECHA CREACIÓN",
                    "CREADO POR",
                    "FECHA MODIFICACIÓN",
                    "MODIFICADO POR",
                    "DESCRIPCIÓN",
                    "NO. RECEPTOR",
                    "RECEPTOR",
                    "TIPO DE TRASLADO",
                    "ÚLTIMO COMENTARIO",
                    "NO. ACTIVO FIJO",
                    "SERIE",
                    "MODELO",
                    "CÓDIGO LEGADO",
                    "ETIQUETA",
                    "ACTIVO FIJO",
                    "TIPO MOTIVO",
                    "MOTIVO",
                    "NO. LOCACIÓN",
                    "LOCACIÓN",
                    "FA LOCATION CODE",
                    "BOOK TYPE CODE"
            };

            List<Object> rows = new ArrayList<>();

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            for (GestionesEntity entity : entities) {
                for (GestionesDetalleEntity detail : entity.getDetalle()) {
                    List<String> values = new ArrayList<>();
                    CpLocationsEntity locEntity = this.locationsRepository.findOne(detail.getLocacion().getIdLocacion());
                    values.add(String.valueOf(entity.getId()));
                    values.add(String.valueOf(detail.getId()));
                    values.add(entity.getTipoGestion().getNombre());
                    values.add(entity.getEstado().getNombre());
                    values.add(entity.getFechaCreacion() == null ? "" : sdf.format(entity.getFechaCreacion()));
                    values.add(entity.getUsuarioCreacion());
                    values.add(entity.getFechaModificacion() == null ? "" : sdf.format(entity.getFechaModificacion()));
                    values.add(entity.getUsuarioModificacion());
                    values.add(entity.getDescripcion());
                    values.add(entity.getReceptor() == null ? "" : entity.getReceptor().getAttribute1());
                    values.add(entity.getReceptor() == null ? "" : entity.getReceptor().getApellidosNombres());
                    values.add(entity.getTipoTraslado());
                    values.add(entity.getComentarios());
                    values.add(String.valueOf(detail.getAssetNumber()));
                    values.add(detail.getSerie());
                    values.add(detail.getModelo());
                    values.add(detail.getLegacyCode());
                    values.add(detail.getEtiqueta());
                    values.add(detail.getDescripcion());
                    values.add(detail.getTipoMotivo());
                    values.add(detail.getMotivo());
                    values.add(String.valueOf(detail.getLocacion() == null ? "" : detail.getLocacion().getIdLocacion()));
                    values.add(detail.getLocacion() == null ? "" : detail.getLocacion().getNombre());
                    values.add(locEntity.getAttribute1());
                    values.add(detail.getAttribute1());

                    rows.add(values.toArray());
                }
            }

            Map<String, Object> config = new HashMap<>();
            config.put("sheetName", "Reporte de Gestiones");
            config.put("filename", "ReporteGestiones.xlsx");
            config.put("columns", Arrays.asList(columns));
            config.put("rows", rows);

            return new ModelAndView(new XlsxViewBuilder(), config);
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourcesNotFoundException();
        }
    }

    public ModelAndView exportXLSX(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::exportXLSX(%s)", this.getClass().getName(), dataTableRequestDto.toString()));

        try {
            Map<String, FilterInfoDto> filtersInfo = new HashMap<>();

            // get column names to filter
            if (dataTableRequestDto.getFiltered() != null) {
                for (FilterInfoDto filterInfo : dataTableRequestDto.getFiltered()) {
                    filtersInfo.put(filterInfo.getId(), filterInfo);
                }
            }

            Specification specialSpec = null;

            // special case - return transfer requests of type location to the analyst
            if (getValueAsLong(filtersInfo, TIPO_GESTION) == 3) {
                if (filtersInfo.containsKey("mode") && getValueAsString(filtersInfo, "mode").equalsIgnoreCase(ANALYST)) {
                    specialSpec = this.<GestionesEntity>getCustomSpecification();
                } else if (filtersInfo.containsKey("mode") && getValueAsString(filtersInfo, "mode").equalsIgnoreCase(MANAGER)
                        || filtersInfo.containsKey("mode") && getValueAsString(filtersInfo, "mode").equalsIgnoreCase(EMPLOYEE)) {
                    specialSpec = Utils.<GestionesEntity, TipoGestionesEntity>getLikeChildSpecification(ESTADO, NOMBRE, getValueAsString(filtersInfo, ESTADO));
                } else {
                    specialSpec = Utils.getConjunction();
                }
            } else {
                specialSpec = Utils.<GestionesEntity, TipoGestionesEntity>getLikeChildSpecification(ESTADO, NOMBRE, getValueAsString(filtersInfo, ESTADO));
            }

            // build filter specifications
            Specification idSpec = Utils.<GestionesEntity>getEqualSpecification("id", getValueAsLong(filtersInfo, "id"));
            Specification createdUserSpec = Utils.<GestionesEntity>getEqualSpecification(USUARIO_CREACION, getValueAsString(filtersInfo, USUARIO_CREACION));
            Specification serialNumberSpec = Utils.<GestionesEntity, GestionesDetalleEntity>getLikeChildSpecification(DETALLE, SERIE, getValueAsString(filtersInfo, SERIE));
            Specification modelSpec = Utils.<GestionesEntity, GestionesDetalleEntity>getLikeChildSpecification(DETALLE, MODELO, getValueAsString(filtersInfo, MODELO));
            Specification legacyCodeSpec = Utils.<GestionesEntity, GestionesDetalleEntity>getLikeChildSpecification(DETALLE, LEGACY_CODE, getValueAsString(filtersInfo, LEGACY_CODE));
            Specification tagNumberSpec = Utils.<GestionesEntity, GestionesDetalleEntity>getLikeChildSpecification(DETALLE, ETIQUETA, getValueAsString(filtersInfo, ETIQUETA));
            Specification descriptionSpec = Utils.<GestionesEntity, GestionesDetalleEntity>getLikeChildSpecification(DETALLE, DESCRIPCION, getValueAsString(filtersInfo, DESCRIPCION));
            Specification receiverSpec = Utils.<GestionesEntity, CpEmpleadosEntity>getEqualChildSpecification(RECEPTOR,"id", getValueAsLong(filtersInfo, ID_RECEPTOR));
            Specification requestTypeSpec = Utils.<GestionesEntity, TipoGestionesEntity>getEqualChildSpecification(TIPO_GESTION, "id", getValueAsLong(filtersInfo, TIPO_GESTION));
            Specification managerSpec = Utils.<GestionesEntity, AdmUsuarioEntity>getEqualChildSpecification("jefe", "id", getValueAsLong(filtersInfo, ID_JEFE));

            // put filter specifications together
            Specification filterSpec = Specifications
                    .where(getDefaultSpecification())
                    .and(idSpec)
                    .and(createdUserSpec)
                    .and(serialNumberSpec)
                    .and(modelSpec)
                    .and(legacyCodeSpec)
                    .and(tagNumberSpec)
                    .and(descriptionSpec)
                    .and(receiverSpec)
                    .and(requestTypeSpec)
                    .and(managerSpec)
                    .and(specialSpec)
                    ;

            List<GestionesEntity> entities = this.requestsRepository.findAll(filterSpec, new Sort(Sort.Direction.ASC, "id"));

            String[] columns = {
                    "NO. GESTIÓN",
                    "NO. DETALLE",
                    "TIPO GESTIÓN",
                    "ESTADO",
                    "FECHA CREACIÓN",
                    "CREADO POR",
                    "FECHA MODIFICACIÓN",
                    "MODIFICADO POR",
                    "DESCRIPCIÓN",
                    "NO. RECEPTOR",
                    "RECEPTOR",
                    "TIPO DE TRASLADO",
                    "ÚLTIMO COMENTARIO",
                    "NO. ACTIVO FIJO",
                    "SERIE",
                    "MODELO",
                    "CÓDIGO LEGADO",
                    "ETIQUETA",
                    "ACTIVO FIJO",
                    "TIPO MOTIVO",
                    "MOTIVO",
                    "NO. LOCACIÓN",
                    "LOCACIÓN",
                    "FA LOCATION CODE",
                    "BOOK TYPE CODE"
            };

            List<Object> rows = new ArrayList<>();

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            for (GestionesEntity entity : entities) {
                for (GestionesDetalleEntity detail : entity.getDetalle()) {
                    List<String> values = new ArrayList<>();
                    CpLocationsEntity locEntity = this.locationsRepository.findOne(detail.getLocacion().getIdLocacion());
                    values.add(String.valueOf(entity.getId()));
                    values.add(String.valueOf(detail.getId()));
                    values.add(entity.getTipoGestion().getNombre());
                    values.add(entity.getEstado().getNombre());
                    values.add(entity.getFechaCreacion() == null ? "" : sdf.format(entity.getFechaCreacion()));
                    values.add(entity.getUsuarioCreacion());
                    values.add(entity.getFechaModificacion() == null ? "" : sdf.format(entity.getFechaModificacion()));
                    values.add(entity.getUsuarioModificacion());
                    values.add(entity.getDescripcion());
                    values.add(entity.getReceptor() == null ? "" : entity.getReceptor().getAttribute1());
                    values.add(entity.getReceptor() == null ? "" : entity.getReceptor().getApellidosNombres());
                    values.add(entity.getTipoTraslado());
                    values.add(entity.getComentarios());
                    values.add(String.valueOf(detail.getAssetNumber()));
                    values.add(detail.getSerie());
                    values.add(detail.getModelo());
                    values.add(detail.getLegacyCode());
                    values.add(detail.getEtiqueta());
                    values.add(detail.getDescripcion());
                    values.add(detail.getTipoMotivo());
                    values.add(detail.getMotivo());
                    values.add(String.valueOf(detail.getLocacion() == null ? "" : detail.getLocacion().getIdLocacion()));
                    values.add(detail.getLocacion() == null ? "" : detail.getLocacion().getNombre());
                    values.add(locEntity.getAttribute1());
                    values.add(detail.getAttribute1());

                    rows.add(values.toArray());
                }
            }

            Map<String, Object> config = new HashMap<>();
            config.put("sheetName", "Reporte de Gestiones");
            config.put("filename", "ReporteGestiones.xlsx");
            config.put("columns", Arrays.asList(columns));
            config.put("rows", rows);

            return new ModelAndView(new XlsxViewBuilder(), config);
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourcesNotFoundException();
        }
    }
}
