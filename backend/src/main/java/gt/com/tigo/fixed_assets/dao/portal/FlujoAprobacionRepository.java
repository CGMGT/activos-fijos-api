package gt.com.tigo.fixed_assets.dao.portal;

import gt.com.tigo.fixed_assets.model.portal.EstadosAprobacionEntity;
import gt.com.tigo.fixed_assets.model.portal.FlujoAprobacionEntity;
import gt.com.tigo.fixed_assets.model.portal.TipoGestionesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FlujoAprobacionRepository extends JpaRepository<FlujoAprobacionEntity, Long>, JpaSpecificationExecutor<FlujoAprobacionEntity> {
    FlujoAprobacionEntity findByTipoGestionAndEstadoInicialAndEvento(TipoGestionesEntity tipoGestion, EstadosAprobacionEntity estadoInicial, String evento);
}
