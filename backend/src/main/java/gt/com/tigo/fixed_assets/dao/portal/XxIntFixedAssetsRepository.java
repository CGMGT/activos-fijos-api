package gt.com.tigo.fixed_assets.dao.portal;

import gt.com.tigo.fixed_assets.model.portal.XxIntFixedAssetsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface XxIntFixedAssetsRepository extends JpaRepository<XxIntFixedAssetsEntity, Long>, JpaSpecificationExecutor<XxIntFixedAssetsEntity> {
}
