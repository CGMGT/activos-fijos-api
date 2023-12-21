package gt.com.tigo.fixed_assets.dao.portal;

import gt.com.tigo.fixed_assets.model.portal.VUnidadAdministrativaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VUnidadAdministrativaRepository extends JpaRepository<VUnidadAdministrativaEntity, String>, JpaSpecificationExecutor<VUnidadAdministrativaEntity> {
}
