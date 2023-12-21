package gt.com.tigo.fixed_assets.dao.portal;

import gt.com.tigo.fixed_assets.model.portal.VAreaFuncionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VAreaFuncionalRepository extends JpaRepository<VAreaFuncionalEntity, String>, JpaSpecificationExecutor<VAreaFuncionalEntity> {
}
