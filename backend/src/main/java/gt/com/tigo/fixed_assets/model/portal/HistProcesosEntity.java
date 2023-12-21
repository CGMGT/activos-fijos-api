package gt.com.tigo.fixed_assets.model.portal;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "HIST_PROCESOS", schema = "", catalog = "")
public class HistProcesosEntity {
    private long id;
    private String nombre;
    private String descripcion;
    private String estado;
    private Timestamp fechaInicialEjecucion;
    private Timestamp fechaFinalEjecucion;
    private Double tiempoEjecucion;
    private Long numRegistrosCargados;
    private String usuarioEjecucion;
    private String attribute1;
    private String attribute2;
    private String attribute3;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = true, length = 250)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    @Column(name = "FECHA_INICIAL_EJECUCION", nullable = false)
    public Timestamp getFechaInicialEjecucion() {
        return fechaInicialEjecucion;
    }

    public void setFechaInicialEjecucion(Timestamp fechaInicialEjecucion) {
        this.fechaInicialEjecucion = fechaInicialEjecucion;
    }

    @Basic
    @Column(name = "FECHA_FINAL_EJECUCION", nullable = true)
    public Timestamp getFechaFinalEjecucion() {
        return fechaFinalEjecucion;
    }

    public void setFechaFinalEjecucion(Timestamp fechaFinalEjecucion) {
        this.fechaFinalEjecucion = fechaFinalEjecucion;
    }

    @Basic
    @Column(name = "TIEMPO_EJECUCION", nullable = true, precision = 2)
    public Double getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    public void setTiempoEjecucion(Double tiempoEjecucion) {
        this.tiempoEjecucion = tiempoEjecucion;
    }

    @Basic
    @Column(name = "NUM_REGISTROS_CARGADOS", nullable = true, precision = 0)
    public Long getNumRegistrosCargados() {
        return numRegistrosCargados;
    }

    public void setNumRegistrosCargados(Long numRegistrosCargados) {
        this.numRegistrosCargados = numRegistrosCargados;
    }

    @Basic
    @Column(name = "USUARIO_EJECUCION", nullable = true, length = 100)
    public String getUsuarioEjecucion() {
        return usuarioEjecucion;
    }

    public void setUsuarioEjecucion(String usuarioEjecucion) {
        this.usuarioEjecucion = usuarioEjecucion;
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

}
