package gt.com.tigo.fixed_assets.dao.portal;

import gt.com.tigo.fixed_assets.model.portal.DummyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DashboardRepository extends JpaRepository<DummyEntity, Long> {

    @Query(
            value = "SELECT TG.NOMBRE AS REQUEST_TYPE, NVL(T1.QTY, 0) AS QTY " +
                    "FROM TIPO_GESTIONES TG " +
                    "LEFT OUTER JOIN ( " +
                    "    SELECT GE.ID_TIPO_GESTION, COUNT(1) AS QTY " +
                    "    FROM GESTIONES GE " +
                    "    INNER JOIN CP_EMPLEADOS EM ON GE.ID_RECEPTOR = EM.ID " +
                    "    INNER JOIN ADM_USUARIO US ON US.CORREO_ELECTRONICO = EM.EMAIL_COLABORADOR " +
                    "    WHERE 1 = 1 " +
                    "    AND GE.ESTADO = 1 " +
                    "    AND GE.ID_TIPO_GESTION = 3 " +
                    "    AND US.ID = ?1 " +
                    "    AND GE.FECHA_CREACION > SYSDATE - 7 " +
                    "    GROUP BY GE.ID_TIPO_GESTION " +
                    ") T1 ON T1.ID_TIPO_GESTION = TG.ID " +
                    "ORDER BY TG.NOMBRE ASC",
            nativeQuery = true
    )
    List<Object> getMyPendingRequests7Days(Long userId);

    @Query(
            value = "SELECT TG.NOMBRE AS REQUEST_TYPE, NVL(T1.QTY, 0) AS QTY " +
                    "FROM TIPO_GESTIONES TG " +
                    "LEFT OUTER JOIN ( " +
                    "    SELECT GE.ID_TIPO_GESTION, COUNT(1) AS QTY " +
                    "    FROM GESTIONES GE " +
                    "    INNER JOIN CP_EMPLEADOS EM ON GE.ID_RECEPTOR = EM.ID " +
                    "    INNER JOIN ADM_USUARIO US ON US.CORREO_ELECTRONICO = EM.EMAIL_COLABORADOR " +
                    "    WHERE 1 = 1 " +
                    "    AND GE.ESTADO = 1 " +
                    "    AND GE.ID_TIPO_GESTION = 3 " +
                    "    AND US.ID = ?1 " +
                    "    AND GE.FECHA_CREACION BETWEEN SYSDATE - 21 AND SYSDATE - 7 " +
                    "    GROUP BY GE.ID_TIPO_GESTION " +
                    ") T1 ON T1.ID_TIPO_GESTION = TG.ID " +
                    "ORDER BY TG.NOMBRE ASC",
            nativeQuery = true
    )
    List<Object> getMyPendingRequestsFrom7To21Days(Long userId);

    @Query(
            value = "SELECT TG.NOMBRE AS REQUEST_TYPE, NVL(T1.QTY, 0) AS QTY " +
                    "FROM TIPO_GESTIONES TG " +
                    "LEFT OUTER JOIN ( " +
                    "    SELECT GE.ID_TIPO_GESTION, COUNT(1) AS QTY " +
                    "    FROM GESTIONES GE " +
                    "    INNER JOIN CP_EMPLEADOS EM ON GE.ID_RECEPTOR = EM.ID " +
                    "    INNER JOIN ADM_USUARIO US ON US.CORREO_ELECTRONICO = EM.EMAIL_COLABORADOR " +
                    "    WHERE 1 = 1 " +
                    "    AND GE.ESTADO = 1 " +
                    "    AND GE.ID_TIPO_GESTION = 3 " +
                    "    AND US.ID = ?1 " +
                    "    AND GE.FECHA_CREACION < SYSDATE - 21 " +
                    "    GROUP BY GE.ID_TIPO_GESTION " +
                    ") T1 ON T1.ID_TIPO_GESTION = TG.ID " +
                    "ORDER BY TG.NOMBRE ASC",
            nativeQuery = true
    )
    List<Object> getMyPendingRequestsOlderThan21Days(Long userId);

    @Query(
            value = "SELECT TG.NOMBRE AS REQUEST_TYPE, NVL(T1.QTY, 0) AS QTY " +
                    "FROM TIPO_GESTIONES TG " +
                    "LEFT OUTER JOIN (" +
                    "    SELECT GE.ID_TIPO_GESTION, COUNT(1) AS QTY " +
                    "    FROM GESTIONES GE " +
                    "    INNER JOIN CP_EMPLEADOS CE ON GE.USUARIO_CREACION = CE.EMAIL_COLABORADOR " +
                    "    INNER JOIN ADM_USUARIO US ON US.CORREO_ELECTRONICO = CE.EMAIL_JEFE_INMEDIATO " +
                    "    WHERE 1 = 1 " +
                    "    AND GE.ESTADO = 22 " +
                    "    AND US.ID = ?1 " +
                    "    AND GE.FECHA_CREACION > SYSDATE - 7 " +
                    "    GROUP BY GE.ID_TIPO_GESTION " +
                    ") T1 ON T1.ID_TIPO_GESTION = TG.ID " +
                    "ORDER BY TG.NOMBRE ASC",
            nativeQuery = true
    )
    List<Object> getMyTeamPendingRequests7Days(Long managerId);

    @Query(
            value = "SELECT TG.NOMBRE AS REQUEST_TYPE, NVL(T1.QTY, 0) AS QTY " +
                    "FROM TIPO_GESTIONES TG " +
                    "LEFT OUTER JOIN (" +
                    "    SELECT GE.ID_TIPO_GESTION, COUNT(1) AS QTY " +
                    "    FROM GESTIONES GE " +
                    "    INNER JOIN CP_EMPLEADOS CE ON GE.USUARIO_CREACION = CE.EMAIL_COLABORADOR " +
                    "    INNER JOIN ADM_USUARIO US ON US.CORREO_ELECTRONICO = CE.EMAIL_JEFE_INMEDIATO " +
                    "    WHERE 1 = 1 " +
                    "    AND GE.ESTADO = 22 " +
                    "    AND US.ID = ?1 " +
                    "    AND GE.FECHA_CREACION BETWEEN SYSDATE - 21 AND SYSDATE - 7 " +
                    "    GROUP BY GE.ID_TIPO_GESTION " +
                    ") T1 ON T1.ID_TIPO_GESTION = TG.ID " +
                    "ORDER BY TG.NOMBRE ASC",
            nativeQuery = true
    )
    List<Object> getMyTeamPendingRequestsFrom7To21Days(Long managerId);

    @Query(
            value = "SELECT TG.NOMBRE AS REQUEST_TYPE, NVL(T1.QTY, 0) AS QTY " +
                    "FROM TIPO_GESTIONES TG " +
                    "LEFT OUTER JOIN ( " +
                    "    SELECT GE.ID_TIPO_GESTION, COUNT(1) AS QTY " +
                    "    FROM GESTIONES GE " +
                    "    INNER JOIN CP_EMPLEADOS CE ON GE.USUARIO_CREACION = CE.EMAIL_COLABORADOR " +
                    "    INNER JOIN ADM_USUARIO US ON US.CORREO_ELECTRONICO = CE.EMAIL_JEFE_INMEDIATO " +
                    "    WHERE 1 = 1 " +
                    "    AND GE.ESTADO = 22 " +
                    "    AND US.ID = ?1 " +
                    "    AND GE.FECHA_CREACION < SYSDATE - 21 " +
                    "    GROUP BY GE.ID_TIPO_GESTION " +
                    ") T1 ON T1.ID_TIPO_GESTION = TG.ID " +
                    "ORDER BY TG.NOMBRE ASC",
            nativeQuery = true
    )
    List<Object> getMyTeamPendingRequestsOlderThan21Days(Long managerId);

    @Query(
            value = "SELECT TG.NOMBRE AS REQUEST_TYPE, NVL(T1.QTY, 0) AS QTY " +
                    "FROM TIPO_GESTIONES TG " +
                    "LEFT OUTER JOIN (" +
                    "    SELECT GE.ID_TIPO_GESTION, COUNT(1) AS QTY " +
                    "    FROM GESTIONES GE " +
                    "    WHERE 1 = 1 " +
                    "    AND (GE.ID_TIPO_GESTION = 1 AND GE.ESTADO = 1 OR GE.ID_TIPO_GESTION = 2 AND GE.ESTADO = 1 OR GE.ID_TIPO_GESTION = 3 AND GE.ESTADO = 62) " +
                    "    AND GE.FECHA_CREACION > SYSDATE - 7 " +
                    "    GROUP BY GE.ID_TIPO_GESTION " +
                    ") T1 ON T1.ID_TIPO_GESTION = TG.ID " +
                    "ORDER BY TG.NOMBRE ASC",
            nativeQuery = true
    )
    List<Object> getAllPendingRequests7Days();

    @Query(
            value = "SELECT TG.NOMBRE AS REQUEST_TYPE, NVL(T1.QTY, 0) AS QTY " +
                    "FROM TIPO_GESTIONES TG " +
                    "LEFT OUTER JOIN (" +
                    "    SELECT GE.ID_TIPO_GESTION, COUNT(1) AS QTY " +
                    "    FROM GESTIONES GE " +
                    "    WHERE 1 = 1 " +
                    "    AND (GE.ID_TIPO_GESTION = 1 AND GE.ESTADO = 1 OR GE.ID_TIPO_GESTION = 2 AND GE.ESTADO = 1 OR GE.ID_TIPO_GESTION = 3 AND GE.ESTADO = 62) " +
                    "    AND GE.FECHA_CREACION BETWEEN SYSDATE - 21 AND SYSDATE - 7 " +
                    "    GROUP BY GE.ID_TIPO_GESTION " +
                    ") T1 ON T1.ID_TIPO_GESTION = TG.ID " +
                    "ORDER BY TG.NOMBRE ASC",
            nativeQuery = true
    )
    List<Object> getAllPendingRequestsFrom7To21Days();

    @Query(
            value = "SELECT TG.NOMBRE AS REQUEST_TYPE, NVL(T1.QTY, 0) AS QTY " +
                    "FROM TIPO_GESTIONES TG " +
                    "LEFT OUTER JOIN ( " +
                    "    SELECT GE.ID_TIPO_GESTION, COUNT(1) AS QTY " +
                    "    FROM GESTIONES GE " +
                    "    WHERE 1 = 1 " +
                    "    AND (GE.ID_TIPO_GESTION = 1 AND GE.ESTADO = 1 OR GE.ID_TIPO_GESTION = 2 AND GE.ESTADO = 1 OR GE.ID_TIPO_GESTION = 3 AND GE.ESTADO = 62) " +
                    "    AND GE.FECHA_CREACION < SYSDATE - 21 " +
                    "    GROUP BY GE.ID_TIPO_GESTION " +
                    ") T1 ON T1.ID_TIPO_GESTION = TG.ID " +
                    "ORDER BY TG.NOMBRE ASC",
            nativeQuery = true
    )
    List<Object> getAllPendingRequestsOlderThan21Days();

    @Query(
            value = "SELECT CO.QTY_CONFIRMED, UN.QTY_UNCONFIRMED " +
                    "FROM ( " +
                    "    SELECT COUNT(1) AS QTY_UNCONFIRMED " +
                    "    FROM CONFIRMACIONES CO " +
                    "    WHERE 1 = 1 " +
                    "    AND CO.ESTADO = 'S' " +
                    "    AND CO.USUARIO = ?1 " +
                    ") UN, " +
                    "( " +
                    "    SELECT COUNT(1) AS QTY_CONFIRMED " +
                    "    FROM CONFIRMACIONES CO " +
                    "    WHERE 1 = 1 " +
                    "    AND CO.ESTADO IN ('C', 'R') " +
                    "    AND CO.USUARIO = ?1 " +
                    ") CO",
            nativeQuery = true
    )
    List<Object> getMyConfirmations(Long userId);

    @Query(
            value = "SELECT US.NOMBRE_MOSTRADO AS USER_FULL_NAME, NVL(CO.QTY_CONFIRMED, 0) AS QTY_CONFIRMED, NVL(UN.QTY_UNCONFIRMED, 0) AS QTY_UNCONFIRMED " +
                    "FROM ( " +
                    "    SELECT CO.USUARIO, COUNT(1) AS QTY_UNCONFIRMED " +
                    "    FROM CONFIRMACIONES CO " +
                    "    INNER JOIN ADM_USUARIO AU on CO.USUARIO = AU.ID " +
                    "    INNER JOIN CP_EMPLEADOS CE on AU.CORREO_ELECTRONICO = CE.EMAIL_COLABORADOR " +
                    "    INNER JOIN ADM_USUARIO US ON US.CORREO_ELECTRONICO = CE.EMAIL_JEFE_INMEDIATO " +
                    "    WHERE 1 = 1 " +
                    "    AND CO.ESTADO = 'S' " +
                    "    AND US.ID = ?1 " +
                    "    GROUP BY CO.USUARIO " +
                    ") UN " +
                    "FULL OUTER JOIN " +
                    "( " +
                    "    SELECT CO.USUARIO, COUNT(1) AS QTY_CONFIRMED " +
                    "    FROM CONFIRMACIONES CO " +
                    "    INNER JOIN ADM_USUARIO AU on CO.USUARIO = AU.ID " +
                    "    INNER JOIN CP_EMPLEADOS CE on AU.CORREO_ELECTRONICO = CE.EMAIL_COLABORADOR " +
                    "    INNER JOIN ADM_USUARIO US ON US.CORREO_ELECTRONICO = CE.EMAIL_JEFE_INMEDIATO " +
                    "    WHERE 1 = 1 " +
                    "    AND CO.ESTADO IN ('C', 'R') " +
                    "    AND US.ID = ?1 " +
                    "    GROUP BY CO.USUARIO " +
                    ") CO ON CO.USUARIO = UN.USUARIO " +
                    "INNER JOIN ADM_USUARIO US ON UN.USUARIO = US.ID " +
                    "ORDER BY US.NOMBRE_MOSTRADO ASC",
            nativeQuery = true
    )
    List<Object> getMyTeamConfirmations(Long managerId);

    @Query(
            value = "SELECT T1.BUSINESS_UNIT, T2.QTY_TOTAL, T3.QTY_NOT_SENT, T4.QTY_SENT, NVL(T1.QTY_CONFIRMED, 0) AS QTY_CONFIRMED, NVL(T1.QTY_UNCONFIRMED, 0) AS QTY_UNCONFIRMED " +
                    "FROM ( " +
                    "         SELECT BU.UNIDAD_DE_NEGOCIO AS BUSINESS_UNIT, UN.QTY_UNCONFIRMED, CO.QTY_CONFIRMED " +
                    "         FROM ( " +
                    "                  SELECT CE.UNIDAD_DE_NEGOCIO, COUNT(1) AS QTY_UNCONFIRMED " +
                    "                  FROM CONFIRMACIONES CO " +
                    "                           INNER JOIN ADM_USUARIO AU on CO.USUARIO = AU.ID " +
                    "                           INNER JOIN CP_EMPLEADOS CE on AU.CORREO_ELECTRONICO = CE.EMAIL_COLABORADOR " +
                    "                  WHERE 1 = 1 " +
                    "                    AND CO.ESTADO = 'S' " +
                    "                  GROUP BY CE.UNIDAD_DE_NEGOCIO " +
                    "              ) UN " +
                    "                  RIGHT OUTER JOIN " +
                    "              ( " +
                    "                  SELECT CE.UNIDAD_DE_NEGOCIO, COUNT(1) AS QTY_CONFIRMED " +
                    "                  FROM CONFIRMACIONES CO " +
                    "                           INNER JOIN ADM_USUARIO AU on CO.USUARIO = AU.ID " +
                    "                           INNER JOIN CP_EMPLEADOS CE on AU.CORREO_ELECTRONICO = CE.EMAIL_COLABORADOR " +
                    "                  WHERE 1 = 1 " +
                    "                    AND CO.ESTADO IN ('C', 'R') " +
                    "                  GROUP BY CE.UNIDAD_DE_NEGOCIO " +
                    "              ) CO ON CO.UNIDAD_DE_NEGOCIO = UN.UNIDAD_DE_NEGOCIO " +
                    "                  RIGHT OUTER JOIN ( " +
                    "             SELECT DISTINCT UNIDAD_DE_NEGOCIO " +
                    "             FROM CP_EMPLEADOS " +
                    "         ) BU ON BU.UNIDAD_DE_NEGOCIO = CO.UNIDAD_DE_NEGOCIO " +
                    "     ) T1  " +
                    "INNER JOIN ( " +
                    "    SELECT BU.UNIDAD_DE_NEGOCIO AS BUSINESS_UNIT, COUNT(DISTINCT CO.ID) AS QTY_TOTAL " +
                    "    FROM ( " +
                    "        SELECT DISTINCT UNIDAD_DE_NEGOCIO FROM CP_EMPLEADOS " +
                    "    ) BU " +
                    "    LEFT JOIN NOTIFICACIONES NO ON NO.UNIDAD_NEGOCIO = BU.UNIDAD_DE_NEGOCIO " +
                    "    LEFT JOIN CONFIRMACIONES CO ON NO.ID = CO.ID_NOTIFICACION " +
                    "    INNER JOIN CP_EMPLEADOS EM ON EM.UNIDAD_DE_NEGOCIO = BU.UNIDAD_DE_NEGOCIO " +
                    "    GROUP BY BU.UNIDAD_DE_NEGOCIO " +
                    ") T2 ON T1.BUSINESS_UNIT = T2.BUSINESS_UNIT " +
                    "INNER JOIN ( " +
                    "    SELECT BU.UNIDAD_DE_NEGOCIO AS BUSINESS_UNIT, COUNT(DISTINCT CO.ID)AS QTY_NOT_SENT " +
                    "    FROM ( " +
                    "        SELECT DISTINCT UNIDAD_DE_NEGOCIO FROM CP_EMPLEADOS " +
                    "    ) BU " +
                    "    LEFT JOIN NOTIFICACIONES NO ON NO.UNIDAD_NEGOCIO = BU.UNIDAD_DE_NEGOCIO AND NO.INITIAL_DATE > SYSDATE " +
                    "    LEFT JOIN CONFIRMACIONES CO ON NO.ID = CO.ID_NOTIFICACION " +
                    "    LEFT OUTER JOIN CP_EMPLEADOS EM ON EM.UNIDAD_DE_NEGOCIO = NO.UNIDAD_NEGOCIO " +
                    "    GROUP BY BU.UNIDAD_DE_NEGOCIO " +
                    ") T3 ON T1.BUSINESS_UNIT = T3.BUSINESS_UNIT " +
                    "INNER JOIN ( " +
                    "    SELECT BU.UNIDAD_DE_NEGOCIO AS BUSINESS_UNIT, COUNT(DISTINCT CO.ID) AS QTY_SENT " +
                    "    FROM ( " +
                    "        SELECT DISTINCT UNIDAD_DE_NEGOCIO FROM CP_EMPLEADOS " +
                    "    ) BU " +
                    "    LEFT JOIN NOTIFICACIONES NO ON NO.UNIDAD_NEGOCIO = BU.UNIDAD_DE_NEGOCIO AND NO.INITIAL_DATE <= SYSDATE " +
                    "    LEFT JOIN CONFIRMACIONES CO ON NO.ID = CO.ID_NOTIFICACION " +
                    "    LEFT OUTER JOIN CP_EMPLEADOS EM ON EM.UNIDAD_DE_NEGOCIO = NO.UNIDAD_NEGOCIO " +
                    "    GROUP BY BU.UNIDAD_DE_NEGOCIO " +
                    ") T4 ON T1.BUSINESS_UNIT = T4.BUSINESS_UNIT  WHERE 1 = 0 " +
                    "ORDER BY T1.BUSINESS_UNIT ASC",
            nativeQuery = true
    )
    List<Object> getConfirmationsByBusinessUnit();

    @Query(
            value = "SELECT AU.NOMBRE_MOSTRADO AS USER_FULL_NAME, COUNT(1) AS QTY_FIXED_ASSETS " +
                    "FROM ADM_USUARIO AU " +
                    "INNER JOIN CP_EMPLEADOS CE ON AU.CORREO_ELECTRONICO = CE.EMAIL_COLABORADOR " +
                    "INNER JOIN ADM_USUARIO US ON US.CORREO_ELECTRONICO = CE.EMAIL_JEFE_INMEDIATO " +
                    "INNER JOIN CP_EMPLEADOS_EBS EM ON EM.EMAIL_ADDRESS = AU.CORREO_ELECTRONICO " +
                    "INNER JOIN CP_ACTIVOS_FIJOS AF ON AF.EMPLOYEE_NUMBER = EM.EMPLOYEE_NUMBER " +
                    "WHERE 1 = 1 " +
                    "AND AU.ATTRIBUTE3 = 1 " +
                    "AND US.ID = ?1 " +
                    "GROUP BY AU.NOMBRE_MOSTRADO " +
                    "ORDER BY AU.NOMBRE_MOSTRADO ASC",
            nativeQuery = true
    )
    List<Object> getMyTeamUnsubscribedEmployeesFixedAssets(Long managerId);

    @Query(
            value = "SELECT AU.NOMBRE_MOSTRADO AS USER_FULL_NAME, COUNT(1) AS QTY_FIXED_ASSETS " +
                    "FROM ADM_USUARIO AU " +
                    "INNER JOIN CP_EMPLEADOS_EBS EM ON EM.EMAIL_ADDRESS = AU.CORREO_ELECTRONICO " +
                    "INNER JOIN CP_ACTIVOS_FIJOS AF ON AF.EMPLOYEE_NUMBER = EM.EMPLOYEE_NUMBER " +
                    "WHERE 1 = 1 " +
                    "AND AU.ATTRIBUTE3 = 1 " +
                    "GROUP BY AU.NOMBRE_MOSTRADO " +
                    "ORDER BY AU.NOMBRE_MOSTRADO ASC",
            nativeQuery = true
    )
    List<Object> getAllUnsubscribedEmployeesFixedAssets();

    @Query(
            value = "SELECT T1.BUSINESS_UNIT, T3.QTY_NOT_SENT + T4.QTY_SENT AS QTY_TOTAL, T3.QTY_NOT_SENT, T4.QTY_SENT, NVL(T1.QTY_CONFIRMED, 0) AS QTY_CONFIRMED, NVL(T1.QTY_UNCONFIRMED, 0) AS QTY_UNCONFIRMED " +
                    "FROM ( " +
                    "         SELECT BU.UNIDAD_DE_NEGOCIO AS BUSINESS_UNIT, UN.QTY_UNCONFIRMED, CO.QTY_CONFIRMED " +
                    "         FROM ( " +
                    "                SELECT T01.UNIDAD_DE_NEGOCIO, COUNT(1) AS QTY_UNCONFIRMED FROM ( " +
                    "                    SELECT CE.UNIDAD_DE_NEGOCIO, AU.ID, COUNT(1) AS QTY_UNCONFIRMED " +
                    "                    FROM CONFIRMACIONES CO " +
                    "                             INNER JOIN ADM_USUARIO AU on CO.USUARIO = AU.ID " +
                    "                             INNER JOIN CP_EMPLEADOS CE on AU.CORREO_ELECTRONICO = CE.EMAIL_COLABORADOR " +
                    "                    WHERE 1 = 1 " +
                    "                      AND CO.ESTADO = 'S' " +
                    "                    GROUP BY CE.UNIDAD_DE_NEGOCIO, AU.ID " +
                    "                ) T01 " +
                    "                GROUP BY T01.UNIDAD_DE_NEGOCIO " +
                    "              ) UN " +
                    "                  RIGHT OUTER JOIN " +
                    "              ( " +
                    "                  SELECT T02.UNIDAD_DE_NEGOCIO, COUNT(1) AS QTY_CONFIRMED FROM ( " +
                    "                      SELECT CE.UNIDAD_DE_NEGOCIO, AU.ID, COUNT(1) AS QTY_CONFIRMED " +
                    "                      FROM CONFIRMACIONES CO " +
                    "                               INNER JOIN ADM_USUARIO AU on CO.USUARIO = AU.ID " +
                    "                               INNER JOIN CP_EMPLEADOS CE on AU.CORREO_ELECTRONICO = CE.EMAIL_COLABORADOR " +
                    "                      WHERE 1 = 1 " +
                    "                        AND CO.ESTADO IN ('C', 'R') " +
                    "                      GROUP BY CE.UNIDAD_DE_NEGOCIO, AU.ID " +
                    "                  ) T02 " +
                    "                  GROUP BY T02.UNIDAD_DE_NEGOCIO " +
                    "              ) CO ON CO.UNIDAD_DE_NEGOCIO = UN.UNIDAD_DE_NEGOCIO " +
                    "                  RIGHT OUTER JOIN ( " +
                    "             SELECT DISTINCT UNIDAD_DE_NEGOCIO " +
                    "             FROM CP_EMPLEADOS " +
                    "         ) BU ON BU.UNIDAD_DE_NEGOCIO = CO.UNIDAD_DE_NEGOCIO " +
                    "     ) T1 " +
                    "INNER JOIN ( " +
                    "    SELECT " +
                    "        BU.UNIDAD_DE_NEGOCIO AS BUSINESS_UNIT, NVL(TX.QTY_EMPLOYEES, 0) AS QTY_NOT_SENT " +
                    "    FROM ( " +
                    "        SELECT DISTINCT UNIDAD_DE_NEGOCIO FROM CP_EMPLEADOS " +
                    "    ) BU " +
                    "    LEFT OUTER JOIN NOTIFICACIONES NO ON NO.UNIDAD_NEGOCIO = BU.UNIDAD_DE_NEGOCIO AND NO.INITIAL_DATE > SYSDATE " +
                    "    LEFT OUTER JOIN ( " +
                    "        SELECT EM.UNIDAD_DE_NEGOCIO, COUNT(1) AS QTY_EMPLOYEES " +
                    "        FROM CP_EMPLEADOS EM " +
                    "        GROUP BY EM.UNIDAD_DE_NEGOCIO " +
                    "    ) TX ON TX.UNIDAD_DE_NEGOCIO = NO.UNIDAD_NEGOCIO " +
                    ") T3 ON T1.BUSINESS_UNIT = T3.BUSINESS_UNIT " +
                    "INNER JOIN ( " +
                    "    SELECT " +
                    "        BU.UNIDAD_DE_NEGOCIO AS BUSINESS_UNIT, NVL(TX.QTY_EMPLOYEES, 0) AS QTY_SENT " +
                    "    FROM ( " +
                    "        SELECT DISTINCT UNIDAD_DE_NEGOCIO FROM CP_EMPLEADOS " +
                    "    ) BU " +
                    "    LEFT OUTER JOIN NOTIFICACIONES NO ON NO.UNIDAD_NEGOCIO = BU.UNIDAD_DE_NEGOCIO AND NO.INITIAL_DATE <= SYSDATE " +
                    "    LEFT OUTER JOIN ( " +
                    "        SELECT EM.UNIDAD_DE_NEGOCIO, COUNT(1) AS QTY_EMPLOYEES " +
                    "        FROM CP_EMPLEADOS EM " +
                    "        GROUP BY EM.UNIDAD_DE_NEGOCIO " +
                    "    ) TX ON TX.UNIDAD_DE_NEGOCIO = NO.UNIDAD_NEGOCIO " +
                    ") T4 ON T1.BUSINESS_UNIT = T4.BUSINESS_UNIT WHERE 1 = 0 " +
                    "ORDER BY T1.BUSINESS_UNIT ASC",
            nativeQuery = true
    )
    List<Object> getConfirmationsByBusinessUnitEmployeesCount();
}
