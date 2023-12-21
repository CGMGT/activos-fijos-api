package gt.com.tigo.fixed_assets.dao.portal;

import gt.com.tigo.fixed_assets.model.portal.XxIntLocationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface XxIntLocationsRepository extends JpaRepository<XxIntLocationsEntity, Long>, JpaSpecificationExecutor<XxIntLocationsEntity> {
}
