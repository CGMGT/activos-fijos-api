package gt.com.tigo.fixed_assets.service;

import gt.com.tigo.fixed_assets.dao.portal.EstadosAprobacionRepository;
import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.dto.PaginatedDataDto;
import gt.com.tigo.fixed_assets.model.portal.EstadosAprobacionEntity;
import gt.com.tigo.fixed_assets.util.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadosAprobacionService implements ICatalog<EstadosAprobacionEntity> {

    private static final Logger logger = LoggerFactory.getLogger(EstadosAprobacionService.class);

    @Autowired
    private EstadosAprobacionRepository estadosAprobacionRepo;

    /**
     * Returns a list of all resources in this catalog.
     * @return a list of objects containing all resources' data.
     * @throws ResourcesNotFoundException when an error occurs.
     */
    @Override
    public List<EstadosAprobacionEntity> findAll() throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::findAll()", this.getClass().getName()));

        try {
            return this.estadosAprobacionRepo.findAll(new Sort(Sort.Direction.ASC, "nombre"));
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourcesNotFoundException();
        }
    }

    @Override
    public PaginatedDataDto<EstadosAprobacionEntity> findAllByPage(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
        return null;
    }

    /**
     * Returns a resource by id.
     * @param id The id of the requested resource
     * @return an object containing the found resource.
     * @throws ResourceNotFoundException when no record is found.
     */
    @Override
    public EstadosAprobacionEntity findById(Long id) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findById(%d)", this.getClass().getName(), id));

        EstadosAprobacionEntity entity = this.estadosAprobacionRepo.findOne(id);

        if (entity == null) {
            throw new ResourceNotFoundException();
        }

        return entity;
    }

    /**
     * Returns a resource by name.
     * @param name The name of the requested resource
     * @return an object containing the found resource.
     * @throws ResourceNotFoundException when no record is found.
     */
    public EstadosAprobacionEntity findByName(String name) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findByName(%s)", this.getClass().getName(), name));

        EstadosAprobacionEntity entity = this.estadosAprobacionRepo.findByNombre(name);

        if (entity == null) {
            throw new ResourceNotFoundException();
        }

        return entity;
    }

    @Override
    public EstadosAprobacionEntity create(EstadosAprobacionEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceCreateException {
        return null;
    }

    @Override
    public EstadosAprobacionEntity update(EstadosAprobacionEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceUpdateException {
        return null;
    }

    @Override
    public EstadosAprobacionEntity delete(Long entityId, Long requesterId) throws RequesterNotFoundException, ResourceNotFoundException, ResourceUpdateException {
        return null;
    }
}
