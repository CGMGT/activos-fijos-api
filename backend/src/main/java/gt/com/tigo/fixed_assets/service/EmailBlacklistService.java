package gt.com.tigo.fixed_assets.service;

import gt.com.tigo.fixed_assets.dao.portal.EmailBlacklistRepository;
import gt.com.tigo.fixed_assets.dao.portal.UserRepository;
import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.dto.FilterInfoDto;
import gt.com.tigo.fixed_assets.dto.PaginatedDataDto;
import gt.com.tigo.fixed_assets.model.portal.AdmUsuarioEntity;
import gt.com.tigo.fixed_assets.model.portal.EmailBlacklistEntity;
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
public class EmailBlacklistService implements ICatalog<EmailBlacklistEntity>  {
    private static final Logger logger = LoggerFactory.getLogger(EmailBlacklistService.class);

    @Autowired
    private EmailBlacklistRepository blacklistRepository;

    @Autowired
    private UserRepository userRepo;

    @Override
    public List<EmailBlacklistEntity> findAll() throws ResourcesNotFoundException {
        return Collections.emptyList();
    }

    /**
     * Returns a list of all resources in this catalog by page and page size.
     * @param dataTableRequestDto page, sort and filter data.
     * @return a list of objects containing all resources' data.
     * @throws ResourcesNotFoundException when an error occurs.
     */
    @Override
    public PaginatedDataDto<EmailBlacklistEntity> findAllByPage(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
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
            Specification idSpec = Utils.<EmailBlacklistEntity>getEqualSpecification("id", getValueAsLong(filtersInfo, "id"));
            Specification usuarioSpec = Utils.<EmailBlacklistEntity, AdmUsuarioEntity>getEqualChildSpecification("usuario","id", getValueAsLong(filtersInfo, "id"));
            Specification estadoSpec = Utils.<EmailBlacklistEntity>getEqualSpecification("estado", getValueAsString(filtersInfo, "estado"));

            // put filter specifications together
            Specification filterSpec = Specifications
                    .where(getDefaultSpecification())
                    .and(idSpec)
                    .and(usuarioSpec)
                    .and(estadoSpec)
                    ;

            Page<EmailBlacklistEntity> pageData;

            // if sort was not requested then use the default order
            if (dataTableRequestDto.getSorted() == null || dataTableRequestDto.getSorted().isEmpty()) {
                pageData = this.blacklistRepository.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Sort.Direction.ASC,"id"));
            } else { // else use the requested order
                pageData = this.blacklistRepository.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Boolean.TRUE.equals(dataTableRequestDto.getSorted().get(0).getDesc()) ? Sort.Direction.DESC : Sort.Direction.ASC, dataTableRequestDto.getSorted().get(0).getId()));
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
    public EmailBlacklistEntity findById(Long id) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findById(%d)", this.getClass().getName(), id));

        EmailBlacklistEntity entidad = this.blacklistRepository.findOne(id);

        if (entidad == null) {
            throw new ResourceNotFoundException();
        }

        return entidad;
    }

    @Override
    public EmailBlacklistEntity create(EmailBlacklistEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceCreateException {
        logger.debug(String.format("@%s::create(%s, %d)", this.getClass().getName(), entity, requesterId));

        AdmUsuarioEntity requester = this.userRepo.findOne(requesterId);

        if (requester == null) {
            throw new RequesterNotFoundException();
        }

        entity.setEstado("A");
        entity.setUsuarioCreacion(requester.getUsuario());
        entity.setFechaCreacion(new Timestamp(new Date().getTime()));

        try {
            return this.blacklistRepository.save(entity);
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourceCreateException();
        }
    }

    @Override
    public EmailBlacklistEntity update(EmailBlacklistEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceUpdateException {
        logger.debug(String.format("@%s::update(%s, %d)", this.getClass().getName(), entity, requesterId));

        AdmUsuarioEntity requester = this.userRepo.findOne(requesterId);

        if (requester == null) {
            throw new RequesterNotFoundException();
        }

        entity.setUsuarioModificacion(requester.getUsuario());
        entity.setFechaModificacion(new Timestamp(new Date().getTime()));

        try {
            return this.blacklistRepository.save(entity);
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourceUpdateException();
        }
    }

    @Override
    public EmailBlacklistEntity delete(Long entityId, Long requesterId) throws RequesterNotFoundException, ResourceNotFoundException, ResourceUpdateException {
        logger.debug(String.format("@%s::delete(%d, %d)", this.getClass().getName(), entityId, requesterId));

        AdmUsuarioEntity requester = this.userRepo.findOne(requesterId);

        if (requester == null) {
            throw new RequesterNotFoundException();
        }

        EmailBlacklistEntity entity = this.blacklistRepository.findOne(entityId);

        if (entity == null) {
            throw new ResourceNotFoundException();
        }


        entity.setEstado("I");
        entity.setUsuarioModificacion(requester.getUsuario());
        entity.setFechaModificacion(new Timestamp(new Date().getTime()));

        try {
            return this.blacklistRepository.save(entity);
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourceUpdateException();
        }
    }
}
