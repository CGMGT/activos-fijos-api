package gt.com.tigo.fixed_assets.model.portal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "DOCUMENTOS", schema = "", catalog = "")
@SequenceGenerator(name="SEQ_DOCUMENTOS", sequenceName = "SEQ_DOCUMENTOS", initialValue=1, allocationSize=1)
public class DocumentosEntity {
    private long id;
    private String tipoArchivo;
    private String nombreArchivo;
    private String descripcion;
    private String estado;
    private Long peso;
    private byte[] contenido;
    private Long idGestionDet;
    private Timestamp fechaCreacion;
    private String usuarioCreacion;
    private String atribute1;
    private String atribute2;
    private String atribute3;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DOCUMENTOS")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TIPO_ARCHIVO", nullable = true, length = 250)
    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    @Basic
    @Column(name = "NOMBRE_ARCHIVO", nullable = true, length = 500)
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    @Basic
    @Column(name = "DESCRIPCION", nullable = true, length = 350)
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
    @Column(name = "PESO", nullable = true, precision = 0)
    public Long getPeso() {
        return peso;
    }

    public void setPeso(Long peso) {
        this.peso = peso;
    }

    @JsonIgnore
    @Basic
    @Column(name = "CONTENIDO", nullable = true)
    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    @Basic
    @Column(name = "ID_GESTION_DET", nullable = false, precision = 0)
    public Long getIdGestionDet() {
        return idGestionDet;
    }

    public void setIdGestionDet(Long idGestionDet) {
        this.idGestionDet = idGestionDet;
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

}
