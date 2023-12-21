package gt.com.tigo.fixed_assets.dao.portal;

import gt.com.tigo.fixed_assets.model.portal.AdmParametrosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ParameterRepository extends JpaRepository<AdmParametrosEntity, Long>, JpaSpecificationExecutor<AdmParametrosEntity> {

    AdmParametrosEntity findByNombre(String nombre);

}
