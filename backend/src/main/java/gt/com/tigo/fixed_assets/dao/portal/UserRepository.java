package gt.com.tigo.fixed_assets.dao.portal;

import gt.com.tigo.fixed_assets.model.portal.AdmUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AdmUsuarioEntity, Long>, JpaSpecificationExecutor<AdmUsuarioEntity> {

    AdmUsuarioEntity findByUuid(String uuid);

    AdmUsuarioEntity findByCorreoElectronico(String correoElectronico);

}
