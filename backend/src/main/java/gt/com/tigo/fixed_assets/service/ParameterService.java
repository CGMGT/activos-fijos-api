package gt.com.tigo.fixed_assets.service;

import gt.com.tigo.fixed_assets.dao.portal.ParameterRepository;
import gt.com.tigo.fixed_assets.dao.portal.UserRepository;
import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.dto.FilterInfoDto;
import gt.com.tigo.fixed_assets.dto.PaginatedDataDto;
import gt.com.tigo.fixed_assets.model.portal.AdmParametrosEntity;
import gt.com.tigo.fixed_assets.model.portal.AdmUsuarioEntity;
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

import static gt.com.tigo.fixed_assets.util.Utils.getDefaultSpecification;

@Service
public class ParameterService implements ICatalog<AdmParametrosEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterService.class);

    @Autowired
    private ParameterRepository paramRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public List<AdmParametrosEntity> findAll() throws ResourcesNotFoundException {
        return this.paramRepo.findAll(new Sort(Sort.Direction.ASC, "nombreMostrado"));
    }

    @Override
    public PaginatedDataDto<AdmParametrosEntity> findAllByPage(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
        LOGGER.debug(String.format("@%s::findAllByPage(%s)", this.getClass().getName(), dataTableRequestDto.toString()));

        try {
            Map<String, FilterInfoDto> filtersInfo = new HashMap<>();

            // get column names to filter
            if (dataTableRequestDto.getFiltered() != null) {
                for (FilterInfoDto filterInfo : dataTableRequestDto.getFiltered()) {
                    filtersInfo.put(filterInfo.getId(), filterInfo);
                }
            }

            // build filter specifications

            // put filter specifications together
            Specification filterSpec = Specifications
                    .where(getDefaultSpecification());

            Page<AdmParametrosEntity> pageData;

            // if sort was not requested then use the default order
            if (dataTableRequestDto.getSorted() == null || dataTableRequestDto.getSorted().isEmpty()) {
                pageData = this.paramRepo.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Sort.Direction.ASC,"nombreMostrado"));
            } else { // else use the requested order
                pageData = this.paramRepo.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Boolean.TRUE.equals(dataTableRequestDto.getSorted().get(0).getDesc()) ? Sort.Direction.DESC : Sort.Direction.ASC, dataTableRequestDto.getSorted().get(0).getId()));
            }

            // return a transfer object containing the rows, total rows and total pages
            return new PaginatedDataDto<>(pageData.getContent(), pageData.getTotalElements(), pageData.getTotalPages());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());

            throw new ResourcesNotFoundException();
        }
    }

    @Override
    public AdmParametrosEntity findById(Long id) throws ResourceNotFoundException {
        return null;
    }

    public AdmParametrosEntity findByNombre(String name) throws ResourceNotFoundException {
        LOGGER.debug(String.format("@%s::findByNombre(%s)", this.getClass().getName(), name));

        try {
            return this.paramRepo.findByNombre(name);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());

            throw new ResourceNotFoundException();
        }
    }

    @Override
    public AdmParametrosEntity create(AdmParametrosEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceCreateException {
        return null;
    }

    @Override
    public AdmParametrosEntity update(AdmParametrosEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceUpdateException {
        LOGGER.debug(String.format("@%s::update(%s, %d)", this.getClass().getName(), entity, requesterId));

        AdmUsuarioEntity requester = this.userRepo.findOne(requesterId);

        if (requester == null) {
            throw new RequesterNotFoundException();
        }

        entity.setUsuarioModificacion(requester.getUsuario());
        entity.setFechaModificacion(new Timestamp(new Date().getTime()));

        try {
            return this.paramRepo.save(entity);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());

            throw new ResourceUpdateException();
        }
    }

    @Override
    public AdmParametrosEntity delete(Long entityId, Long requesterId) throws RequesterNotFoundException, ResourceNotFoundException, ResourceUpdateException {
        return null;
    }
}
