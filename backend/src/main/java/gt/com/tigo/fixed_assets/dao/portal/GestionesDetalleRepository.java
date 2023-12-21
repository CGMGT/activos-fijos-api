package gt.com.tigo.fixed_assets.dao.portal;

import gt.com.tigo.fixed_assets.model.portal.GestionesDetalleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GestionesDetalleRepository extends JpaRepository<GestionesDetalleEntity, Long>, JpaSpecificationExecutor<GestionesDetalleEntity> {
}
