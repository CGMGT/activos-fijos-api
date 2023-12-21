package gt.com.tigo.fixed_assets.model.portal;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "FLUJO_APROBACION", schema = "", catalog = "")
public class FlujoAprobacionEntity {
    private long id;
    private short orden;
    private String evento;
    private String descripcion;
    private String estado;
    private Timestamp fechaCreacion;
    private String usuarioCreacion;
    private Timestamp fechaModificacion;
    private String usuarioModificacion;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private TipoGestionesEntity tipoGestion;
    private EstadosAprobacionEntity estadoInicial;
    private EstadosAprobacionEntity estadoFinal;


    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ORDEN", nullable = false, precision = 0)
    public short getOrden() {
        return orden;
    }

    public void setOrden(short orden) {
        this.orden = orden;
    }

    @Basic
    @Column(name = "EVENTO", nullable = true, length = 250)
    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    @Basic
    @Column(name = "DESCRIPCION", nullable = true, length = 250)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
    @Column(name = "FECHA_MODIFICACION", nullable = true)
    public Timestamp getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Timestamp fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @Basic
    @Column(name = "USUARIO_MODIFICACION", nullable = true, length = 50)
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

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_GESTION", referencedColumnName = "ID", nullable = false)
    public TipoGestionesEntity getTipoGestion() {
        return tipoGestion;
    }

    public void setTipoGestion(TipoGestionesEntity tipoGestion) {
        this.tipoGestion = tipoGestion;
    }

    @ManyToOne
    @JoinColumn(name = "ESTADO_INICIAL", referencedColumnName = "ID")
    public EstadosAprobacionEntity getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(EstadosAprobacionEntity estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    @ManyToOne
    @JoinColumn(name = "ESTADO_FINAL", referencedColumnName = "ID")
    public EstadosAprobacionEntity getEstadoFinal() {
        return estadoFinal;
    }

    public void setEstadoFinal(EstadosAprobacionEntity estadoFinal) {
        this.estadoFinal = estadoFinal;
    }

}
