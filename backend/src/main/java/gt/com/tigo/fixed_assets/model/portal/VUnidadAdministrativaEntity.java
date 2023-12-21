package gt.com.tigo.fixed_assets.model.portal;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(VUnidadAdministrativaEntity.class)
@Table(name = "V_UNIDAD_ADMINISTRATIVA", schema = "", catalog = "")
public class VUnidadAdministrativaEntity implements Serializable {
    private String unidadNegocio;
    private String areaFuncional;
    private String unidadAdministrativa;

    @Basic
    @Id
    @Column(name = "UNIDAD_NEGOCIO", nullable = false, length = 150)
    public String getUnidadNegocio() {
        return unidadNegocio;
    }

    public void setUnidadNegocio(String unidadNegocio) {
        this.unidadNegocio = unidadNegocio;
    }

    @Basic
    @Id
    @Column(name = "AREA_FUNCIONAL", nullable = false, length = 150)
    public String getAreaFuncional() {
        return areaFuncional;
    }

    public void setAreaFuncional(String areaFuncional) {
        this.areaFuncional = areaFuncional;
    }

    @Basic
    @Id
    @Column(name = "UNIDAD_ADMINISTRATIVA", nullable = true, length = 150)
    public String getUnidadAdministrativa() {
        return unidadAdministrativa;
    }

    public void setUnidadAdministrativa(String unidadAdministrativa) {
        this.unidadAdministrativa = unidadAdministrativa;
    }

}
