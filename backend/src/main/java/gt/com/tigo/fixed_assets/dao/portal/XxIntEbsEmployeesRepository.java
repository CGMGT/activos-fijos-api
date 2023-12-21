package gt.com.tigo.fixed_assets.dao.portal;

import gt.com.tigo.fixed_assets.model.portal.XxIntEbsEmployeesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface XxIntEbsEmployeesRepository extends JpaRepository<XxIntEbsEmployeesEntity, Long>, JpaSpecificationExecutor<XxIntEbsEmployeesEntity> {
}
