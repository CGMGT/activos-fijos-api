package gt.com.tigo.fixed_assets.service;

import gt.com.tigo.fixed_assets.dao.portal.FlujoAprobacionRepository;
import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.dto.PaginatedDataDto;
import gt.com.tigo.fixed_assets.model.portal.EstadosAprobacionEntity;
import gt.com.tigo.fixed_assets.model.portal.FlujoAprobacionEntity;
import gt.com.tigo.fixed_assets.model.portal.TipoGestionesEntity;
import gt.com.tigo.fixed_assets.util.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlujoAprobacionService implements ICatalog<FlujoAprobacionEntity> {

    private static final Logger logger = LoggerFactory.getLogger(FlujoAprobacionService.class);

    @Autowired
    private FlujoAprobacionRepository workflowRepository;


    /**
     * Returns a list of all resources in this catalog.
     * @return a list of objects containing all resources' data.
     * @throws ResourcesNotFoundException when an error occurs.
     */
    @Override
    public List<FlujoAprobacionEntity> findAll() throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::findAll()", this.getClass().getName()));

        try {
            return this.workflowRepository.findAll(new Sort(Sort.Direction.ASC, "id"));
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourcesNotFoundException();
        }
    }

    @Override
    public PaginatedDataDto<FlujoAprobacionEntity> findAllByPage(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
        return null;
    }


    /**
     * Returns a resource by id.
     * @param id The id of the requested resource
     * @return an object containing the found resource.
     * @throws ResourceNotFoundException when no record is found.
     */
    @Override
    public FlujoAprobacionEntity findById(Long id) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findById(%d)", this.getClass().getName(), id));

        FlujoAprobacionEntity entity = this.workflowRepository.findOne(id);

        if (entity == null) {
            throw new ResourceNotFoundException();
        }

        return entity;
    }

    /**
     * Returns a resource by name.
     * @param tipoGestion The type of the request
     * @param estadoInicial The id of the current state
     *  @param event The name of the requested resource
     * @return an object containing the found resource.
     * @throws ResourceNotFoundException when no record is found.
     */
    public FlujoAprobacionEntity findByWorkFlowAndEvent(TipoGestionesEntity tipoGestion,EstadosAprobacionEntity estadoInicial, String event) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findByStateAndEvent(%s, %s, %s)", this.getClass().getName(), tipoGestion, estadoInicial,event));

        FlujoAprobacionEntity entity = this.workflowRepository.findByTipoGestionAndEstadoInicialAndEvento(tipoGestion,estadoInicial,event);

        if (entity == null) {
            throw new ResourceNotFoundException();
        }

        return entity;
    }

    @Override
    public FlujoAprobacionEntity create(FlujoAprobacionEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceCreateException {
        return null;
    }

    @Override
    public FlujoAprobacionEntity update(FlujoAprobacionEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceUpdateException {
        return null;
    }

    @Override
    public FlujoAprobacionEntity delete(Long entityId, Long requesterId) throws RequesterNotFoundException, ResourceNotFoundException, ResourceUpdateException {
        return null;
    }
}
