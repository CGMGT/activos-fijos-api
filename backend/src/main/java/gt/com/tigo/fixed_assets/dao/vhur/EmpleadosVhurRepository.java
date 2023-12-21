package gt.com.tigo.fixed_assets.dao.vhur;

import gt.com.tigo.fixed_assets.model.vhur.EmpleadosVhurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpleadosVhurRepository extends JpaRepository<EmpleadosVhurEntity, Long>{

    @Query(value = "Select distinct apellidos_nombres apellidosNombres, nombres_apellidos nombresApellidos, emp_codigo empCodigo, \n" +
            "       email_colaborador emailColaborador, no_staff noStaff, plaza, nombre_jefe_inmediato nombreJefeInmediato,\n" +
            "       email_jefe_inmediato emailJefeInmediato, unidad_administrativa unidadAdministrativa, \n" +
            "\t   area_funcional areaFuncional, unidad_de_negocio unidadNegocio, codigo_jefe codigoJefe\n" +
            "from view_empleados_activos_fijos\n" +
            "where emp_codigo = ?1", nativeQuery = true)
    EmpleadosVhurEntity findById(long id);

    @Query(value = "Select distinct apellidos_nombres apellidosNombres, nombres_apellidos nombresApellidos, emp_codigo empCodigo, \n" +
            "       email_colaborador emailColaborador, no_staff noStaff, plaza, nombre_jefe_inmediato nombreJefeInmediato,\n" +
            "       email_jefe_inmediato emailJefeInmediato, unidad_administrativa unidadAdministrativa, \n" +
            "\t   area_funcional areaFuncional, unidad_de_negocio unidadNegocio, codigo_jefe codigoJefe\n" +
            "from view_empleados_activos_fijos" , nativeQuery = true)
    List<EmpleadosVhurEntity> findAll();

    @Query(value = "Select distinct apellidos_nombres apellidosNombres, nombres_apellidos nombresApellidos, emp_codigo empCodigo, \n" +
            "       email_colaborador emailColaborador, no_staff noStaff, plaza, nombre_jefe_inmediato nombreJefeInmediato,\n" +
            "       email_jefe_inmediato emailJefeInmediato, unidad_administrativa unidadAdministrativa, \n" +
            "\t   area_funcional areaFuncional, unidad_de_negocio unidadNegocio, codigo_jefe codigoJefe\n" +
            "from view_empleados_activos_fijos order by emp_codigo OFFSET ?1 ROWS FETCH NEXT ?2 ROWS ONLY" ,
            countQuery = "select count(*) from view_empleados_activos_fijos",
            nativeQuery = true)
    List<EmpleadosVhurEntity> findAllByPage(long offset, long fetchRows);
}
