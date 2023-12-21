package gt.com.tigo.fixed_assets.model.portal;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "CONFIRMACIONES", schema = "", catalog = "")
public class ConfirmacionesEntity {
    private long id;
    private NotificacionesEntity idNotificacion;
    private AdmUsuarioEntity usuario;
    private CpActivosFijosEntity activoFijo;
    private String estado;
    private String comentarios;
    private Timestamp fechaActualiza;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "ID_NOTIFICACION", referencedColumnName = "ID")
    public NotificacionesEntity getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(NotificacionesEntity idNotificacion) {
        this.idNotificacion = idNotificacion;
    }


    @ManyToOne
    @JoinColumn(name = "USUARIO", referencedColumnName = "ID")
    public AdmUsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(AdmUsuarioEntity usuario) {
        this.usuario = usuario;
    }

    @ManyToOne
    @JoinColumn(name = "ACTIVO_FIJO", referencedColumnName = "ID")
    public CpActivosFijosEntity getActivoFijo() {
        return activoFijo;
    }

    public void setActivoFijo(CpActivosFijosEntity activoFijo) {
        this.activoFijo = activoFijo;
    }

    @Basic
    @Column(name = "ESTADO", nullable = true, length = 10)
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Basic
    @Column(name = "COMENTARIOS", nullable = true, length = 150)
    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    @Basic
    @Column(name = "FECHA_ACTUALIZA", nullable = true)
    public Timestamp getFechaActualiza() {
        return fechaActualiza;
    }

    public void setFechaActualiza(Timestamp fechaActualiza) {
        this.fechaActualiza = fechaActualiza;
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

    @Basic
    @Column(name = "ATTRIBUTE4", nullable = true, length = 250)
    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

}
