package gt.com.tigo.fixed_assets.dao.portal;

import gt.com.tigo.fixed_assets.model.portal.VUnidadNegocioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface VUnidadNegocioRepository extends JpaRepository<VUnidadNegocioEntity, String>, JpaSpecificationExecutor<VUnidadNegocioEntity> {
}
