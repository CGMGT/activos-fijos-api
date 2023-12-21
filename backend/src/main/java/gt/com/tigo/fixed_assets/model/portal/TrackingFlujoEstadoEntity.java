package gt.com.tigo.fixed_assets.model.portal;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "TRACKING_FLUJO_ESTADO", schema = "", catalog = "")
public class TrackingFlujoEstadoEntity {
    private long id;
    private String estado;
    private String descripcion;
    private Timestamp fechaCreacion;
    private String usuarioCreacion;
    private String atribute1;
    private String atribute2;
    private String atribute3;
    private String attribute4;
    private String attribute5;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ESTADO", nullable = false, length = 100)
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Basic
    @Column(name = "DESCRIPCION", nullable = true, length = 500)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
    @Column(name = "USUARIO_CREACION", nullable = true, length = 50)
    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    @Basic
    @Column(name = "ATRIBUTE1", nullable = true, length = 250)
    public String getAtribute1() {
        return atribute1;
    }

    public void setAtribute1(String atribute1) {
        this.atribute1 = atribute1;
    }

    @Basic
    @Column(name = "ATRIBUTE2", nullable = true, length = 250)
    public String getAtribute2() {
        return atribute2;
    }

    public void setAtribute2(String atribute2) {
        this.atribute2 = atribute2;
    }

    @Basic
    @Column(name = "ATRIBUTE3", nullable = true, length = 250)
    public String getAtribute3() {
        return atribute3;
    }

    public void setAtribute3(String atribute3) {
        this.atribute3 = atribute3;
    }

    @Basic
    @Column(name = "ATTRIBUTE4", nullable = true, length = 250)
    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    @Basic
    @Column(name = "ATTRIBUTE5", nullable = true, length = 250)
    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

}
