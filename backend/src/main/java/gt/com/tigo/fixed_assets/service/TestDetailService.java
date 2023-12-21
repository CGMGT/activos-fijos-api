package gt.com.tigo.fixed_assets.service;

import gt.com.tigo.fixed_assets.dao.portal.TestDetailRepository;
import gt.com.tigo.fixed_assets.dao.portal.UserRepository;
import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.dto.PaginatedDataDto;
import gt.com.tigo.fixed_assets.model.portal.AdmUsuarioEntity;
import gt.com.tigo.fixed_assets.model.portal.TestDetailEntity;
import gt.com.tigo.fixed_assets.util.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestDetailService implements ICatalog<TestDetailEntity> {

    private static final Logger logger = LoggerFactory.getLogger(TestDetailService.class);

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
    public List<TestDetailEntity> findAll() throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::findAll()", this.getClass().getName()));

        try {
            return this.detailRepo.findAll();
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourcesNotFoundException();
        }
    }

    @Override
    public PaginatedDataDto<TestDetailEntity> findAllByPage(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
        return null;
    }

    @Override
    public TestDetailEntity findById(Long id) throws ResourceNotFoundException {
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
    public TestDetailEntity create(TestDetailEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceCreateException {
        logger.debug(String.format("@%s::create(%s, %d)", this.getClass().getName(), entity, requesterId));

        AdmUsuarioEntity requester = this.userRepo.findOne(requesterId);

        if (requester == null) {
            throw new RequesterNotFoundException();
        }

        try {
            return this.detailRepo.save(entity);
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourceCreateException();
        }
    }

    @Override
    public TestDetailEntity update(TestDetailEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceUpdateException {
        return null;
    }

    @Override
    public TestDetailEntity delete(Long entityId, Long requesterId) throws RequesterNotFoundException, ResourceNotFoundException, ResourceUpdateException {
        return null;
    }
}
