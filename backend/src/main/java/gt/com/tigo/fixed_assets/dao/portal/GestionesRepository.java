package gt.com.tigo.fixed_assets.dao.portal;

import gt.com.tigo.fixed_assets.model.portal.GestionesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface GestionesRepository extends JpaRepository<GestionesEntity, Long>, JpaSpecificationExecutor<GestionesEntity> {

    List<GestionesEntity> findByReceptorIdAndTipoGestionId(Long id, Long idTipoGestion);

    List<GestionesEntity> findByTipoGestionId(Long idTipoGestion);

    @Query(
            value = "SELECT GE.ID FROM GESTIONES GE WHERE GE.USUARIO_CREACION = ?1 AND GE.ESTADO NOT IN (2, 3, 21)",
            nativeQuery = true
    )
    List<BigDecimal> getOpenRequestsByUsuarioCreacion(String usuarioCreacion);

    @Query(value = "SELECT PKG_FIXED_ASSETS.SP_NOTIFICA_FA_MANUAL(?1,?2)ID FROM DUAL" ,
            nativeQuery = true)
    Long enviaCorreo(String usuario, String gestion);
}
