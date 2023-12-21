package gt.com.tigo.fixed_assets.dao.portal;

import gt.com.tigo.fixed_assets.model.portal.TipoGestionesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoGestionesRepository extends JpaRepository<TipoGestionesEntity, Long>, JpaSpecificationExecutor<TipoGestionesEntity> {

    TipoGestionesEntity findByNombre(String nombre);

}
