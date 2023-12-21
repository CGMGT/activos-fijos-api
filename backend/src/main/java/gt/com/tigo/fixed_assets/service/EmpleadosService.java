package gt.com.tigo.fixed_assets.service;

import gt.com.tigo.fixed_assets.dao.portal.*;
import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.dto.FilterInfoDto;
import gt.com.tigo.fixed_assets.dto.PaginatedDataDto;
import gt.com.tigo.fixed_assets.model.portal.*;
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

import java.math.BigDecimal;
import java.util.*;

import static gt.com.tigo.fixed_assets.util.Utils.*;

@Service
public class EmpleadosService implements ICatalog<CpEmpleadosEntity>{
    private static final Logger logger = LoggerFactory.getLogger(EmpleadosService.class);
    public static final String EMP_CODIGO = "empCodigo";
    public static final String APELLIDOS_NOMBRES = "apellidosNombres";
    public static final String NOMBRES_APELLIDOS = "nombresApellidos";
    public static final String EMAIL_COLABORADOR = "emailColaborador";
    public static final String NO_STAFF = "noStaff";
    public static final String PLAZA = "plaza";
    public static final String UNIDAD_ADMINISTRATIVA = "unidadAdministrativa";
    public static final String AREA_FUNCIONAL = "areaFuncional";
    public static final String UNIDAD_DE_NEGOCIO = "unidadDeNegocio";
    public static final String EMAIL_JEFE_INMEDIATO = "emailJefeInmediato";

    @Autowired
    private EmpleadosRepository employeesRepository;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ActivosFijosService fixedAssetRepo;

    @Autowired
    private GestionesService requestService;

    @Autowired
    private TipoGestionesRepository requestTypeRepo;

    @Autowired
    private EstadosAprobacionRepository statusRepo;

    @Autowired
    private GestionesRepository requestsRepository;

    @Autowired
    private EstadosAprobacionRepository requestStatusRepository;

    /**
     * Returns a list of all resources in this catalog.
     * @return a list of objects containing all resources' data.
     * @throws ResourcesNotFoundException when an error occurs.
     */
    @Override
    public List<CpEmpleadosEntity> findAll() throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::findAll()", this.getClass().getName()));

