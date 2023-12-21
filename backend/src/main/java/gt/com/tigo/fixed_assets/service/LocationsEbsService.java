package gt.com.tigo.fixed_assets.service;

import gt.com.tigo.fixed_assets.dao.ebs.LocationsEbsRepository;
import gt.com.tigo.fixed_assets.dao.portal.XxIntLocationsRepository;
import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.dto.PaginatedDataDto;
import gt.com.tigo.fixed_assets.model.ebs.LocationsEbsEntity;
import gt.com.tigo.fixed_assets.model.portal.XxIntLocationsEntity;
import gt.com.tigo.fixed_assets.util.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationsEbsService implements ICatalog<LocationsEbsEntity>{
    private static final Logger logger = LoggerFactory.getLogger(EmpleadosEbsService.class);

    @Autowired
    private LocationsEbsRepository locRepo;

    @Autowired
    private XxIntLocationsRepository intLocationsRepository ;

    @Override
    public List<LocationsEbsEntity> findAll() throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::findAll()", this.getClass().getName()));

        try {
            return this.locRepo.findAll();
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourcesNotFoundException();
        }
    }

    @Override
    public PaginatedDataDto<LocationsEbsEntity> findAllByPage(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
        return null;
    }

    public List<LocationsEbsEntity> findAllByPage(Long offset, Long fetchRows) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findAllByPage(%d,%d)", this.getClass().getName(), offset, fetchRows));

        List<LocationsEbsEntity> entidad = this.locRepo.findAllByPage(offset, fetchRows);

        if (entidad == null) {
            throw new ResourceNotFoundException();
        }

        return entidad;
    }

    @Override
    public LocationsEbsEntity findById(Long id) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findById(%d)", this.getClass().getName(), id));

        LocationsEbsEntity entidad = this.locRepo.findById(id);

        if (entidad == null) {
            throw new ResourceNotFoundException();
        }

        return entidad;
    }

    public String sync() throws ResourceNotFoundException, ResourceCreateException {
        logger.debug(String.format("@%s::sync()", this.getClass().getName()));

        List<LocationsEbsEntity> entidad = this.locRepo.findAll();

        if (entidad == null) {
            throw new ResourceNotFoundException();
        }
        try{

            for (LocationsEbsEntity entity : entidad){
                XxIntLocationsEntity location = new XxIntLocationsEntity();
                location.setAddress1(entity.getAddress1());
                location.setAddress2(entity.getAddress2());
                location.setAddress3(entity.getAddress3());
                location.setAddress4(entity.getAddress4());
                location.setLocationId(entity.getLocationId());
                location.setNombre(entity.getNombre());
                location.setOrigSystemReference(entity.getOrigSystemReference());
                location.setCountry(entity.getCountry());
                location.setCity(entity.getCity());
                location.setPostalCode(entity.getPostalCode());
                location.setState(entity.getState());
                location.setProvince(entity.getProvince());
                location.setCounty(entity.getCounty());
                location.setAddressKey(entity.getAddressKey());
                location.setFaLocationCode(entity.getFaLocationCode());
                this.intLocationsRepository.save(location);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourceCreateException();
        }

        return null;
    }

    @Override
    public LocationsEbsEntity create(LocationsEbsEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceCreateException {
        return null;
    }

    @Override
    public LocationsEbsEntity update(LocationsEbsEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceUpdateException {
        return null;
    }

    @Override
    public LocationsEbsEntity delete(Long entityId, Long requesterId) throws RequesterNotFoundException, ResourceNotFoundException, ResourceUpdateException {
        return null;
    }
}
