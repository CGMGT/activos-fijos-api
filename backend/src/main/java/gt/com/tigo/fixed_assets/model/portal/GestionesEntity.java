package gt.com.tigo.fixed_assets.model.portal;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "GESTIONES", schema = "", catalog = "")
@SequenceGenerator(name="SEQ_GESTIONES", sequenceName = "SEQ_GESTIONES", initialValue=1, allocationSize=1)
public class GestionesEntity {
    private long id;
    private String descripcion;
    private CpEmpleadosEntity receptor;
    private AdmUsuarioEntity jefe;
    private Timestamp fechaRechazo;
    private String motivoRechazo;
    private String comentarios;
    private String tipoTraslado;
    private Timestamp fechaCreacion;
    private String usuarioCreacion;
    private Timestamp fechaModificacion;
    private String usuarioModificacion;
    private String atribute1;
    private String atribute2;
    private String atribute3;
    private String attribute4;
    private String attribute5;
    private Collection<GestionesDetalleEntity> detalle;
    private TipoGestionesEntity tipoGestion;
    private EstadosAprobacionEntity estado;
    private CpLocationsEntity locacion;
    private Collection<TrackingFlujoEstadoEntity> trackingEstados;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GESTIONES")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
    @Column(name = "FECHA_RECHAZO", nullable = true)
    public Timestamp getFechaRechazo() {
        return fechaRechazo;
    }

    public void setFechaRechazo(Timestamp fechaRechazo) {
        this.fechaRechazo = fechaRechazo;
    }

    @Basic
    @Column(name = "MOTIVO_RECHAZO", nullable = true, length = 250)
    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    @Basic
    @Column(name = "COMENTARIOS", nullable = true, length = 500)
    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    @Basic
    @Column(name = "TIPO_TRASLADO", nullable = true, length = 250)
    public String getTipoTraslado() {
        return tipoTraslado;
    }

    public void setTipoTraslado(String tipoTraslado) {
        this.tipoTraslado = tipoTraslado;
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_GESTION", referencedColumnName = "ID")
    public Collection<GestionesDetalleEntity> getDetalle() {
        return detalle;
    }

    public void setDetalle(Collection<GestionesDetalleEntity> gestionesDetalle) {
        this.detalle = gestionesDetalle;
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
    @JoinColumn(name = "ESTADO", referencedColumnName = "ID")
    public EstadosAprobacionEntity getEstado() {
        return estado;
    }

    public void setEstado(EstadosAprobacionEntity estado) {
        this.estado = estado;
    }

    @ManyToOne
    @JoinColumn(name = "ID_LOCACION", referencedColumnName = "ID")
    public CpLocationsEntity getLocacion() {
        return locacion;
    }

    public void setLocacion(CpLocationsEntity locacion) {
        this.locacion = locacion;
    }

    @ManyToOne
    @JoinColumn(name = "ID_RECEPTOR", referencedColumnName = "ID")
    public CpEmpleadosEntity getReceptor() {
        return receptor;
    }

    public void setReceptor(CpEmpleadosEntity receptor) {
        this.receptor = receptor;
    }

    @ManyToOne
    @JoinColumn(name = "ID_JEFE", referencedColumnName = "ID")
    public AdmUsuarioEntity getJefe() {
        return jefe;
    }

    public void setJefe(AdmUsuarioEntity jefe) {
        this.jefe = jefe;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_GESTION", referencedColumnName = "ID")
    @OrderBy("id")
    public Collection<TrackingFlujoEstadoEntity> getTrackingEstados() {
        return trackingEstados;
    }

    public void setTrackingEstados(Collection<TrackingFlujoEstadoEntity> trackingEstados) {
        this.trackingEstados = trackingEstados;
    }
}
