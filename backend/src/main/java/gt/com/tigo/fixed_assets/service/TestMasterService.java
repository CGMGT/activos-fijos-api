package gt.com.tigo.fixed_assets.service;

import gt.com.tigo.fixed_assets.dao.portal.TestDetailRepository;
import gt.com.tigo.fixed_assets.dao.portal.TestMasterRepository;
import gt.com.tigo.fixed_assets.dao.portal.UserRepository;
import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.dto.FilterInfoDto;
import gt.com.tigo.fixed_assets.dto.PaginatedDataDto;
import gt.com.tigo.fixed_assets.model.portal.AdmUsuarioEntity;
import gt.com.tigo.fixed_assets.model.portal.TestMasterEntity;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static gt.com.tigo.fixed_assets.util.Utils.*;

@Service
public class TestMasterService implements ICatalog<TestMasterEntity> {

    private static final Logger logger = LoggerFactory.getLogger(TestMasterService.class);
    public static final String VALUE = "value";

    @Autowired
    private TestMasterRepository masterRepo;

    @Autowired
    private TestDetailRepository detailRepo;

    @Autowired
    private UserRepository userRepo;

    /**
     * Returns a list of all resources in this catalog.
     * @return a list of objects containing all resources' data.
     * @throws ResourcesNotFoundException when an error occurs.
     */
    @Override
    public List<TestMasterEntity> findAll() throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::findAll()", this.getClass().getName()));

        try {
            return this.masterRepo.findAll();
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourcesNotFoundException();
        }
    }

    @Override
    public PaginatedDataDto<TestMasterEntity> findAllByPage(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
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
            Specification valueSpec = getLikeSpecification(VALUE, filtersInfo.get(VALUE) == null ? null : filtersInfo.get(VALUE).getValue());

            // put filter specifications together
            Specification filterSpec = Specifications
                    .where(getDefaultSpecification())
                    .and(valueSpec)
                    /*.and(typeSpec)*/;

            Page<TestMasterEntity> pageData;

            // if sort was not requested then use the default order
            if (dataTableRequestDto.getSorted() == null || dataTableRequestDto.getSorted().isEmpty()) {
                pageData = this.masterRepo.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Sort.Direction.ASC, VALUE));
            } else { // else use the requested order
                pageData = this.masterRepo.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Boolean.TRUE.equals(dataTableRequestDto.getSorted().get(0).getDesc()) ? Sort.Direction.DESC : Sort.Direction.ASC, dataTableRequestDto.getSorted().get(0).getId()));
            }

            // return a transfer object containing the rows, total rows and total pages
            return new PaginatedDataDto<>(pageData.getContent(), pageData.getTotalElements(), pageData.getTotalPages());
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourcesNotFoundException();
        }
    }

    @Override
    public TestMasterEntity findById(Long id) throws ResourceNotFoundException {
        return null;
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
    public TestMasterEntity create(TestMasterEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceCreateException {
        logger.debug(String.format("@%s::create(%s, %d)", this.getClass().getName(), entity, requesterId));

        AdmUsuarioEntity requester = this.userRepo.findOne(requesterId);

        if (requester == null) {
            throw new RequesterNotFoundException();
        }

        try {
            entity = this.masterRepo.save(entity);
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourceCreateException();
        }

        return this.masterRepo.findOne(entity.getIdMaster());
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
    public TestMasterEntity update(TestMasterEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceUpdateException {
        logger.debug(String.format("@%s::update(%s, %d)", this.getClass().getName(), entity, requesterId));

        AdmUsuarioEntity requester = this.userRepo.findOne(requesterId);

        if (requester == null) {
            throw new RequesterNotFoundException();
        }

        try {
            entity = this.masterRepo.saveAndFlush(entity);
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourceUpdateException();
        }

        return this.masterRepo.findOne(entity.getIdMaster());
    }

    @Override
    public TestMasterEntity delete(Long entityId, Long requesterId) throws RequesterNotFoundException, ResourceNotFoundException, ResourceUpdateException {
        return null;
    }
}
