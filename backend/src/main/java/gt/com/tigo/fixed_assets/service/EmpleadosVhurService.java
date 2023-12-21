package gt.com.tigo.fixed_assets.service;

import gt.com.tigo.fixed_assets.dao.portal.XxIntCustomersRepository;
import gt.com.tigo.fixed_assets.dao.vhur.EmpleadosVhurRepository;
import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.dto.PaginatedDataDto;
import gt.com.tigo.fixed_assets.model.portal.XxIntCustomersEntity;
import gt.com.tigo.fixed_assets.model.vhur.EmpleadosVhurEntity;
import gt.com.tigo.fixed_assets.util.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadosVhurService implements ICatalog<EmpleadosVhurEntity> {
    private static final Logger logger = LoggerFactory.getLogger(EmpleadosVhurService.class);

    @Autowired
    private EmpleadosVhurRepository vhurEmployeesRepository;

    @Autowired
    private XxIntCustomersRepository intCustomersRepository ;

    /**
     * Returns a list of all resources in this catalog.
     * @return a list of objects containing all resources' data.
     * @throws ResourcesNotFoundException when an error occurs.
     */
    @Override
    public List<EmpleadosVhurEntity> findAll() throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::findAll()", this.getClass().getName()));

        try {
            return this.vhurEmployeesRepository.findAll();
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourcesNotFoundException();
        }
    }

    /**
     * Returns a list of all resources in this catalog by page and page size.
     * @param dataTableRequestDto page, sort and filter data.
     * @return a list of objects containing all resources' data.
     * @throws ResourcesNotFoundException when an error occurs.
     */
    @Override
    public PaginatedDataDto<EmpleadosVhurEntity> findAllByPage(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
        return null;
    }

    /**
     * Returns a resource by id.
     * @param id The id of the requested resource
     * @return an object containing the found resource.
     * @throws ResourceNotFoundException when no record is found.
     */
    @Override
    public EmpleadosVhurEntity findById(Long id) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findById(%d)", this.getClass().getName(), id));

        EmpleadosVhurEntity entidad = this.vhurEmployeesRepository.findById(id);

        if (entidad == null) {
            throw new ResourceNotFoundException();
        }

        return entidad;
    }

    /**
     * Returns a list of all resources in this catalog by page and page size.
     * @param offset, fetchRows.
     * @return a list of objects containing all resources' data.
     * @throws ResourcesNotFoundException when an error occurs.
     */
    public List<EmpleadosVhurEntity> findAllByPage(Long offset, Long fetchRows) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findAllByPage(%d,%d)", this.getClass().getName(), offset, fetchRows));

        List<EmpleadosVhurEntity> entidad = this.vhurEmployeesRepository.findAllByPage(offset, fetchRows);

        if (entidad == null) {
            throw new ResourceNotFoundException();
        }

        return entidad;
    }

    public String sync() throws ResourceNotFoundException, ResourceCreateException {
        logger.debug(String.format("@%s::sync()", this.getClass().getName()));

        List<EmpleadosVhurEntity> entidad = this.vhurEmployeesRepository.findAll();

        if (entidad == null) {
            throw new ResourceNotFoundException();
        }
        try{

            for (EmpleadosVhurEntity entity : entidad){
                XxIntCustomersEntity vhurEmployee = new XxIntCustomersEntity();
                vhurEmployee.setApellidosNombres(entity.getApellidosNombres());
                vhurEmployee.setNombresApellidos(entity.getNombresApellidos());
                vhurEmployee.setAreaFuncional(entity.getAreaFuncional());
                vhurEmployee.setCodigoJefe(entity.getCodigoJefe());
                vhurEmployee.setEmailColaborador(entity.getEmailColaborador());
                vhurEmployee.setEmpCodigo(entity.getEmpCodigo());
                vhurEmployee.setEmailJefeInmediato(entity.getEmailJefeInmediato());
                vhurEmployee.setNoStaff(entity.getNoStaff());
                vhurEmployee.setPlaza(entity.getPlaza());
                vhurEmployee.setUnidadAdministrativa(entity.getUnidadAdministrativa());
                vhurEmployee.setUnidadDeNegocio(entity.getUnidadNegocio());
                vhurEmployee.setNombreJefeInmediato(entity.getNombreJefeInmediato());
                this.intCustomersRepository.save(vhurEmployee);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourceCreateException();
        }

        return null;
    }

    @Override
    public EmpleadosVhurEntity create(EmpleadosVhurEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceCreateException {
        return null;
    }

    @Override
    public EmpleadosVhurEntity update(EmpleadosVhurEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceUpdateException {
        return null;
    }

    @Override
    public EmpleadosVhurEntity delete(Long entityId, Long requesterId) throws RequesterNotFoundException, ResourceNotFoundException, ResourceUpdateException {
        return null;
    }
}
