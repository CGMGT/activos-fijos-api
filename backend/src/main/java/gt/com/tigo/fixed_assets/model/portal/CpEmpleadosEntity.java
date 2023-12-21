package gt.com.tigo.fixed_assets.model.portal;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "CP_EMPLEADOS", schema = "", catalog = "")
public class CpEmpleadosEntity {
    private Long id;
    private Long empCodigo;
    private String apellidosNombres;
    private String nombresApellidos;
    private String emailColaborador;
    private String noStaff;
    private String plaza;
    private String nombreJefeInmediato;
    private String emailJefeInmediato;
    private String unidadAdministrativa;
    private String areaFuncional;
    private String unidadDeNegocio;
    private long codigoJefe;
    private String estado;
    private Timestamp fechaCreacion;
    private String usuarioCreacion;
    private Timestamp fechaModificacion;
    private String usuarioModificacion;
    private String attribute1;
    private String attribute2;
    private String attribute3;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "EMP_CODIGO", nullable = false, precision = 0)
    public Long getEmpCodigo() {
        return empCodigo;
    }

    public void setEmpCodigo(Long empCodigo) {
        this.empCodigo = empCodigo;
    }

    @Basic
    @Column(name = "APELLIDOS_NOMBRES", nullable = true, length = 250)
    public String getApellidosNombres() {
        return apellidosNombres;
    }

    public void setApellidosNombres(String apellidosNombres) {
        this.apellidosNombres = apellidosNombres;
    }

    @Basic
    @Column(name = "NOMBRES_APELLIDOS", nullable = true, length = 250)
    public String getNombresApellidos() {
        return nombresApellidos;
    }

    public void setNombresApellidos(String nombresApellidos) {
        this.nombresApellidos = nombresApellidos;
    }

    @Basic
    @Column(name = "EMAIL_COLABORADOR", nullable = true, length = 150)
    public String getEmailColaborador() {
        return emailColaborador;
    }

    public void setEmailColaborador(String emailColaborador) {
        this.emailColaborador = emailColaborador;
    }

    @Basic
    @Column(name = "NO_STAFF", nullable = true, length = 100)
    public String getNoStaff() {
        return noStaff;
    }

    public void setNoStaff(String noStaff) {
        this.noStaff = noStaff;
    }

    @Basic
    @Column(name = "PLAZA", nullable = true, length = 150)
    public String getPlaza() {
        return plaza;
    }

    public void setPlaza(String plaza) {
        this.plaza = plaza;
    }

    @Basic
    @Column(name = "NOMBRE_JEFE_INMEDIATO", nullable = true, length = 250)
    public String getNombreJefeInmediato() {
        return nombreJefeInmediato;
    }

    public void setNombreJefeInmediato(String nombreJefeInmediato) {
        this.nombreJefeInmediato = nombreJefeInmediato;
    }

    @Basic
    @Column(name = "EMAIL_JEFE_INMEDIATO", nullable = true, length = 150)
    public String getEmailJefeInmediato() {
        return emailJefeInmediato;
    }

    public void setEmailJefeInmediato(String emailJefeInmediato) {
        this.emailJefeInmediato = emailJefeInmediato;
    }

    @Basic
    @Column(name = "UNIDAD_ADMINISTRATIVA", nullable = true, length = 150)
    public String getUnidadAdministrativa() {
        return unidadAdministrativa;
    }

    public void setUnidadAdministrativa(String unidadAdministrativa) {
        this.unidadAdministrativa = unidadAdministrativa;
    }

    @Basic
    @Column(name = "AREA_FUNCIONAL", nullable = false, length = 150)
    public String getAreaFuncional() {
        return areaFuncional;
    }

    public void setAreaFuncional(String areaFuncional) {
        this.areaFuncional = areaFuncional;
    }

    @Basic
    @Column(name = "UNIDAD_DE_NEGOCIO", nullable = false, length = 150)
    public String getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public void setUnidadDeNegocio(String unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
    }

    @Basic
    @Column(name = "CODIGO_JEFE", nullable = false, precision = 0)
    public long getCodigoJefe() {
        return codigoJefe;
    }

    public void setCodigoJefe(long codigoJefe) {
        this.codigoJefe = codigoJefe;
    }

    @Basic
    @Column(name = "ESTADO", nullable = false, length = 1)
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Basic
    @Column(name = "FECHA_CREACION", nullable = false)
    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Basic
    @Column(name = "USUARIO_CREACION", nullable = false, length = 150)
    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    @Basic
    @Column(name = "FECHA_MODIFICACION", nullable = true)
    public Timestamp getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Timestamp fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @Basic
    @Column(name = "USUARIO_MODIFICACION", nullable = true, length = 150)
    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    @Basic
    @Column(name = "ATTRIBUTE1", nullable = true, length = 250)
    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    @Basic
    @Column(name = "ATTRIBUTE2", nullable = true, length = 250)
    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    @Basic
    @Column(name = "ATTRIBUTE3", nullable = true, length = 250)
    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }
}
