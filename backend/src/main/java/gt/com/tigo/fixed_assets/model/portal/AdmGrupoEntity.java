package gt.com.tigo.fixed_assets.model.portal;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "ADM_GRUPO", schema = "", catalog = "")
@SequenceGenerator(name="SEQ_ADM_GRUPO", sequenceName = "SEQ_ADM_GRUPO", initialValue=1, allocationSize=1)
public class AdmGrupoEntity {
    private long id;
    private String nombre;
    private String descripcion;
    private String estado;
    private String eliminable;
    private String modificable;
    private Timestamp fechaCreacion;
    private String usuarioCreacion;
    private Timestamp fechaModificacion;
    private String usuarioModificacion;
    private List<AdmPermisoEntity> permisos;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADM_GRUPO")
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = false, length = 250)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "DESCRIPCION", nullable = false, length = 1000)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
    @Column(name = "ELIMINABLE", nullable = false, length = 1)
    public String getEliminable() {
        return eliminable;
    }

    public void setEliminable(String eliminable) {
        this.eliminable = eliminable;
    }

    @Basic
    @Column(name = "MODIFICABLE", nullable = false, length = 1)
    public String getModificable() {
        return modificable;
    }

    public void setModificable(String modificable) {
        this.modificable = modificable;
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
    @Column(name = "USUARIO_CREACION", nullable = false, length = 250)
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
    @Column(name = "USUARIO_MODIFICACION", nullable = true, length = 250)
    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    @ManyToMany
    @JoinTable(
            name = "ADM_GRUPO_REL_PERMISO",
            joinColumns = @JoinColumn(name = "GRUPO"),
            inverseJoinColumns = @JoinColumn(name = "PERMISO")
    )
    public List<AdmPermisoEntity> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<AdmPermisoEntity> permisos) {
        this.permisos = permisos;
    }

}
