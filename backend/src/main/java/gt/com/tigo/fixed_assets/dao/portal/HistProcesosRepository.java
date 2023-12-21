package gt.com.tigo.fixed_assets.dao.portal;


import gt.com.tigo.fixed_assets.model.portal.HistProcesosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface HistProcesosRepository extends JpaRepository<HistProcesosEntity, Long>, JpaSpecificationExecutor<HistProcesosEntity> {

    @Query(value = "SELECT PKG_FIXED_ASSETS.SP_EJECUTA_PROCESO(?1,?2,?3)ID FROM DUAL" ,
            nativeQuery = true)
    Long executeProcess(String proceso, String usuario, String descripcion);
}
