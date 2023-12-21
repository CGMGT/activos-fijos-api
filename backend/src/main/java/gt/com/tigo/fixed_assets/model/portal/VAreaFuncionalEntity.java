package gt.com.tigo.fixed_assets.model.portal;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(VAreaFuncionalEntity.class)
@Table(name = "V_AREA_FUNCIONAL", schema = "", catalog = "")
public class VAreaFuncionalEntity implements Serializable {
    private String unidadNegocio;
    private String areaFuncional;

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

}
