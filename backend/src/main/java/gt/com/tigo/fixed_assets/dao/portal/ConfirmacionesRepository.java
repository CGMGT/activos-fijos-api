package gt.com.tigo.fixed_assets.dao.portal;

import gt.com.tigo.fixed_assets.model.portal.ConfirmacionesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ConfirmacionesRepository extends JpaRepository<ConfirmacionesEntity, Long>, JpaSpecificationExecutor<ConfirmacionesEntity> {
}
