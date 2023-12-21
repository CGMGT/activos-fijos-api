package gt.com.tigo.fixed_assets.service;

import gt.com.tigo.fixed_assets.dao.portal.TipoGestionesRepository;
import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.dto.PaginatedDataDto;
import gt.com.tigo.fixed_assets.model.portal.TipoGestionesEntity;
import gt.com.tigo.fixed_assets.util.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoGestionesService implements ICatalog<TipoGestionesEntity> {

    private static final Logger logger = LoggerFactory.getLogger(TipoGestionesService.class);

    @Autowired
    private TipoGestionesRepository tipoGestionesRepo;

    /**
     * Returns a list of all resources in this catalog.
     * @return a list of objects containing all resources' data.
     * @throws ResourcesNotFoundException when an error occurs.
     */
    @Override
    public List<TipoGestionesEntity> findAll() throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::findAll()", this.getClass().getName()));

        try {
            return this.tipoGestionesRepo.findAll(new Sort(Sort.Direction.ASC, "nombre"));
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourcesNotFoundException();
        }
    }

    @Override
    public PaginatedDataDto<TipoGestionesEntity> findAllByPage(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
        return null;
    }

    /**
     * Returns a resource by id.
     * @param id The id of the requested resource
     * @return an object containing the found resource.
     * @throws ResourceNotFoundException when no record is found.
     */
    @Override
    public TipoGestionesEntity findById(Long id) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findById(%d)", this.getClass().getName(), id));

        TipoGestionesEntity entity = this.tipoGestionesRepo.findOne(id);

        if (entity == null) {
            throw new ResourceNotFoundException();
        }

        return entity;
    }

    /**
     * Resturns a resource by name.
     * @param name The name of the requested resource.
     * @return an object containing the found resource.
     * @throws ResourceNotFoundException when no record is found.
     */
    public TipoGestionesEntity findByName(String name) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findByName(%s)", this.getClass().getName(), name));

        TipoGestionesEntity entity = this.tipoGestionesRepo.findByNombre(name);

        if (entity == null) {
            throw new ResourceNotFoundException();
        }

        return entity;
    }

    @Override
    public TipoGestionesEntity create(TipoGestionesEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceCreateException {
        return null;
    }

    @Override
    public TipoGestionesEntity update(TipoGestionesEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceUpdateException {
        return null;
    }

    @Override
    public TipoGestionesEntity delete(Long entityId, Long requesterId) throws RequesterNotFoundException, ResourceNotFoundException, ResourceUpdateException {
        return null;
    }
}
