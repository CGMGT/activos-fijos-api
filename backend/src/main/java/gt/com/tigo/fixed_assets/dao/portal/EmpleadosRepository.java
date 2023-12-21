package gt.com.tigo.fixed_assets.dao.portal;

import gt.com.tigo.fixed_assets.model.portal.CpEmpleadosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpleadosRepository extends JpaRepository<CpEmpleadosEntity, Long>, JpaSpecificationExecutor<CpEmpleadosEntity> {

    CpEmpleadosEntity findByEmailColaborador(String emailColaborador);

    @Query(
            value = "SELECT DISTINCT UNIDAD_DE_NEGOCIO " +
                    "  FROM CP_EMPLEADOS  " +
                    "WHERE UNIDAD_DE_NEGOCIO||AREA_FUNCIONAL||UNIDAD_ADMINISTRATIVA " +
                    "                       NOT IN (SELECT UNIDAD_NEGOCIO||AREA_FUNCIONAL||UNIDAD_ADMINISTRATIVA " +
                    "                                 FROM NOTIFICACIONES " +
                    "                                 WHERE PERIODO = PKG_FIXED_ASSETS.F_GET_PARAMETER('aplicacion','CONFIRMATION_PERIOD') ) " +
                    "ORDER BY UNIDAD_DE_NEGOCIO ASC",
            nativeQuery = true)
    List<String> getBusinessUnits();

    @Query(
            value = "SELECT DISTINCT AREA_FUNCIONAL " +
                    "  FROM CP_EMPLEADOS " +
                    "WHERE UNIDAD_DE_NEGOCIO = ?1 " +
                    "  AND UNIDAD_DE_NEGOCIO||AREA_FUNCIONAL||UNIDAD_ADMINISTRATIVA " +
                    "                     NOT IN (SELECT UNIDAD_NEGOCIO||AREA_FUNCIONAL||UNIDAD_ADMINISTRATIVA " +
                    "                               FROM NOTIFICACIONES " +
                    "                              WHERE PERIODO = PKG_FIXED_ASSETS.F_GET_PARAMETER('aplicacion','CONFIRMATION_PERIOD') )  " +
                    "ORDER BY AREA_FUNCIONAL ASC",
            nativeQuery = true)
    List<String> getFunctionalAreaByBusinessUnit(String businessUnit);

    @Query(
            value = "SELECT DISTINCT UNIDAD_ADMINISTRATIVA " +
                    " FROM CP_EMPLEADOS " +
                    "WHERE UNIDAD_DE_NEGOCIO = ?1 " +
                    "  AND AREA_FUNCIONAL = ?2 " +
                    "  AND UNIDAD_DE_NEGOCIO||AREA_FUNCIONAL||UNIDAD_ADMINISTRATIVA " +
                    "                            NOT IN (SELECT UNIDAD_NEGOCIO||AREA_FUNCIONAL||UNIDAD_ADMINISTRATIVA " +
                    "                                      FROM NOTIFICACIONES " +
                    "                                     WHERE PERIODO = PKG_FIXED_ASSETS.F_GET_PARAMETER('aplicacion','CONFIRMATION_PERIOD') )" +
                    "ORDER BY UNIDAD_ADMINISTRATIVA ASC",
            nativeQuery = true)
    List<String> getAdministrativeUnitByFunctionalArea(String businessUnit, String functionalArea);

}
