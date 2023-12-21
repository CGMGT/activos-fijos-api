package gt.com.tigo.fixed_assets.dao.portal;

import gt.com.tigo.fixed_assets.model.portal.TrackingFlujoEstadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TrackingFlujoEstadoRepository extends JpaRepository<TrackingFlujoEstadoEntity, Long>, JpaSpecificationExecutor<TrackingFlujoEstadoEntity> {
}
