package gt.com.tigo.fixed_assets.dao.portal;

import gt.com.tigo.fixed_assets.model.portal.CpEmpleadosEbsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadosEbsRepository extends JpaRepository<CpEmpleadosEbsEntity, Long>, JpaSpecificationExecutor<CpEmpleadosEbsEntity> {

    CpEmpleadosEbsEntity findByEmailAddress(String emailAddress);

    CpEmpleadosEbsEntity findByEmployeeNumber(String employeeNumber);

}
