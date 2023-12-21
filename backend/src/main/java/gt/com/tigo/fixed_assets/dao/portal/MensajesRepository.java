package gt.com.tigo.fixed_assets.dao.portal;

import gt.com.tigo.fixed_assets.model.portal.MensajesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MensajesRepository extends JpaRepository<MensajesEntity, Long>, JpaSpecificationExecutor<MensajesEntity> {
}
