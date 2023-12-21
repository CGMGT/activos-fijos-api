package gt.com.tigo.fixed_assets.dao.portal;

import gt.com.tigo.fixed_assets.model.portal.CpActivosFijosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActivosFijosRepository extends JpaRepository<CpActivosFijosEntity, Long>, JpaSpecificationExecutor<CpActivosFijosEntity> {

    CpActivosFijosEntity findByAssetNumber(Long assetNumber);

    CpActivosFijosEntity findBySerialNumber(String serialNumber);

    CpActivosFijosEntity findByModelNumber(String modelNumber);

    CpActivosFijosEntity findByLegacycode(String legacyCode);

    CpActivosFijosEntity findByTagNumber(String targNumber);

    @Query(
            value = "               SELECT CAST(SUBSTR (t1.valor, 1,4000) AS VARCHAR2(4000)) AS VALOR\n" +
                    "                FROM ADM_PARAMETROS t1\n" +
                    "                WHERE GRUPO = 'proceso.ebs.activos'\n" +
                    "                   AND NOMBRE = 'consulta'\n" +
                    "                   AND ROWNUM = 1\n" +
                    "                   UNION\n" +
                    "               SELECT CAST(SUBSTR (t1.valor, 4001,4000) AS VARCHAR2(4000)) AS VALOR\n" +
                    "                FROM ADM_PARAMETROS t1\n" +
                    "                WHERE GRUPO = 'proceso.ebs.activos'\n" +
                    "                   AND NOMBRE = 'consulta'\n" +
                    "                   AND ROWNUM = 1\n" +
                    "                   UNION\n" +
                    "              SELECT CAST(SUBSTR (t1.valor, 8001,4000) AS VARCHAR2(4000)) AS VALOR\n" +
                    "                FROM ADM_PARAMETROS t1\n" +
                    "                WHERE GRUPO = 'proceso.ebs.activos'\n" +
                    "                   AND NOMBRE = 'consulta'\n" +
                    "                   AND ROWNUM = 1 " ,
            nativeQuery = true
    )
    List<String> getConsultaFA();

    @Query(
            value = "               SELECT CAST(SUBSTR (t1.valor, 1,4000) AS VARCHAR2(4000)) AS VALOR\n" +
                    "                FROM ADM_PARAMETROS t1\n" +
                    "                WHERE GRUPO = 'proceso.ebs.activos'\n" +
                    "                   AND NOMBRE = 'cantidadRegistros'\n" +
                    "                   AND ROWNUM = 1\n" +
                    "                UNION ALL\n" +
                    "               SELECT NVL(CAST(SUBSTR (t1.valor, 4001,4000) AS VARCHAR2(4000)), '' ) AS VALOR\n" +
                    "                FROM ADM_PARAMETROS t1\n" +
                    "                WHERE GRUPO = 'proceso.ebs.activos'\n" +
                    "                   AND NOMBRE = 'cantidadRegistros'\n" +
                    "                   AND ROWNUM = 1\n" +
                    "               UNION ALL \n" +
                    "              SELECT NVL(CAST(SUBSTR (t1.valor, 8001,4000) AS VARCHAR2(4000)), '') AS VALOR\n" +
                    "                FROM ADM_PARAMETROS t1\n" +
                    "                WHERE GRUPO = 'proceso.ebs.activos'\n" +
                    "                   AND NOMBRE = 'cantidadRegistros'\n" +
                    "                   AND ROWNUM = 1" ,
            nativeQuery = true
    )
    List<String> getConsultaTotalFA();

    @Query(
            value = "               SELECT CAST(SUBSTR (t1.valor, 1,4000) AS VARCHAR2(4000)) AS VALOR\n" +
                    "                FROM ADM_PARAMETROS t1\n" +
                    "                WHERE NOMBRE = 'REMOVE_FA_MESSAGE'\n" +
                    "                   AND ROWNUM = 1\n" ,
            nativeQuery = true
    )
    String getMensajeNotaBaja();

}
