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
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import static gt.com.tigo.fixed_assets.util.Utils.*;

@Service
public class ConfirmacionesService implements ICatalog<ConfirmacionesEntity> {
    private static final Logger logger = LoggerFactory.getLogger(ConfirmacionesService.class);
    public static final String ID = "id";
    public static final String USUARIO = "usuario";
    public static final String ASSET_NUMBER = "assetNumber";
    public static final String ESTADO = "estado";

    @Autowired
    private ConfirmacionesRepository confirmationsRepository;

    @Autowired
    private EmpleadosRepository employeeRepo;

    @Autowired
    private LocacionesRepository locationsRepo;

    @Override
    public List<ConfirmacionesEntity> findAll() throws ResourcesNotFoundException {
        return Collections.emptyList();
    }

    /**
     * Returns a list of all resources in this catalog by page and page size.
     * @param dataTableRequestDto page, sort and filter data.
     * @return a list of objects containing all resources' data.
     * @throws ResourcesNotFoundException when an error occurs.
     */
    @Override
    public PaginatedDataDto<ConfirmacionesEntity> findAllByPage(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::findConfirmacionesByPage(%s)", this.getClass().getName(), dataTableRequestDto.toString()));

        try {
            Map<String, FilterInfoDto> filtersInfo = new HashMap<>();

            // get column names to filter
            if (dataTableRequestDto.getFiltered() != null) {
                for (FilterInfoDto filterInfo : dataTableRequestDto.getFiltered()) {
                    filtersInfo.put(filterInfo.getId(), filterInfo);
                }
            }

            // build filter specifications
            Specification idSpec = Utils.<ConfirmacionesEntity>getEqualSpecification(ID, getValueAsLong(filtersInfo, ID));
            Specification userIdSpec = Utils.<ConfirmacionesEntity, AdmUsuarioEntity>getEqualChildSpecification(USUARIO, ID, getValueAsLong(filtersInfo, "userId"));
            Specification userSpec = Utils.<ConfirmacionesEntity, AdmUsuarioEntity>getLikeChildSpecification(USUARIO, USUARIO, getValueAsString(filtersInfo, USUARIO));
            Specification assetNumberSpec = Utils.<ConfirmacionesEntity, CpActivosFijosEntity>getEqualChildSpecification("activoFijo", ASSET_NUMBER, getValueAsLong(filtersInfo, ASSET_NUMBER));
            Specification estadoSpec = Utils.<ConfirmacionesEntity>getEqualSpecification(ESTADO, getValueAsString(filtersInfo, ESTADO));

            // put filter specifications together
            Specification filterSpec = Specifications
                    .where(getDefaultSpecification())
                    .and(userIdSpec)
                    .and(idSpec)
                    .and(userSpec)
                    .and(assetNumberSpec)
                    .and(estadoSpec)
                    ;

            Page<ConfirmacionesEntity> pageData;

            // if sort was not requested then use the default order
            if (dataTableRequestDto.getSorted() == null || dataTableRequestDto.getSorted().isEmpty()) {
                pageData = this.confirmationsRepository.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Sort.Direction.ASC, ID));
            } else { // else use the requested order
                pageData = this.confirmationsRepository.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Boolean.TRUE.equals(dataTableRequestDto.getSorted().get(0).getDesc()) ? Sort.Direction.DESC : Sort.Direction.ASC, dataTableRequestDto.getSorted().get(0).getId()));
            }

            // return a transfer object containing the rows, total rows and total pages
            return new PaginatedDataDto<>(pageData.getContent(), pageData.getTotalElements(), pageData.getTotalPages());
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourcesNotFoundException();
        }
    }

    @Override
    public ConfirmacionesEntity findById(Long id) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public ConfirmacionesEntity create(ConfirmacionesEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceCreateException {
        return null;
    }

    @Override
    public ConfirmacionesEntity update(ConfirmacionesEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceUpdateException {
        logger.debug(String.format("@%s::update(%s, %d)", this.getClass().getName(), entity, requesterId));

        entity.setFechaActualiza(new Timestamp(new Date().getTime()));

        try {
            return this.confirmationsRepository.save(entity);
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourceUpdateException();
        }
    }

    @Override
    public ConfirmacionesEntity delete(Long entityId, Long requesterId) throws RequesterNotFoundException, ResourceNotFoundException, ResourceUpdateException {
        return null;
    }

    /**
     * Returns a list of all resources in this catalog by page and page size.
     * @param dataTableRequestDto page, sort and filter data.
     * @return a list of objects containing all resources' data.
     * @throws ResourcesNotFoundException when an error occurs.
     */
    public PaginatedDataDto<ConfirmacionesEntity> report(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
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
            Specification periodSpec = Utils.<ConfirmacionesEntity, NotificacionesEntity>getLikeChildSpecification("idNotificacion", "periodo", getValueAsString(filtersInfo, "period"));
            Specification userSpec = Utils.<ConfirmacionesEntity, AdmUsuarioEntity>getLikeChildSpecification(USUARIO, "nombreMostrado", getValueAsString(filtersInfo, "user"));

            // put filter specifications together
            Specification filterSpec = Specifications
                    .where(getDefaultSpecification())
                    .and(userSpec)
                    .and(periodSpec)
                    ;

            Page<ConfirmacionesEntity> pageData;

            // if sort was not requested then use the default order
            if (dataTableRequestDto.getSorted() == null || dataTableRequestDto.getSorted().isEmpty()) {
                pageData = this.confirmationsRepository.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Sort.Direction.ASC, ID));
            } else { // else use the requested order
                pageData = this.confirmationsRepository.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Boolean.TRUE.equals(dataTableRequestDto.getSorted().get(0).getDesc()) ? Sort.Direction.DESC : Sort.Direction.ASC, dataTableRequestDto.getSorted().get(0).getId()));
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
            Specification periodSpec = Utils.<ConfirmacionesEntity, NotificacionesEntity>getLikeChildSpecification("idNotificacion", "periodo", getValueAsString(filtersInfo, "period"));
            Specification userSpec = Utils.<ConfirmacionesEntity, AdmUsuarioEntity>getLikeChildSpecification(USUARIO, "nombreMostrado", getValueAsString(filtersInfo, "user"));

            // put filter specifications together
            Specification filterSpec = Specifications
                    .where(getDefaultSpecification())
                    .and(userSpec)
                    .and(periodSpec)
                    ;

            List<ConfirmacionesEntity> entities = this.confirmationsRepository.findAll(filterSpec, new Sort(Sort.Direction.ASC, ID));

            String[] columns = {
                    "NO. NOTIFICACIÓN",
                    "NO. CONFIRMACIÓN",
                    "PERÍODO",
                    "CÓDIGO COLABORADOR",
                    "COLABORADOR",
                    "NO. ACTIVO FIJO",
                    "SERIE",
                    "LIBRO",
                    "DESCRIPCIÓN",
                    "MODELO",
                    "CÓDIGO LEGADO",
                    "LOCACIÓN",
                    "NO. LOCACIÓN CONFIRMADA",
                    "LOCACIÓN CONFIRMADA",
                    "ESTADO",
                    "FECHA CONFIRMACIÓN",
                    "COMENTARIOS"
            };

            List<Object> rows = new ArrayList<>();

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            for (ConfirmacionesEntity entity : entities) {
                List<String> values = new ArrayList<>();

                values.add(String.valueOf(entity.getIdNotificacion().getId()));
                values.add(String.valueOf(entity.getId()));
                values.add(entity.getIdNotificacion().getPeriodo());
                values.add(entity.getUsuario().getAttribute1());
                values.add(entity.getUsuario().getNombreMostrado());
                values.add(String.valueOf(entity.getActivoFijo().getAssetNumber()));
                values.add(entity.getActivoFijo().getSerialNumber());
                values.add(entity.getActivoFijo().getBookTypeCode());
                values.add(entity.getActivoFijo().getDescription());
                values.add(entity.getActivoFijo().getModelNumber());
                values.add(entity.getActivoFijo().getLegacycode());
                values.add(entity.getActivoFijo().getLocacion() == null ? "" : entity.getActivoFijo().getLocacion().getNombre());
                values.add(entity.getAttribute4() == null ? "" : this.locationsRepo.findByNombre(entity.getAttribute4()).getAttribute1());
                values.add(entity.getAttribute4() == null ? "" : entity.getAttribute4());
                values.add(getStatus(entity.getEstado()));
                values.add(entity.getFechaActualiza() == null ? "" : sdf.format(entity.getFechaActualiza()));
                values.add(entity.getComentarios());

                rows.add(values.toArray());
            }

            Map<String, Object> config = new HashMap<>();
            config.put("sheetName", "Reporte de Confirmaciones");
            config.put("filename", "ReporteConfirmaciones.xlsx");
            config.put("columns", Arrays.asList(columns));
            config.put("rows", rows);

            return new ModelAndView(new XlsxViewBuilder(), config);
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourcesNotFoundException();
        }
    }

    private String getStatus(String status) {
        if (status.equals("S")) {
            return "Sin confirmar";
        }

        return status.equals("C") ? "Confirmado" : "Rechazado";
    }
}
