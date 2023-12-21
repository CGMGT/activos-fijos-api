package gt.com.tigo.fixed_assets.service;

import gt.com.tigo.fixed_assets.dao.portal.EmailTemplateRepository;
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
public class EmailTemplateService implements ICatalog<EmailTemplateEntity>{

    private static final Logger logger = LoggerFactory.getLogger(EmailTemplateService.class);
    public static final String NOMBRE = "nombre";
    public static final String ID = "id";
    public static final String DESCRIPCION = "descripcion";

    @Autowired
    private EmailTemplateRepository emailTemplateRepository;

    @Autowired
    private UserRepository userRepo;

    /**
     * Returns a list of all resources in this catalog.
     * @return a list of objects containing all resources' data.
     * @throws ResourcesNotFoundException when an error occurs.
     */
    @Override
    public List<EmailTemplateEntity> findAll() throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::findAll()", this.getClass().getName()));

        try {
            return this.emailTemplateRepository.findAll(new Sort(Sort.Direction.ASC, ID));
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourcesNotFoundException();
        }
    }

    @Override
    public PaginatedDataDto<EmailTemplateEntity> findAllByPage(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
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
            Specification idSpec = Utils.<EmailTemplateEntity>getEqualSpecification(ID, getValueAsLong(filtersInfo, ID));
            Specification nameSpec = Utils.<EmailTemplateEntity>getLikeSpecification(NOMBRE, getValueAsString(filtersInfo, NOMBRE));
            Specification descriptionSpec = Utils.<EmailTemplateEntity>getLikeSpecification(DESCRIPCION, getValueAsString(filtersInfo, DESCRIPCION));

            // put filter specifications together
            Specification filterSpec = Specifications
                    .where(getDefaultSpecification())
                    .and(idSpec)
                    .and(nameSpec)
                    .and(descriptionSpec)
                    ;

            Page<EmailTemplateEntity> pageData;

            // if sort was not requested then use the default order
            if (dataTableRequestDto.getSorted() == null || dataTableRequestDto.getSorted().isEmpty()) {
                pageData = this.emailTemplateRepository.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Sort.Direction.ASC, NOMBRE));
            } else { // else use the requested order
                pageData = this.emailTemplateRepository.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Boolean.TRUE.equals(dataTableRequestDto.getSorted().get(0).getDesc()) ? Sort.Direction.DESC : Sort.Direction.ASC, dataTableRequestDto.getSorted().get(0).getId()));
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
    public EmailTemplateEntity findById(Long id) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findById(%d)", this.getClass().getName(), id));

        EmailTemplateEntity entity = this.emailTemplateRepository.findOne(id);

        if (entity == null) {
            throw new ResourceNotFoundException();
        }

        return entity;
    }

    @Override
    public EmailTemplateEntity create(EmailTemplateEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceCreateException {
        logger.debug(String.format("@%s::create(%s, %d)", this.getClass().getName(), entity, requesterId));

        AdmUsuarioEntity requester = this.userRepo.findOne(requesterId);

        if (requester == null) {
            throw new RequesterNotFoundException();
        }

        entity.setUsuarioCreacion(requester.getUsuario());
        entity.setFechaCreacion(new Timestamp(new Date().getTime()));

        try {
            return this.emailTemplateRepository.save(entity);
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourceCreateException();
        }
    }

    @Override
    public EmailTemplateEntity update(EmailTemplateEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceUpdateException {
        logger.debug(String.format("@%s::update(%s, %d)", this.getClass().getName(), entity, requesterId));

        AdmUsuarioEntity requester = this.userRepo.findOne(requesterId);

        if (requester == null) {
            throw new RequesterNotFoundException();
        }

        entity.setUsuarioModificacion(requester.getUsuario());
        entity.setFechaModificacion(new Timestamp(new Date().getTime()));


        try {
            return this.emailTemplateRepository.save(entity);
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourceUpdateException();
        }
    }

    @Override
    public EmailTemplateEntity delete(Long entityId, Long requesterId) throws RequesterNotFoundException, ResourceNotFoundException, ResourceUpdateException {
        logger.debug(String.format("@%s::delete(%d, %d)", this.getClass().getName(), entityId, requesterId));

        AdmUsuarioEntity requester = this.userRepo.findOne(requesterId);

        if (requester == null) {
            throw new RequesterNotFoundException();
        }

        EmailTemplateEntity entity = this.emailTemplateRepository.findOne(entityId);

        if (entity == null) {
            throw new ResourceNotFoundException();
        }

        entity.setEstado("I");
        entity.setUsuarioModificacion(requester.getUsuario());
        entity.setFechaModificacion(new Timestamp(new Date().getTime()));

        try {
            return this.emailTemplateRepository.save(entity);
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourceUpdateException();
        }
    }
}
