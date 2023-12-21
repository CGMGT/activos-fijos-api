package gt.com.tigo.fixed_assets.model.portal;

import javax.persistence.*;

@Entity
@Table(name = "V_UNIDAD_NEGOCIO", schema = "", catalog = "")
public class VUnidadNegocioEntity {
    private String unidadNegocio;

    @Basic
    @Id
    @Column(name = "UNIDAD_NEGOCIO", nullable = false, length = 150)
    public String getUnidadNegocio() {
        return unidadNegocio;
    }

    public void setUnidadNegocio(String unidadNegocio) {
        this.unidadNegocio = unidadNegocio;
    }

}
