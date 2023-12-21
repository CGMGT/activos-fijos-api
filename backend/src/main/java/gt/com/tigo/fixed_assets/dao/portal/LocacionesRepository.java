package gt.com.tigo.fixed_assets.dao.portal;


import gt.com.tigo.fixed_assets.model.portal.CpLocationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocacionesRepository extends JpaRepository<CpLocationsEntity, Long>, JpaSpecificationExecutor<CpLocationsEntity> {

    @Query("SELECT l " +
            "FROM CpLocationsEntity l " +
            "LEFT JOIN l.encargado1 usr1 " +
            "LEFT JOIN l.encargado2 usr2 " +
            "LEFT JOIN l.encargado3 usr3 " +
            "LEFT JOIN l.encargado4 usr4 " +
            "LEFT JOIN l.encargado5 usr5 " +
            "WHERE usr1.id = ?1 " +
            "OR usr2.id = ?1 " +
            "OR usr3.id = ?1 " +
            "OR usr4.id = ?1 " +
            "OR usr5.id = ?1")
    List<CpLocationsEntity> getAllByUserId(Long userId);

    @Query(
            value = "               SELECT CAST(SUBSTR (t1.valor, 1,4000) AS VARCHAR2(4000)) AS VALOR\n" +
                    "                FROM ADM_PARAMETROS t1\n" +
                    "                WHERE NOMBRE = 'DEFAULT_WAREHOUSE'\n" +
                    "                   AND ROWNUM = 1\n" ,
            nativeQuery = true
    )
    String getDefaultWarehouseName();

    CpLocationsEntity findByNombre(String nombre);
}
