package gt.com.tigo.fixed_assets.service;

import gt.com.tigo.fixed_assets.dao.ebs.EmployeesEbsRepository;
import gt.com.tigo.fixed_assets.dao.portal.XxIntEbsEmployeesRepository;
import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.dto.PaginatedDataDto;
import gt.com.tigo.fixed_assets.model.ebs.EmpleadosEbsEntity;
import gt.com.tigo.fixed_assets.model.portal.XxIntEbsEmployeesEntity;
import gt.com.tigo.fixed_assets.util.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadosEbsService implements ICatalog<EmpleadosEbsEntity> {
    private static final Logger logger = LoggerFactory.getLogger(EmpleadosEbsService.class);

    @Autowired
    private EmployeesEbsRepository employeeRepo;

    @Autowired
    private XxIntEbsEmployeesRepository intEmployeesRepository ;


    @Override
    public List<EmpleadosEbsEntity> findAll() throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::findAll()", this.getClass().getName()));

        try {
            return this.employeeRepo.findAll();
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourcesNotFoundException();
        }
    }

    @Override
    public PaginatedDataDto<EmpleadosEbsEntity> findAllByPage(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
        return null;
    }

    public List<EmpleadosEbsEntity> findAllByPage(Long offset, Long fetchRows) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findAllByPage(%d,%d)", this.getClass().getName(), offset, fetchRows));

        List<EmpleadosEbsEntity> entidad = this.employeeRepo.findAllByPage(offset, fetchRows);

        if (entidad == null) {
            throw new ResourceNotFoundException();
        }

        return entidad;
    }

    @Override
    public EmpleadosEbsEntity findById(Long id) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findById(%d)", this.getClass().getName(), id));

        EmpleadosEbsEntity entidad = this.employeeRepo.findById(id);

        if (entidad == null) {
            throw new ResourceNotFoundException();
        }

        return entidad;
    }

    public String sync() throws ResourceNotFoundException, ResourceCreateException {
        logger.debug(String.format("@%s::sync()", this.getClass().getName()));

        List<EmpleadosEbsEntity> entidad = this.employeeRepo.findAll();

        if (entidad == null) {
            throw new ResourceNotFoundException();
        }
        try{

            for (EmpleadosEbsEntity entity : entidad){
                XxIntEbsEmployeesEntity employee = new XxIntEbsEmployeesEntity();
                employee.setAttribute1(entity.getAttribute1());
                employee.setCurrentEmployeeFlag(entity.getCurrentEmployeeFlag());
                employee.setCurrentEmpOrAplFlag(entity.getCurrentEmpOrAplFlag());
                employee.setEffectiveEndDate(entity.getEffectiveEndDate());
                employee.setEffectiveStartDate(entity.getEffectiveStartDate());
                employee.setEmailAddress(entity.getEmailAddress());
                employee.setEmployeeNumber(entity.getEmployeeNumber());
                employee.setFirstName(entity.getFirstName());
                employee.setFullName(entity.getFullName());
                employee.setGlobalName(entity.getGlobalName());
                employee.setLastName(entity.getLastName());
                employee.setLocalName(entity.getLocalName());
                employee.setOriginalDateOfHire(entity.getOriginalDateOfHire());
                employee.setPartyId(entity.getPartyId());
                employee.setPersonId(entity.getPersonId());
                employee.setPersonTypeId(entity.getPersonTypeId());
                employee.setSex(entity.getSex());
                employee.setStartDate(entity.getStartDate());
                this.intEmployeesRepository.save(employee);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourceCreateException();
        }

        return null;
    }

    @Override
    public EmpleadosEbsEntity create(EmpleadosEbsEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceCreateException {
        return null;
    }

    @Override
    public EmpleadosEbsEntity update(EmpleadosEbsEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceUpdateException {
        return null;
    }

    @Override
    public EmpleadosEbsEntity delete(Long entityId, Long requesterId) throws RequesterNotFoundException, ResourceNotFoundException, ResourceUpdateException {
        return null;
    }
}
