package gt.com.tigo.fixed_assets.dao.portal;

import gt.com.tigo.fixed_assets.model.portal.NotificacionesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface NotificacionesRepository extends JpaRepository<NotificacionesEntity, Long>, JpaSpecificationExecutor<NotificacionesEntity> {
    @Query(value = "SELECT PKG_FIXED_ASSETS.SP_SEND_NOTIFICATION(?1)ID FROM DUAL" ,
            nativeQuery = true)
    Long sendNotification(Long usuario);
}
