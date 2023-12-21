package gt.com.tigo.fixed_assets.dao.portal;

import gt.com.tigo.fixed_assets.model.portal.XxIntCustomersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface XxIntCustomersRepository extends JpaRepository<XxIntCustomersEntity, Long>, JpaSpecificationExecutor<XxIntCustomersEntity> {
}
