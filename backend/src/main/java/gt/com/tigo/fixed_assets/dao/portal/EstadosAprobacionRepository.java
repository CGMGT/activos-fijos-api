package gt.com.tigo.fixed_assets.dao.portal;

import gt.com.tigo.fixed_assets.model.portal.EstadosAprobacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadosAprobacionRepository extends JpaRepository<EstadosAprobacionEntity, Long>, JpaSpecificationExecutor<EstadosAprobacionEntity> {

    EstadosAprobacionEntity findByNombre(String nombre);

}
