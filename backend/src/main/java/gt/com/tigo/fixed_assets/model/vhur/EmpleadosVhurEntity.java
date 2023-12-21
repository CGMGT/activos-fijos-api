package gt.com.tigo.fixed_assets.model.vhur;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EmpleadosVhurEntity {
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
    private String unidadNegocio;
    private long codigoJefe;

    public String getApellidosNombres() {
        return apellidosNombres;
    }

    public void setApellidosNombres(String apellidosNombres) {
        this.apellidosNombres = apellidosNombres;
    }

    public String getNombresApellidos() {
        return nombresApellidos;
    }

    public void setNombresApellidos(String nombresApellidos) {
        this.nombresApellidos = nombresApellidos;
    }

    @Id
    public long getEmpCodigo() {
        return empCodigo;
    }

    public void setEmpCodigo(long empCodigo) {
        this.empCodigo = empCodigo;
    }

    public String getEmailColaborador() {
        return emailColaborador;
    }

    public void setEmailColaborador(String emailColaborador) {
        this.emailColaborador = emailColaborador;
    }

    public String getNoStaff() {
        return noStaff;
    }

    public void setNoStaff(String noStaff) {
        this.noStaff = noStaff;
    }

    public String getPlaza() {
        return plaza;
    }

    public void setPlaza(String plaza) {
        this.plaza = plaza;
    }

    public String getNombreJefeInmediato() {
        return nombreJefeInmediato;
    }

    public void setNombreJefeInmediato(String nombreJefeInmediato) {
        this.nombreJefeInmediato = nombreJefeInmediato;
    }

    public String getEmailJefeInmediato() {
        return emailJefeInmediato;
    }

    public void setEmailJefeInmediato(String emailJefeInmediato) {
        this.emailJefeInmediato = emailJefeInmediato;
    }

    public String getUnidadAdministrativa() {
        return unidadAdministrativa;
    }

    public void setUnidadAdministrativa(String unidadAdministrativa) {
        this.unidadAdministrativa = unidadAdministrativa;
    }

    public String getAreaFuncional() {
        return areaFuncional;
    }

    public void setAreaFuncional(String areaFuncional) {
        this.areaFuncional = areaFuncional;
    }

    public String getUnidadNegocio() {
        return unidadNegocio;
    }

    public void setUnidadNegocio(String unidadNegocio) {
        this.unidadNegocio = unidadNegocio;
    }

    public long getCodigoJefe() {
        return codigoJefe;
    }

    public void setCodigoJefe(long codigoJefe) {
        this.codigoJefe = codigoJefe;
    }
}