        try {
            return (List<CpEmpleadosEntity>) this.employeesRepository.findAll();
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
    public PaginatedDataDto<CpEmpleadosEntity> findAllByPage(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
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
            Specification empCodigoSpec = getLikeSpecification(EMP_CODIGO, filtersInfo.get(EMP_CODIGO) == null ? null : filtersInfo.get(EMP_CODIGO).getValue());
            Specification apellidosNombresSpec = getLikeSpecification(APELLIDOS_NOMBRES, filtersInfo.get(APELLIDOS_NOMBRES) == null ? null : filtersInfo.get(APELLIDOS_NOMBRES).getValue());
            Specification nombresApellidosSpec = getLikeSpecification(NOMBRES_APELLIDOS, filtersInfo.get(NOMBRES_APELLIDOS) == null ? null : filtersInfo.get(NOMBRES_APELLIDOS).getValue());
            Specification emailColaboradorSpec = getLikeSpecification(EMAIL_COLABORADOR, filtersInfo.get(EMAIL_COLABORADOR) == null ? null : filtersInfo.get(EMAIL_COLABORADOR).getValue());
            Specification noStaffSpec = getLikeSpecification(NO_STAFF, filtersInfo.get(NO_STAFF) == null ? null : filtersInfo.get(NO_STAFF).getValue());
            Specification plazaSpec = getLikeSpecification(PLAZA, filtersInfo.get(PLAZA) == null ? null : filtersInfo.get(PLAZA).getValue());
            Specification unidadAdministrativaSpec = getLikeSpecification(UNIDAD_ADMINISTRATIVA, filtersInfo.get(UNIDAD_ADMINISTRATIVA) == null ? null : filtersInfo.get(UNIDAD_ADMINISTRATIVA).getValue());
            Specification areaFuncionalSpec = getLikeSpecification(AREA_FUNCIONAL, filtersInfo.get(AREA_FUNCIONAL) == null ? null : filtersInfo.get(AREA_FUNCIONAL).getValue());
            Specification unidadDeNegocioSpec = getLikeSpecification(UNIDAD_DE_NEGOCIO, filtersInfo.get(UNIDAD_DE_NEGOCIO) == null ? null : filtersInfo.get(UNIDAD_DE_NEGOCIO).getValue());
            Specification emailJefeInmediatoSpec = getEqualSpecification(EMAIL_JEFE_INMEDIATO, filtersInfo.get(EMAIL_JEFE_INMEDIATO) == null ? null : filtersInfo.get(EMAIL_JEFE_INMEDIATO).getValue());

            // put filter specifications together
            Specification filterSpec = Specifications
                    .where(getDefaultSpecification())
                    .and(empCodigoSpec)
                    .and(apellidosNombresSpec)
                    .and(nombresApellidosSpec)
                    .and(emailColaboradorSpec)
                    .and(noStaffSpec)
                    .and(plazaSpec)
                    .and(unidadAdministrativaSpec)
                    .and(areaFuncionalSpec)
                    .and(unidadDeNegocioSpec)
                    .and(emailJefeInmediatoSpec);

            Page<CpEmpleadosEntity> pageData;

            // if sort was not requested then use the default order
            if (dataTableRequestDto.getSorted() == null || dataTableRequestDto.getSorted().isEmpty()) {
                pageData = this.employeesRepository.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Sort.Direction.ASC, APELLIDOS_NOMBRES));
            } else { // else use the requested order
                pageData = this.employeesRepository.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Boolean.TRUE.equals(dataTableRequestDto.getSorted().get(0).getDesc()) ? Sort.Direction.DESC : Sort.Direction.ASC, dataTableRequestDto.getSorted().get(0).getId()));
            }

            // return a transfer object containing the rows, total rows and total pages
            return new PaginatedDataDto<>(pageData.getContent(), pageData.getTotalElements(), pageData.getTotalPages());
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourcesNotFoundException();
        }
    }

    /**
     * Returns a resource by id.
     * @param id The id of the requested resource
     * @return an object containing the found resource.
     * @throws ResourceNotFoundException when no record is found.
     */
    @Override
    public CpEmpleadosEntity findById(Long id) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findById(%d)", this.getClass().getName(), id));

        CpEmpleadosEntity entidad = this.employeesRepository.findOne(id);

        if (entidad == null) {
            throw new ResourceNotFoundException();
        }

        return entidad;
    }

    /**
     * Returns a resource by id.
     * @param emailColaborador The email of the requested resource
     * @return an object containing the found resource.
     * @throws ResourceNotFoundException when no record is found.
     */
    public CpEmpleadosEntity findByEmailColaborador(String emailColaborador) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findByEmailColaborador(%s)", this.getClass().getName(), emailColaborador));

        CpEmpleadosEntity entidad = this.employeesRepository.findByEmailColaborador(emailColaborador);

        if (entidad == null) {
            throw new ResourceNotFoundException();
        }

        return entidad;
    }

    @Override
    public CpEmpleadosEntity create(CpEmpleadosEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceCreateException {
        return null;
    }

    @Override
    public CpEmpleadosEntity update(CpEmpleadosEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceUpdateException {
        return null;
    }

    @Override
    public CpEmpleadosEntity delete(Long entityId, Long requesterId) throws RequesterNotFoundException, ResourceNotFoundException, ResourceUpdateException {
        return null;
    }

    /**
     * Create a new transfer request containing all fixed assets for a disabled employee.
     * @param employeeId The employee identifier to disable.
     * @param requesterId The manager identifier.
     * @return A new request object.
     * @throws RequesterNotFoundException When the requester is not found.
     * @throws ResourceNotFoundException When the employee is not found.
     * @throws ResourcesNotFoundException When the fixed assets for the employee are not found.
     */
    public GestionesEntity disable(Long employeeId, Long requesterId) throws RequesterNotFoundException, ResourceNotFoundException, ResourcesNotFoundException, ResourceCreateException {
        logger.debug(String.format("@%s::disable(%d, %d)", this.getClass().getName(), employeeId, requesterId));

        AdmUsuarioEntity requester = this.userRepo.findOne(requesterId);

        if (requester == null) {
            throw new RequesterNotFoundException();
        }

        CpEmpleadosEntity receptor = this.findByEmailColaborador(requester.getCorreoElectronico());

        CpEmpleadosEntity employee = this.findById(employeeId);

        // disable user in adm_usuarios table
        AdmUsuarioEntity user = this.userRepo.findByCorreoElectronico(employee.getEmailColaborador());

        if (user == null) {
            throw new ResourceNotFoundException();
        }

        user.setAttribute2(String.valueOf(1));

        this.userRepo.save(user);

        // disable employee in cp_colaboradores table
        employee.setAttribute2(String.valueOf(1));

        this.employeesRepository.save(employee);

        // reject all open requests
        List<BigDecimal> openRequestsIds = this.requestsRepository.getOpenRequestsByUsuarioCreacion(user.getCorreoElectronico());

        EstadosAprobacionEntity rejectedStatus = this.requestStatusRepository.findByNombre("Rechazada");

        for (BigDecimal openRequestId : openRequestsIds) {
            GestionesEntity request = this.requestsRepository.findOne(openRequestId.longValue());

            request.setEstado(rejectedStatus);

            this.requestsRepository.save(request);
        }

        // build filter to get all employee's fixed assets
        List<FilterInfoDto> filters = new ArrayList<>();
        filters.add(new FilterInfoDto("userId", String.valueOf(user.getId())));

        DataTableRequestDto params = new DataTableRequestDto();
        params.setPage(0);
        params.setPageSize(100);
        params.setFiltered(filters);

        // get all employee's fixed assets
        PaginatedDataDto<CpActivosFijosEntity> fixedAssetsPaginatedData = this.fixedAssetRepo.findAllByPageByUserId(params);

        if (fixedAssetsPaginatedData == null) {
            throw new ResourcesNotFoundException();
        }

        // build request detail with all fixed assets
        List<GestionesDetalleEntity> requestDetails = new ArrayList<>();

        for (CpActivosFijosEntity fixedAsset : fixedAssetsPaginatedData.getRows()) {
            GestionesDetalleEntity requestDetail = new GestionesDetalleEntity();
            requestDetail.setAssetNumber(fixedAsset.getAssetNumber());
            requestDetail.setSerie(fixedAsset.getSerialNumber());
            requestDetail.setModelo(fixedAsset.getModelNumber());
            requestDetail.setLegacyCode(fixedAsset.getLegacycode());
            requestDetail.setEtiqueta(fixedAsset.getTagNumber());
            requestDetail.setDescripcion(fixedAsset.getDescription());
            requestDetail.setLocacion(fixedAsset.getLocacion());

            requestDetails.add(requestDetail);
        }

        // build request header
        GestionesEntity request = new GestionesEntity();
        request.setDescripcion("Traslado automático por baja de colaborador.");
        request.setReceptor(receptor);
        request.setJefe(requester);
        request.setTipoTraslado("Colaborador");
        request.setDetalle(requestDetails);
        request.setTipoGestion(this.requestTypeRepo.findByNombre("TRASLADO"));
        request.setEstado(this.statusRepo.findByNombre("Aprobada por receptor"));

        // create request
        request = this.requestService.create(request, requester.getId());

        return request;
    }

    public ModelAndView exportAsXLSX() throws ResourcesNotFoundException {
        List<CpEmpleadosEntity> entities = this.employeesRepository.findAll();

        if (entities == null) {
            throw new ResourcesNotFoundException();
        }

        String[] columns = {
                "ID",
                "CÓDIGO EMPLEADO",
                "CÓDIGO EBS",
                "NOMBRES/APELLIDOS",
                "EMAIL",
                "NO. STAFF",
                "PLAZA",
                "NOMBRE JEFE INMEDIATO",
                "EMAIL JEFE INMEDIATO",
                "UNIDAD ADMINISTRATIVA",
                "ÁREA FUNCIONAL",
                "UNIDAD DE NEGOCIO",
                "CÓDIGO JEFE"
        };

        List<Object> rows = new ArrayList<>();

        for (CpEmpleadosEntity entity : entities) {
            List<String> values = new ArrayList<>();

            values.add(String.valueOf(entity.getId()));
            values.add(String.valueOf(entity.getEmpCodigo()));
            values.add(entity.getAttribute1());
            values.add(entity.getApellidosNombres());
            values.add(entity.getEmailColaborador());
            values.add(entity.getNoStaff());
            values.add(entity.getPlaza());
            values.add(entity.getNombreJefeInmediato());
            values.add(entity.getEmailJefeInmediato());
            values.add(entity.getUnidadAdministrativa());
            values.add(entity.getAreaFuncional());
            values.add(entity.getUnidadDeNegocio());
            values.add(String.valueOf(entity.getCodigoJefe()));

            rows.add(values.toArray());
        }

        Map<String, Object> config = new HashMap<>();
        config.put("sheetName", "Colaboradores");
        config.put("filename", "Colaboradores.xlsx");
        config.put("columns", Arrays.asList(columns));
        config.put("rows", rows);

        return new ModelAndView(new XlsxViewBuilder(), config);
    }
}
