package gt.com.tigo.fixed_assets.service;

import gt.com.tigo.fixed_assets.dao.portal.HistProcesosRepository;
import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.dto.FilterInfoDto;
import gt.com.tigo.fixed_assets.dto.PaginatedDataDto;
import gt.com.tigo.fixed_assets.model.portal.HistProcesosEntity;
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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static gt.com.tigo.fixed_assets.util.Utils.*;
import static gt.com.tigo.fixed_assets.util.Utils.getValueAsString;

@Service
public class HistProcesosService implements ICatalog<HistProcesosEntity> {
    private static final Logger logger = LoggerFactory.getLogger(HistProcesosService.class);
    public static final String ID = "id";
    public static final String NOMBRE = "nombre";
    public static final String DESCRIPCION = "descripcion";

    @Autowired
    private HistProcesosRepository processRepository;

    @Override
    public List<HistProcesosEntity> findAll() throws ResourcesNotFoundException {
        return Collections.emptyList();
    }

    /**
     * Returns a list of all resources in this catalog by page and page size.
     * @param dataTableRequestDto page, sort and filter data.
     * @return a list of objects containing all resources' data.
     * @throws ResourcesNotFoundException when an error occurs.
     */
    @Override
    public PaginatedDataDto<HistProcesosEntity> findAllByPage(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
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
            Specification idSpec = Utils.<HistProcesosEntity>getEqualSpecification(ID, getValueAsLong(filtersInfo, ID));
            Specification nameSpec = Utils.<HistProcesosEntity>getLikeSpecification(NOMBRE, getValueAsString(filtersInfo, NOMBRE));
            Specification descriptionSpec = Utils.<HistProcesosEntity>getLikeSpecification(DESCRIPCION, getValueAsString(filtersInfo, DESCRIPCION));

            // put filter specifications together
            Specification filterSpec = Specifications
                    .where(getDefaultSpecification())
                    .and(idSpec)
                    .and(nameSpec)
                    .and(descriptionSpec);

            Page<HistProcesosEntity> pageData;

            // if sort was not requested then use the default order
            if (dataTableRequestDto.getSorted() == null || dataTableRequestDto.getSorted().isEmpty()) {
                pageData = this.processRepository.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Sort.Direction.DESC, ID));
            } else { // else use the requested order
                pageData = this.processRepository.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Boolean.TRUE.equals(dataTableRequestDto.getSorted().get(0).getDesc()) ? Sort.Direction.DESC : Sort.Direction.ASC, dataTableRequestDto.getSorted().get(0).getId()));
            }

            // return a transfer object containing the rows, total rows and total pages
            return new PaginatedDataDto<>(pageData.getContent(), pageData.getTotalElements(), pageData.getTotalPages());
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourcesNotFoundException();
        }
    }

    @Override
    public HistProcesosEntity findById(Long id) throws ResourceNotFoundException {
        return null;
    }

    public Long executeProcess(String proceso, String usuario, String descripcion) throws  ResourceNotFoundException {
        logger.debug(String.format("@%s::executeProcess(%s, %s, %s)", this.getClass().getName(), proceso, usuario, descripcion));

        Long idProceso = processRepository.executeProcess(proceso, usuario, descripcion);

        if (idProceso == null) {
            throw new ResourceNotFoundException();
        }

        logger.info(String.format("@%s::executeProcess(%s, %s, %s) - Synchronization process %d initiated.", this.getClass().getName(), proceso, usuario, descripcion, idProceso));

        return idProceso;
    }

    @Override
    public HistProcesosEntity create(HistProcesosEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceCreateException {
        return null;
    }

    @Override
    public HistProcesosEntity update(HistProcesosEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceUpdateException {
        return null;
    }

    @Override
    public HistProcesosEntity delete(Long entityId, Long requesterId) throws RequesterNotFoundException, ResourceNotFoundException, ResourceUpdateException {
        return null;
    }

    /**
     * Returns a list of all resources in this catalog by page and page size.
     * @param dataTableRequestDto page, sort and filter data.
     * @return a list of objects containing all resources' data.
     * @throws ResourcesNotFoundException when an error occurs.
     */
    public PaginatedDataDto<HistProcesosEntity> report(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
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
            Specification idSpec = Utils.<HistProcesosEntity>getEqualSpecification(ID, getValueAsLong(filtersInfo, ID));
            Specification nameSpec = Utils.<HistProcesosEntity>getLikeSpecification(NOMBRE, getValueAsString(filtersInfo, NOMBRE));
            Specification descriptionSpec = Utils.<HistProcesosEntity>getLikeSpecification(DESCRIPCION, getValueAsString(filtersInfo, DESCRIPCION));

            // put filter specifications together
            Specification filterSpec = Specifications
                    .where(getDefaultSpecification())
                    .and(idSpec)
                    .and(nameSpec)
                    .and(descriptionSpec);

            Page<HistProcesosEntity> pageData;

            // if sort was not requested then use the default order
            if (dataTableRequestDto.getSorted() == null || dataTableRequestDto.getSorted().isEmpty()) {
                pageData = this.processRepository.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Sort.Direction.DESC, ID));
            } else { // else use the requested order
                pageData = this.processRepository.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Boolean.TRUE.equals(dataTableRequestDto.getSorted().get(0).getDesc()) ? Sort.Direction.DESC : Sort.Direction.ASC, dataTableRequestDto.getSorted().get(0).getId()));
            }

            // return a transfer object containing the rows, total rows and total pages
            return new PaginatedDataDto<>(pageData.getContent(), pageData.getTotalElements(), pageData.getTotalPages());
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourcesNotFoundException();
        }
    }
}
