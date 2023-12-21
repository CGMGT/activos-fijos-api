package gt.com.tigo.fixed_assets.model.portal;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "XX_INT_CUSTOMERS", schema = "", catalog = "")
public class XxIntCustomersEntity {
    private String apellidosNombres;
    private String nombresApellidos;
    private long empCodigo;
    private String emailColaborador;
    private String noStaff;
    private String plaza;
    private String nombreJefeInmediato;
    private String emailJefeInmediato;
    private String unidadAdministrativa;
    private String areaFuncional;
    private String unidadDeNegocio;
    private long codigoJefe;

    @Basic
    @Column(name = "APELLIDOS_NOMBRES", nullable = true, length = 108)
    public String getApellidosNombres() {
        return apellidosNombres;
    }

    public void setApellidosNombres(String apellidosNombres) {
        this.apellidosNombres = apellidosNombres;
    }

    @Basic
    @Column(name = "NOMBRES_APELLIDOS", nullable = true, length = 107)
    public String getNombresApellidos() {
        return nombresApellidos;
    }

    public void setNombresApellidos(String nombresApellidos) {
        this.nombresApellidos = nombresApellidos;
    }

    @Basic
    @Id
    @Column(name = "EMP_CODIGO", nullable = true, precision = 0)
    public long getEmpCodigo() {
        return empCodigo;
    }

    public void setEmpCodigo(long empCodigo) {
        this.empCodigo = empCodigo;
    }

    @Basic
    @Column(name = "EMAIL_COLABORADOR", nullable = true, length = 100)
    public String getEmailColaborador() {
        return emailColaborador;
    }

    public void setEmailColaborador(String emailColaborador) {
        this.emailColaborador = emailColaborador;
    }

    @Basic
    @Column(name = "NO_STAFF", nullable = true, length = 45)
    public String getNoStaff() {
        return noStaff;
    }

    public void setNoStaff(String noStaff) {
        this.noStaff = noStaff;
    }

    @Basic
    @Column(name = "PLAZA", nullable = true, length = 80)
    public String getPlaza() {
        return plaza;
    }

    public void setPlaza(String plaza) {
        this.plaza = plaza;
    }

    @Basic
    @Column(name = "NOMBRE_JEFE_INMEDIATO", nullable = true, length = 107)
    public String getNombreJefeInmediato() {
        return nombreJefeInmediato;
    }

    public void setNombreJefeInmediato(String nombreJefeInmediato) {
        this.nombreJefeInmediato = nombreJefeInmediato;
    }

    @Basic
    @Column(name = "EMAIL_JEFE_INMEDIATO", nullable = true, length = 100)
    public String getEmailJefeInmediato() {
        return emailJefeInmediato;
    }

    public void setEmailJefeInmediato(String emailJefeInmediato) {
        this.emailJefeInmediato = emailJefeInmediato;
    }

    @Basic
    @Column(name = "UNIDAD_ADMINISTRATIVA", nullable = true, length = 80)
    public String getUnidadAdministrativa() {
        return unidadAdministrativa;
    }

    public void setUnidadAdministrativa(String unidadAdministrativa) {
        this.unidadAdministrativa = unidadAdministrativa;
    }

    @Basic
    @Column(name = "AREA_FUNCIONAL", nullable = false, length = 50)
    public String getAreaFuncional() {
        return areaFuncional;
    }

    public void setAreaFuncional(String areaFuncional) {
        this.areaFuncional = areaFuncional;
    }

    @Basic
    @Column(name = "UNIDAD_DE_NEGOCIO", nullable = false, length = 50)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XxIntCustomersEntity that = (XxIntCustomersEntity) o;
        return codigoJefe == that.codigoJefe &&
                Objects.equals(apellidosNombres, that.apellidosNombres) &&
                Objects.equals(nombresApellidos, that.nombresApellidos) &&
                Objects.equals(empCodigo, that.empCodigo) &&
                Objects.equals(emailColaborador, that.emailColaborador) &&
                Objects.equals(noStaff, that.noStaff) &&
                Objects.equals(plaza, that.plaza) &&
                Objects.equals(nombreJefeInmediato, that.nombreJefeInmediato) &&
                Objects.equals(emailJefeInmediato, that.emailJefeInmediato) &&
                Objects.equals(unidadAdministrativa, that.unidadAdministrativa) &&
                Objects.equals(areaFuncional, that.areaFuncional) &&
                Objects.equals(unidadDeNegocio, that.unidadDeNegocio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apellidosNombres, nombresApellidos, empCodigo, emailColaborador, noStaff, plaza, nombreJefeInmediato, emailJefeInmediato, unidadAdministrativa, areaFuncional, unidadDeNegocio, codigoJefe);
    }
}
